package mro.arcade.game.view.renderer;

import mro.arcade.game.common.Position;
import mro.arcade.game.common.Size;
import mro.arcade.game.view.BoardRenderer;
import mro.arcade.game.view.RenderData;

import javax.swing.*;
import java.awt.*;

public class SwingRenderer implements BoardRenderer {

    private static int FIELD_SIZE_PX = 32;
    private JFrame frame = new JFrame();

    private Size size;
    private RenderData model;

    public JFrame getFrame() {
        return frame;
    }

    public SwingRenderer(Size size) {
        this.size = size;

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
                frame.setPreferredSize(new Dimension((size.getWidth() * (FIELD_SIZE_PX + 1)) + 4, (size.getHeight() * (FIELD_SIZE_PX + 1) + 28)));
                frame.setContentPane(new PaintPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setVisible(true);
                frame.setTitle("Tetris");
            }
        });

    }

    @Override
    public void render(RenderData data) {
        this.model = data;
        if (model == null) {
            throw new IllegalArgumentException("The model cannot be null.");
        }
        frame.pack();
        frame.repaint();
    }

    @Override
    public void clear() {}

    @Override
    public void showIntro() {

    }

    @Override
    public Size getSize() {
        return size;
    }

    private class PaintPane extends JPanel {

        public PaintPane() {
            setOpaque(true);
            setBackground(new Color(192, 192, 192));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g.create();
            int x, y = 0;
            for (int column = 0; column < size.getWidth(); column++) {
                for (int row = 0; row < size.getHeight(); row++) {
                    x = column * FIELD_SIZE_PX;
                    y = row * FIELD_SIZE_PX;

                    mro.arcade.game.common.Color color = mro.arcade.game.common.Color.COLOR_BLACK;
                    if (model != null) {
                        color = model.getFieldColor(new Position(column, size.getHeight() - 1 - row));
                    }

                    Color awtColor = null;

                    if (mro.arcade.game.common.Color.COLOR_BLACK.equals(color)) {
                        awtColor = new Color(120, 140, 160);
                    } else {
                        awtColor = convertToAwtColor(color);
                    }

                    g2d.setColor(awtColor);
                    g2d.fillRect(x + 2, y + 2, FIELD_SIZE_PX - 2, FIELD_SIZE_PX - 2);

                }
            }

            g2d.dispose();

        }

        public Color convertToAwtColor(mro.arcade.game.common.Color ourColor) {
            return new Color(ourColor.getRed(), ourColor.getGreen(), ourColor.getBlue());
        }
    }


}
