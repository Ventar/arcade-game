package mro.arcade.game.view;

import mro.arcade.game.model.Gameboard;

import javax.swing.*;
import java.awt.*;

public class GameBoardFrame {

    private static int FIELD_SIZE_PX;
    private JFrame frame = new JFrame();

    private Gameboard model;

    public JFrame getFrame() {
        return frame;
    }

    public GameBoardFrame(Gameboard model) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setPreferredSize(new Dimension(model.getSize().getWidth() * (FIELD_SIZE_PX + 2), model.getSize().getHeight() * (FIELD_SIZE_PX + 2) + 25));
                frame.setContentPane(new PaintPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.setTitle("Match 4");
            }
        });

        setModel(model);
    }

    public void setModel(Gameboard model) {

        this.model = model;
        if (model == null) {
            throw new IllegalArgumentException("The model cannot be null.");
        }
        frame.pack();
        frame.repaint();
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

                    g2d.setColor(Color.BLACK);
                    g2d.fillRect(x, y, FIELD_SIZE_PX, FIELD_SIZE_PX);

                    //g2d.setColor(model.getFields()[column][row]);
                    //g2d.fillArc(x + 10, y + 10, FIELD_SIZE_PX - 20, FIELD_SIZE_PX - 20, 0, 360);
                }
            }

            g2d.dispose();

        }

    }
}
