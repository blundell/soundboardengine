package com.blundell.soundboard.domain;

public class Tab {
    private final int iconId;
    private final int layoutId;

    public Tab(int iconId, int layoutId) {
        this.iconId = iconId;
        this.layoutId = layoutId;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public int getIconId() {
        return iconId;
    }

}
