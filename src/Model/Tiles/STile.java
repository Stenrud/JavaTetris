package Model.Tiles;

public class STile extends Tile {

    public STile(){

        byte i = GetColor();

        shape = new byte [][]{
                {0, i, i},
                {i, i, 0},
                {0, 0, 0}
        };
    }
}
