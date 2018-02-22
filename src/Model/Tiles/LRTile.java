package Model.Tiles;

public class LRTile extends Tile {

    public LRTile() {

        byte i = GetColor();

        shape = new byte[][]{
                {0, i, 0},
                {0, i, 0},
                {i, i, 0}
        };
    }
}
