package Model.Tiles;

public class ITile extends Tile{

    public ITile(){

        byte i = GetColor();

        shape = new byte [][]{
                {0, i, 0},
                {0, i, 0},
                {0, i, 0},
                {0, i, 0}
        };

    }
}
