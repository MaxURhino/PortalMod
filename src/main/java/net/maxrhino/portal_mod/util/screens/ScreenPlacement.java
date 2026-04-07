package net.maxrhino.portal_mod.util.screens;

public class ScreenPlacement {
    private final int screenWidth, screenHeight, objectWidth, objectHeight;

    public ScreenPlacement(int screenWidth, int screenHeight, int objectWidth, int objectHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.objectWidth = objectWidth;
        this.objectHeight = objectHeight;
    }

    public int getX(Placement placement) {
        return switch (placement) {
            case LEFT -> 0;
            case CENTER -> (screenWidth / 2) - (objectWidth / 2);
            case RIGHT -> screenWidth - objectWidth;
        };
    }

    public int getY(Placement placement) {
        return switch (placement) {
            case LEFT -> 0;
            case CENTER -> (screenHeight / 2) - (objectHeight / 2);
            case RIGHT -> screenHeight - objectHeight;
        };
    }

    public enum Placement {
        LEFT,
        CENTER,
        RIGHT;
    }
}
