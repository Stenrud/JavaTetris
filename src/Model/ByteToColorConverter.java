package Model;

import javafx.scene.paint.Color;

public class ByteToColorConverter {

    public static Color Convert(byte b) {
        switch(b){
            case 0:
                return Color.WHITE;
            case 1:
                return Color.YELLOW;

            case 2:
                return Color.GREEN;

            case 3:
                return Color.BLUE;

            case 4:
                return Color.RED;

            case 5:
                return Color.PURPLE;

            case 6:
                return Color.PINK;

            case 7:
                return Color.INDIGO;

            case 8:
                return Color.BLACK;

            case 9:
                return Color.GRAY;

            case 10:
                return Color.ORANGE;

            default:
                return Color.STEELBLUE;
        }
    }
}
