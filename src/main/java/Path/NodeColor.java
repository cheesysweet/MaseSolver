package Path;

import java.awt.*;

/**
 * This class purpose is to check whether a color of a coordinate is a wall, start, end or neither.
 * This is done se each node may know what it is.
 * @author André Timan & Anton Byström
 */

public class NodeColor {

    /**
     * This returns a boolean depending on if the integer pixel in the default RGB color model is within the given bounds.
     * @param color Rgb color.
     * @return True / false depending on the color of the pixel
     */

    public boolean wall(Color color) {
        return color.getBlue() < 170 && color.getRed() < 170 && color.getGreen() < 170;
    }

    /**
     * This returns a boolean depending on if the integer pixel in the default RGB color model is within the given bounds.
     * @param color Rgb color.
     * @return True / false depending on the color of the pixel
     */

    public boolean start(Color color) {
        return color.getBlue() < 100 && color.getBlue() > 50 && color.getRed() > 150 && color.getGreen() < 50;
    }

    /**
     * This returns a boolean depending on if the integer pixel in the default RGB color model is within the given bounds.
     * @param color Rgb color.
     * @return True / false depending on the color of the pixel
     */

    public boolean end(Color color) {
        return color.getBlue() > 150 && color.getRed() < 75 && color.getGreen() < 160 && color.getGreen() > 140;
    }

    /**
     * used to set the color to the path
     * @return green color
     */
    public Color pathColor() {
        return new Color(0, 204, 0);
    }



}
