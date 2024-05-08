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

    private static final String KITCHEN_BUTTON_STYLE = "-fx-background-color: #041d5e; " +
            "-fx-text-fill: white; " +
            "-fx-font-family: 'Gabriola'; " +
            "-fx-font-size: 30px;";
    private static final String KITCHEN_BUTTON_HOVER_STYLE = "-fx-background-color: #0a40d6; " +
            "-fx-text-fill: white; " +
            "-fx-font-family: 'Gabriola'; " +
            "-fx-font-size: 33px;";

    public static String getHoverButtonStyle() {
        return HOVER_BUTTON_STYLE;
    }
    public static String getNormalButtonStyle() {
        return NORMAL_BUTTON_STYLE;
    }
    public static String getKitchenButtonStyle() {
        return KITCHEN_BUTTON_STYLE;
    }
    public static String getKitchenButtonHoverStyle() {
        return KITCHEN_BUTTON_HOVER_STYLE;
    }

}