package mro.arcade.game.view.renderer;

import mro.arcade.game.model.Color;
import mro.arcade.game.model.Position;
import mro.arcade.game.view.BoardRenderer;
import mro.arcade.game.view.RenderData;
import mro.arcade.game.model.Size;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ArduinoHTTPRenderer implements BoardRenderer {

    private String ipAddress;

    private OkHttpClient client = new OkHttpClient();

    private Color[][] colorData;

    private Size size;

    public ArduinoHTTPRenderer(Size size, String ipAddress) {
        this.size = size;
        this.ipAddress = ipAddress;
        executeHttp("code=0");
    }

    @Override
    public void clear() {
        executeHttp("code=0");
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

        int rows = size.getHeight();
        int columns = size.getWidth();

        if (colorData == null) {
            colorData = new Color[columns][rows];
            clear();
        }

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Color color = data.getFieldColor(new Position(column, row));
                if (!color.equals(colorData[column][row])) {
                    executeHttp("code=1&leds=1&column=" + column + "&row=" + row + "&red=" + color.getRed() + "&green=" + color.getGreen() + "&blue=" + color.getBlue());
                    colorData[column][row] = color;
                }
            }
        }
    }


    private void executeHttp(String query) {

        Request request = new Request.Builder()
                .url("http://" + ipAddress + "/led?" + query)
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
