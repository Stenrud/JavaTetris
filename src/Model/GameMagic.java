package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameMagic {

    private final GraphicsContext gc;
    private final GraphicsContext pgc;

    private final int width = 10;

    private final double cellSize = 400.0 / width;

    public GameMagic(GraphicsContext gc, GraphicsContext pgc) {
        this.gc = gc;
        this.pgc = pgc;
    }

    public void DrawCell(int x, int y, Color color) {
        gc.setFill(color);
        gc.fillRect(x*cellSize, y*cellSize, cellSize, cellSize);
    }

    public void drawGrid(){
        for(int i = 0; i <= width; i++){
            gc.strokeLine(i * cellSize, 0, i * cellSize, 800);
        }
        for(int  i= 0; i <= width*2; i++){
            gc.strokeLine( 0,i * cellSize,  600,i * cellSize);        }
    }

    public int GetWidth() {
        return width;
    }
}
