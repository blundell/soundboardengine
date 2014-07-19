package com.blundell.soundengine.demo;

import android.app.Activity;
import android.os.Bundle;

import com.blundell.soundboard.domain.Sound;
import com.blundell.soundboard.domain.Tab;
import com.blundell.soundboard.domain.Video;
import com.blundell.soundboard.engine.SoundboardEngine;
import com.blundell.soundboard.engine.SoundboardResourcer;

import java.util.ArrayList;
import java.util.List;

public class DemoActivity extends Activity {
    // If the soundclip doesn't play fully, shrink the filesize by lowering it's audio quality

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SoundboardResourcer resourcer = new SoundboardResourcer() {
            @Override
            public List<Sound> getSounds() {
                return ResourceFactory.getSounds();
            }

            @Override
            public List<Video> getVideos() {
                return ResourceFactory.getVideos();
            }

            @Override
            public List<Tab> getTabs() {
                List<Tab> tabs = new ArrayList<Tab>();
                tabs.add(new Tab(R.drawable.ic_tab_1, R.id.tab1Content));
                tabs.add(new Tab(R.drawable.ic_tab_2, R.id.tab2Content));
                tabs.add(new Tab(R.drawable.ic_tab_3, R.id.tab3Content));
                return tabs;
            }
        };
        new SoundboardEngine(this)
                .withResourcer(resourcer)
                .restoreStateFrom(savedInstanceState)
                .create();
    }
}
