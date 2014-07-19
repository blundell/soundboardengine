package com.blundell.soundboard.engine;

import com.blundell.soundboard.domain.Sound;
import com.blundell.soundboard.domain.Tab;
import com.blundell.soundboard.domain.Video;

import java.util.List;

public interface SoundboardResourcer {
    List<Sound> getSounds();

    List<Video> getVideos();

    List<Tab> getTabs();
}
