package com.blundell.soundboard.engine;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TabHost;

import com.blundell.soundboard.domain.Sound;
import com.blundell.soundboard.domain.Tab;
import com.blundell.soundboard.domain.Video;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

class SoundboardActivityWatcher implements Application.ActivityLifecycleCallbacks {

    private final SoundboardResourcer resourcer;

    private AdView adView;
    private SoundUseDialogBuilder dialogBuilder;
    private TabHost mTabHost;

    SoundboardActivityWatcher(SoundboardResourcer resourcer) {
        this.resourcer = resourcer;
    }

    @Override
    public void onActivityCreated(final Activity activity, Bundle savedInstanceState) {
        dialogBuilder = SoundUseDialogBuilder.newInstance(activity);

        mTabHost = (TabHost) activity.findViewById(android.R.id.tabhost);
        mTabHost.setup();

        Resources resources = activity.getResources();
        List<Tab> tabs = resourcer.getTabs();
        for (Tab tab : tabs) {
            Drawable icon = resources.getDrawable(tab.getIconId());
            int layout = tab.getLayoutId();
            String name = "tab_name" + layout;
            mTabHost.addTab(mTabHost.newTabSpec(name).setIndicator("", icon).setContent(layout));
        }
        if (savedInstanceState == null) {
            mTabHost.setCurrentTab(0);
        } else {
            mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
        }

        final List<Sound> sounds = resourcer.getSounds();
        final List<Video> videos = resourcer.getVideos();
        final ProgressDialog gettingWebPageDialog = ProgressDialog.show(activity, "", "Loading Sound Clips", true);
        new Thread() {
            public void run() {
                SoundManager.initSounds(activity.getApplicationContext());

                for (Sound sound : sounds) {
                    SoundManager.addSound(sound.getResourceId(), sound.getResourceId());
                }

                gettingWebPageDialog.dismiss();
            }
        }.start();

        for (final Sound sound : sounds) {
            Button soundButton = (Button) activity.findViewById(sound.getButtonId());
            soundButton.setTextColor(Color.WHITE);
            soundButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    SoundManager.playSound(sound.getResourceId());
                }
            });
            soundButton.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    dialogBuilder.dismissCurrentDialog();
                    dialogBuilder.promptForUseOfSound(sound);
                    return true;
                }
            });
        }

        for (final Video video : videos) {
            Button videoButton = (Button) activity.findViewById(video.getButtonId());
            videoButton.setTextColor(Color.WHITE);
            videoButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    activity.startActivity(new Intent(Intent.ACTION_VIEW, video.getResourceUri()));
                }
            });
        }

        ViewGroup contentView = (ViewGroup) activity.findViewById(android.R.id.content);
        adView = new AdView(activity);
        adView.setLayoutParams(new FrameLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT, Gravity.BOTTOM));
        adView.setAdUnitId(activity.getString(R.string.sbe__ad_unit_id));
        adView.setAdSize(AdSize.SMART_BANNER);
        contentView.addView(adView);

        loadAdvert();
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                loadAdvert();
            }
        });
    }

    private void loadAdvert() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        adView.resume();
    }

    @Override
    public void onActivityPaused(Activity activity) {
        dialogBuilder.dismissCurrentDialog();
        adView.pause();
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        outState.putString("tab", mTabHost.getCurrentTabTag());
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        ((Application) activity.getApplicationContext()).unregisterActivityLifecycleCallbacks(this);
        SoundManager.finish();
        adView.destroy();
    }
}
