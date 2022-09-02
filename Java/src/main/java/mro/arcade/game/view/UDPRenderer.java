package mro.arcade.game.view;

import mro.arcade.game.model.Color;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPRenderer {
    private DatagramSocket socket;
    private boolean running;
    private InetAddress address;
    private byte[] buf = new byte[256];

    public void run() {


        byte[] data = new byte[6];
        data[0] = (byte) 1;
        data[1] = (byte) 6;


        running = true;

        while (running) {
            DatagramPacket packet
                    = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(buf, buf.length, address, port);
            String received
                    = new String(packet.getData(), 0, packet.getLength());

            if (received.equals("end")) {
                running = false;
                continue;
            }
            try {
                socket.send(packet);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        socket.close();
    }
}
