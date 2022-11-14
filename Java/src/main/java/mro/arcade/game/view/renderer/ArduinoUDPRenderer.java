package mro.arcade.game.view.renderer;

import mro.arcade.game.DeviceDiscovery;
import mro.arcade.game.common.Color;
import mro.arcade.game.common.Position;
import mro.arcade.game.common.Size;
import mro.arcade.game.view.BoardRenderer;
import mro.arcade.game.view.RenderData;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

public class ArduinoUDPRenderer implements BoardRenderer {

    private static final Logger LOG = LoggerFactory.getLogger(ArduinoUDPRenderer.class);
    private InetAddress deviceAddress;

    private int devicePort;

    private DatagramSocket socket;

    private OkHttpClient client = new OkHttpClient();

    private Color[][] colorData;

    private Size size;

    public ArduinoUDPRenderer(Size size, String ipAddress) {
        try {

            DeviceDiscovery discovery = new DeviceDiscovery(InetAddress.getByName(ipAddress));
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
        byte[] data = new byte[512];
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
    public void render(RenderData data) {

        int rows = size.getHeight();
        int columns = size.getWidth();

        ByteBuffer buf = ByteBuffer.allocate(2048);
        buf.put((byte) 1);
        buf.put((byte) 0);

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Color color = data.getFieldColor(new Position(column, row));
                if (!color.equals(colorData[column][row])) {
                    colorData[column][row] = color;

                    int counter = Byte.toUnsignedInt(buf.get(1));
                    counter++;
                    buf.put(1, (byte) counter);

                    buf.put((byte) column);
                    buf.put((byte) row);
                    buf.put((byte) color.getRed());
                    buf.put((byte) color.getGreen());
                    buf.put((byte) color.getBlue());
                }
            }
        }
        sendMessage(buf.array());
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
