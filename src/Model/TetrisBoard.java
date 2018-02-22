package Model;

import Model.Tiles.Tile;
import javafx.geometry.Point2D;

public class TetrisBoard {

    private final GameMagic magic;
    private byte[][] board;

    public TetrisBoard(GameMagic magic) {

        this.magic = magic;

        board = new byte[magic.GetWidth()][magic.GetWidth() * 2];
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = 0;
            }
        }
    }

    public void RenderWIthTile(Tile tile) {
        DrawBoard();
        DrawTile(tile);
        magic.drawGrid();
    }

    public void FixTile(Tile tile) {

        for(int i = 0; i < tile.getWidth(); i++){
            for(int j = 0; j < tile.getHeight(); j++){

                int x = i + (int) tile.position.getX(), y = j + (int) tile.position.getY();
                byte newValue = tile.getPosition(i, j);

                if(newValue != 0)
                    board[x][y] = newValue;
            }
        }
    }

    public void DrawBoard(){
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                magic.DrawCell(i, j, ByteToColorConverter.Convert(board[i][j]));
            }
        }
    }

    public void DrawTile(Tile tile) {
        for(int i = 0; i < tile.getWidth(); i++){
            for(int j = 0; j < tile.getHeight(); j++){

                int x = i + (int) tile.position.getX(), y = j + (int) tile.position.getY();

                byte value = tile.getPosition(i, j);

                if(value != 0)
                    magic.DrawCell(x, y, ByteToColorConverter.Convert(value));
            }
        }
    }

    public boolean CheckMove(Tile tile, Point2D movement) {

        Point2D position = tile.position.add(movement);

        for(int i = 0; i < tile.getWidth(); i++){
            for(int j = 0; j < tile.getHeight(); j++){

                int x = i + (int)position.getX(), y = j + (int)position.getY();

                byte tileValue = tile.getPosition(i, j);

                if(tileValue != 0) {
                    if (x < 0 || y < 0 || y >= magic.GetWidth() * 2 || x >= magic.GetWidth())
                        return false;
                    if (tileValue > 0 && board[x][y] > 0)
                        return false;
                }
            }
        }

        return true;
    }

    public Point2D getStartPosition(Tile mainTile) {
        return new Point2D(magic.GetWidth()/2 - mainTile.getWidth()/2, 0);
    }

    public boolean checkRotation(Tile tile) {
        for (int i = 0; i < tile.getHeight(); i++) {
            for (int j = 0; j < tile.getWidth(); j++) {

                byte tileValue = tile.getPosition(j, i);
                int x = i + (int)tile.position.getX(), y = j + (int)tile.position.getY();

                if(tileValue != 0){

                    if (x < 0 || y < 0 || y >= magic.GetWidth() * 2 || x >= magic.GetWidth())
                        return false;
                    if (board[x][y] > 0)
                        return false;
                }
            }
        }
        return true;
    }

    public void previewTile(Tile tile) {

        magic.clearPreview();
        for (int i = 0; i < tile.getWidth(); i++) {
            for (int j = 0; j < tile.getHeight(); j++) {
                byte value = tile.getPosition(i, j);
                if(value != 0)
                    magic.DrawPreviewCell(i,j, ByteToColorConverter.Convert(value));
            }

        }
    }

    public void checkForCompleteRows(int start, int stop) {
        for (int i = start; i < stop; i++) {
            if(RowIsComplete(i))
                RemoveRow(i);
        }
    }

    private void RemoveRow(int row) {
        for (int i = 0; i < magic.GetWidth(); i++) {
            for (int j = row; j >=0 ; j--) {
                if(j > 0)
                    board[i][j] = board[i][j - 1];
                else
                    board[i][j] = 0;
            }
        }
    }

    private boolean RowIsComplete(int row) {
        for (int i = 0; i < magic.GetWidth(); i++) {
            if(board[i][row] == 0)
                return false;
        }
        return true;
    }

    public int getHeight() {
        return magic.GetWidth()*2;
    }
}