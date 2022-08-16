package mro.arcade.game.model;

public class TileField {
    private Position position;
    private boolean anchor;

    public TileField(Position position, boolean anchor) {
        this.position = position;
        this.anchor = anchor;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isAnchor() {
        return anchor;
    }

    public void setAnchor(boolean anchor) {
        this.anchor = anchor;
    }

    public boolean isAnchored(boolean anchor){
        if(anchor == true){
            return true;
        }else{
            return false;
        }
    }
}
