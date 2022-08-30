package mro.arcade.game.view;

import mro.arcade.game.model.Gameboard;
import mro.arcade.game.model.Position;
import mro.arcade.game.model.Size;

import javax.swing.*;
import java.awt.*;

public class SwingRenderer implements BoardRenderer {

    private static int FIELD_SIZE_PX = 50;
    private JFrame frame = new JFrame();

    private RenderData model;

    public JFrame getFrame() {
        return frame;
    }

    public SwingRenderer(Size size) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setPreferredSize(new Dimension(size.getWidth() * (FIELD_SIZE_PX + 2), size.getHeight() * (FIELD_SIZE_PX + 2) + 25));
                frame.setContentPane(new PaintPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
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
    public void clear() {

    }

    private class PaintPane extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            int x, y = 0;
            for (int column = 0; column < model.getSize().getWidth(); column++) {
                for (int row = 0; row < model.getSize().getHeight(); row++) {
                    x = column * FIELD_SIZE_PX;
                    y = row * FIELD_SIZE_PX;

                    mro.arcade.game.model.Color color = mro.arcade.game.model.Color.COLOR_BLACK;
                    if (model != null) {
                        color = model.getFieldColor(new Position(column, model.getSize().getHeight() -1 - row));
                    }
                    g2d.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue()));
                    g2d.fillRect(x, y, FIELD_SIZE_PX, FIELD_SIZE_PX);
                }
            }

            g2d.dispose();

        }

    }

}
