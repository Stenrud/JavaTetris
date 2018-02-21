package Model.Tiles;

public class BoxTile extends Tile {

    public BoxTile(){

        byte i = GetColor();

        shape = new byte [][]{
                {i, i},
                {i, i}
        };
    }

}
