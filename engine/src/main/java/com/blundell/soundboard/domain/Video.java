package com.blundell.soundboard.domain;

import android.net.Uri;

public class Video {
    private final int buttonId;
    private final String resourceId;

    public Video(int buttonId, String resourceId) {
        this.buttonId = buttonId;
        this.resourceId = resourceId;
    }

    public int getButtonId() {
        return buttonId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public Uri getResourceUri() {
        return Uri.parse(resourceId);
    }
}
