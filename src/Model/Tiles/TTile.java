package Model.Tiles;

public class TTile extends Tile {

    public TTile(){

        byte i = GetColor();

        shape = new byte [][]{
                {0, 0, 0},
                {i, i, i},
                {0, i, 0}
        };
    }
}
