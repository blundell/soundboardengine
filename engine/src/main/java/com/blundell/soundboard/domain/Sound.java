package com.blundell.soundboard.domain;

public class Sound {
    private final int buttonId;
    private final int resourceId;
    private final String description;

    public Sound(int buttonId, int resourceId, String description) {
        this.buttonId = buttonId;
        this.resourceId = resourceId;
        this.description = description;
    }

    public int getButtonId() {
        return buttonId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public String getName() {
        return description;
    }
}
