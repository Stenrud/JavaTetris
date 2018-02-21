package Model.Tiles;

public class LTile extends Tile {

    public LTile() {

        byte i = GetColor();

        shape = new byte[][]{
                {0, i, 0},
                {0, i, 0},
                {i, i, 0}
        };
    }
}
