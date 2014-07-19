package com.blundell.soundboard.engine;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public class SoundboardEngine {

    private final Activity activity;

    private SoundboardResourcer resourcer;
    private Bundle savedInstanceState;

    public SoundboardEngine(Activity activity) {
        this.activity = activity;
    }

    public SoundboardEngine withResourcer(SoundboardResourcer resourcer) {
        this.resourcer = resourcer;
        return this;
    }

    public SoundboardEngine restoreStateFrom(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        return this;
    }

    public void create() {
        if (resourcer == null) {
            throw new IllegalStateException("You need to always add a SoundboardResourcer. How else do you pick sounds!");
        }

        SoundboardActivityWatcher activityWatcher = new SoundboardActivityWatcher(resourcer);
        activityWatcher.onActivityCreated(activity, savedInstanceState);
        ((Application) activity.getApplicationContext()).registerActivityLifecycleCallbacks(activityWatcher);
    }
}
