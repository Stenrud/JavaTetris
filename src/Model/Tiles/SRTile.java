package Model.Tiles;

public class SRTile extends Tile {

    public SRTile(){

        byte i = GetColor();

        shape = new byte [][]{
                {i, i, 0},
                {0, i, i},
                {0, 0, 0}
        };
    }
}
