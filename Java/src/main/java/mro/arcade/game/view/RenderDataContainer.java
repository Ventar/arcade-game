package mro.arcade.game.view;

import mro.arcade.game.common.Color;
import mro.arcade.game.common.Position;

import java.util.ArrayList;
import java.util.List;

public class RenderDataContainer implements  RenderData {


    private List<RenderData> data = new ArrayList<>();

    public void addRenderData(RenderData data){

        this.data.add(data);
    }

    @Override
    public Color getFieldColor(Position position) {
       for (RenderData d: data){
             Color c = d.getFieldColor(position);

            if (!Color.COLOR_BLACK.equals(c)){
                return c;
            }
        }
        return Color.COLOR_BLACK;
    }
}
