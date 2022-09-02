package mro.arcade.game.view;

import mro.arcade.game.model.Color;
import mro.arcade.game.model.Position;
import mro.arcade.game.model.Size;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;

public class ArduinoUDPRenderer implements BoardRenderer {

    private String ipAddress;

    private int port;

    private Size size;

    private Color[][] colorData;

    private DatagramSocket socket;

    public ArduinoUDPRenderer(Size size, String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.size = size;
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clear() {
        sendUDP(new byte[]{0});
        if (colorData != null) {
            for (int row = 0; row < colorData[0].length; row++) {
                for (int column = 0; column < colorData.length; column++) {
                    colorData[column][row] = Color.COLOR_BLACK;
                }
            }
        }
    }

    @Override
    public void render(RenderData data) {

        int rows = data.getSize().getHeight();
        int columns = data.getSize().getWidth();

        if (colorData == null) {
            colorData = new Color[columns][rows];
            clear();
        }

        int ledCount = 0;
        ByteBuffer buf = ByteBuffer.allocate(512);
        buf.put((byte) 1); // set LED command
        buf.put((byte) 0); // number of LED placeholder

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Color color = data.getFieldColor(new Position(column, row));
                if (!color.equals(colorData[column][row])) {
                    buf.put((byte) column).put((byte) row).put((byte) color.getRed()).put((byte) color.getGreen()).put((byte) color.getBlue());
                    colorData[column][row] = color;
                    ledCount++;
                }
            }
        }

        buf.position(1);
        buf.put((byte) ledCount); // hopefully less than 255 ;)
        sendUDP(buf.array());
    }


    private void sendUDP(byte[] data) {
        try {
            DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName(ipAddress), 4000);
            socket.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
