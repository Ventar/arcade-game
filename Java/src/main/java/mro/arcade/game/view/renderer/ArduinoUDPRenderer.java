package mro.arcade.game.view.renderer;

import mro.arcade.game.common.Color;
import mro.arcade.game.common.Position;
import mro.arcade.game.common.Size;
import mro.arcade.game.view.BoardRenderer;
import mro.arcade.game.view.RenderData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

/**
 * Implementation of a renderer that uses the UDP protocol to send commands to an Arduino device that is able to render the pixels of the game board.
 *
 * @author Michael Rodenbuecher
 * @since 2022-11-15
 */
public class ArduinoUDPRenderer implements BoardRenderer {

    private static final Logger LOG = LoggerFactory.getLogger(ArduinoUDPRenderer.class);

    /**
     * The address of the Arduino device. This address is identified by the MDNS protocol during the creation of this class.
     */
    private InetAddress deviceAddress;

    /**
     * The UDP port of the device that is listening for UDP packages send by this application.
     */
    private int devicePort;

    /**
     * The UDP socket to transmit the data.
     */
    private DatagramSocket socket;

    /**
     * A local matrix of color data for all pixels. Used to send only the delta information to the Arduino device.
     */
    private Color[][] colorData;

    /**
     * The number of pixels (rows and columns) the Arduino device can display.
     */
    private Size size;

    private int frameCounter = 0;

    public ArduinoUDPRenderer(Size size, String ipAddress) {
        try {

            ArduinoDeviceDiscovery discovery = new ArduinoDeviceDiscovery(InetAddress.getByName(ipAddress));
            discovery.discover();

            this.size = size;
            this.deviceAddress = discovery.getDeviceAddress();
            this.devicePort = discovery.getDevicePort();
            this.socket = new DatagramSocket();
            colorData = new Color[size.getWidth()][size.getHeight()];


            clear();
        } catch (Exception e) {
            LOG.warn("Cannot instantiate ArduinoUDPRenderer: ", e);
        }
    }

    @Override
    public void clear() {
        byte[] data = new byte[1];
        data[0] = 0;
        if (colorData != null) {
            for (int column = 0; column < size.getWidth(); column++) {
                for (int row = 0; row < size.getHeight(); row++) {
                    colorData[column][row] = Color.COLOR_BLACK;
                }
            }
        }
        sendMessage(data);
    }

    @Override
    public void showIntro() {
        byte[] data = new byte[1];
        data[0] = 2;
        if (colorData != null) {
            for (int column = 0; column < size.getWidth(); column++) {
                for (int row = 0; row < size.getHeight(); row++) {
                    colorData[column][row] = Color.COLOR_BLACK;
                }
            }
        }
        sendMessage(data);
    }

    @Override
    public void render(RenderData data) {
        render(data, false);
    }


    private void render(RenderData data, boolean full) {

        int rows = size.getHeight();
        int columns = size.getWidth();
        int counter = 0;


        ByteBuffer buf = ByteBuffer.allocate(2048);
        buf.put((byte) 1);
        buf.put((byte) 0);
        buf.put((byte) 0);

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Color color = data.getFieldColor(new Position(column, row));
                if (full || !color.equals(colorData[column][row])) {
                    colorData[column][row] = color;

                    counter++;
                    buf.put(1, (byte) (byte) ((counter >> 8) & 0xff));
                    buf.put(2, (byte) (counter));

                    buf.put((byte) column);
                    buf.put((byte) row);
                    buf.put((byte) color.getRed());
                    buf.put((byte) color.getGreen());
                    buf.put((byte) color.getBlue());
                }
            }
        }

        LOG.trace("Send update for ::= [{}] leds to the Arduino device", counter);

        buf.position(0);                                  // reset the current position of the byte buffer to the first byte
        byte[] byteData = new byte[3 + counter * 5];      // create a new array to send it to the Arduino device
        buf.get(byteData, 0, 3 + counter * 5);  // copy the content of the byte buffer

        LOG.trace("Send {}", byteData);

        sendMessage(byteData);
    }

    public void sendMessage(byte[] data) {
        DatagramPacket packet = new DatagramPacket(data, data.length, deviceAddress, devicePort);
        try {
            socket.send(packet);
        } catch (Exception e) {
            LOG.warn("cannot send UDP message: ", e);
        }

    }

    @Override
    public Size getSize() {
        return size;
    }
}
