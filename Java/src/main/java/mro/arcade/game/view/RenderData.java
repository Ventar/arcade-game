package mro.arcade.game.view;

import mro.arcade.game.model.Color;
import mro.arcade.game.model.Position;

/**
 * RenderData interface
 *
 * @author Noel Masur, Leon Federau
 * @since 12.08.2022
 */
public interface RenderData {

    /**
     * Get color from specific position.
     *
     * @param position the position to fetch the color. If no color is set at that position {@link Color#COLOR_BLACK} is returned
     *
     * @return the color or black if no color was set.
     */
    Color getFieldColor(Position position);

}
