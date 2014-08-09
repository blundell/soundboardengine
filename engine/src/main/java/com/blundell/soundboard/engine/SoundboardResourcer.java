package com.blundell.soundboard.engine;

import com.blundell.soundboard.domain.Sound;
import com.blundell.soundboard.domain.Tab;
import com.blundell.soundboard.domain.Video;

import java.util.List;

public interface SoundboardResourcer {
    /**
     * @return a list of sounds you wish to be played
     */
    List<Sound> getSounds();

    /**
     * @return a list of videos you wish to link to
     */
    List<Video> getVideos();

    /**
     * @return The tabs you wish to show for the soundboard
     */
    List<Tab> getTabs();
}
