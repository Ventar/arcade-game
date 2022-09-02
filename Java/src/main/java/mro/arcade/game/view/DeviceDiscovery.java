package mro.arcade.game.view;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class DeviceDiscovery {


    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();

        byte[] data = new byte[12];
        data[0] = (byte) 1;      // command
        data[1] = (byte) 2;      // number of pixels to set

        data[2] = (byte) 0;      // column
        data[3] = (byte) 0;      // row
        data[4] = (byte) 255;
        data[5] = (byte) 0;
        data[6] = (byte) 255;

        data[7] = (byte) 0;     // column
        data[8] = (byte) 1;     // row
        data[9] = (byte) 255;   // red
        data[10] = (byte) 128;    // green
        data[11] = (byte) 64;    // blue


        DatagramPacket packet = new DatagramPacket(data,data.length, InetAddress.getByName("192.168.2.138"), 4000);
        socket.send(packet);
    }
}
