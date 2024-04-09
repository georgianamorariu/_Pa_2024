package org.example.hom6;

import javafx.scene.paint.Color;

public enum StoneColor {
    RED(Color.RED),
    BLUE(Color.BLUE);

    private final Color color;

    StoneColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
