package mro.arcade.game.model;

public class BoardField {
    private Position boardPosition;
    private Color positionColor;

    public BoardField(Position boardPosition, Color positionColor) {
        this.boardPosition = boardPosition;
        this.positionColor = positionColor;
    }

    public Position getBoardPosition() {
        return boardPosition;
    }

    public void setBoardPosition(Position boardPosition) {
        this.boardPosition = boardPosition;
    }

    public Color getPositionColor() {
        return positionColor;
    }

    public void setPositionColor(Color positionColor) {
        this.positionColor = positionColor;
    }
}
