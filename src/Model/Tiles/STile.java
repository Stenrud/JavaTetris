package Model.Tiles;

public class STile extends Tile {

    public STile(){

        byte i = GetColor();

        shape = new byte [][]{
                {i, 0, 0},
                {i, i, 0},
                {0, i, 0}
        };
    }
}
