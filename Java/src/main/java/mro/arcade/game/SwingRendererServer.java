package mro.arcade.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SwingRendererServer {
    private static final Logger LOG = LoggerFactory.getLogger(SwingRendererServer.class);
    private static int FIELD_SIZE_PX = 5;
    private JFrame frame = new JFrame();
    private Color[][] model;
    private DatagramSocket socket;
    private boolean running;
    private InetAddress address;
    private byte[] buf = new byte[512];

    public SwingRendererServer(InetAddress address) {
        this.address = address;
        model = new Color[24][24];
        clear();
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                System.out.println(info);
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setPreferredSize(new Dimension((24 * (FIELD_SIZE_PX + 1)) + 4, (24 * (FIELD_SIZE_PX + 1) + 28)));
                frame.setContentPane(new SwingRendererServer.PaintPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setVisible(true);
                frame.setTitle("Tetris");
            }
        });
    }

    public void run() throws IOException {
        running = true;
        socket = new DatagramSocket(4000, address);

        while (running) {
            LOG.debug("Wait for incoming packet...");
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            LOG.debug("Received packet: {}", packet);
            byte[] data = packet.getData();
            int commando = Byte.toUnsignedInt(data[0]);

            switch (commando) {
                case 0:
                    clear();
                    break;
                case 1:
                    int numberOfLED = Byte.toUnsignedInt(data[1]);
                    for (int i = 0; i < numberOfLED; i++) {
                        int column = Byte.toUnsignedInt(data[2 + i * 5]);
                        int row = Byte.toUnsignedInt(data[3 + i * 5]);
                        int colorRValue = Byte.toUnsignedInt(data[4 + i * 5]);
                        int colorGValue = Byte.toUnsignedInt(data[5 + i * 5]);
                        int colorBValue = Byte.toUnsignedInt(data[6 + i * 5]);
                        model[column][row] = new Color(colorRValue, colorGValue, colorBValue);
                    }
                    break;
                default:
                    LOG.warn("unknown commado received: {}", commando);
            }
            frame.pack();
            frame.repaint();
        }
        socket.close();
        System.exit(0);
    }

    public void clear() {
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 24; j++) {
                model[i][j] = Color.BLACK;
            }
        }
    }

    private class PaintPane extends JPanel {

        public PaintPane() {
            setOpaque(true);
            setBackground(new Color(192, 192, 192));
        }

        @Override
        protected void paintComponent(Graphics g) {
            try {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g.create();
                int x, y = 0;
                for (int column = 0; column < 24; column++) {
                    for (int row = 0; row < 24; row++) {
                        x = column * FIELD_SIZE_PX;
                        y = row * FIELD_SIZE_PX;

                        Color awtColor = model[column][24 - row - 1];

                        if (awtColor.equals(Color.BLACK)) {
                            awtColor = new Color(120, 140, 160);
                        }
                        g2d.setColor(awtColor);
                        g2d.fillRect(x + 2, y + 2, FIELD_SIZE_PX - 2, FIELD_SIZE_PX - 2);
                    }
                }

                g2d.dispose();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
