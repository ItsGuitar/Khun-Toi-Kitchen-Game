package constant;

public class ButtonStyles {
    private static final String HOVER_BUTTON_STYLE = "-fx-background-color: linear-gradient(to bottom, #FFB3BA, #FF69B4); " + // darker pink color
            "-fx-border-color: black; " + // black border
            "-fx-border-width: 2px; " + // border width
            "-fx-font-family: 'Gabriola'; " + // font family
            "-fx-font-size: 33px; " + // increased text size
            "-fx-text-fill: black; " + // black text color
            "-fx-background-radius: 15; " + // corner radii
            "-fx-border-radius: 15;"; // border radii

    private static final String NORMAL_BUTTON_STYLE = "-fx-background-color: linear-gradient(to bottom, white, #FFB3BA); " + // ultra light pink color
            "-fx-border-color: black; " + // black border
            "-fx-border-width: 2px; " + // border width
            "-fx-font-family: 'Gabriola'; " + // font family
            "-fx-font-size: 30px; " + // text size
            "-fx-text-fill: black; " + // black text color
            "-fx-background-radius: 15; " + // corner radii
            "-fx-border-radius: 15;"; // border radii

    public static String getHoverButtonStyle() {
        return HOVER_BUTTON_STYLE;
    }
    public static String getNormalButtonStyle() {
        return NORMAL_BUTTON_STYLE;
    }
}