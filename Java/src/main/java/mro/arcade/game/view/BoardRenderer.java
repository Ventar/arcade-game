package mro.arcade.game.view;

import mro.arcade.game.common.Size;

/**
 * BoardRenderer Interface
 *
 * @author Noel Masur, Leon Federau
 * @since 12.08.2022
 * <p>
 * Render the board by the given datas
 */
public interface BoardRenderer {

    void render(RenderData data);

    void clear();

    void showIntro();

    Size getSize();
}
