package com.blundell.soundengine.demo;

import com.blundell.soundboard.domain.Sound;
import com.blundell.soundboard.domain.Video;

import java.util.ArrayList;
import java.util.List;

public class ResourceFactory {

    private static final List<Sound> SOUNDS = new ArrayList<Sound>();

    static {
        SOUNDS.add(new Sound(R.id.btn_sound1, R.raw.sound1, "Demo1"));
        SOUNDS.add(new Sound(R.id.btn_sound2, R.raw.sound2, "Demo2"));
        SOUNDS.add(new Sound(R.id.btn_sound3, R.raw.sound3, "Demo3"));
        SOUNDS.add(new Sound(R.id.btn_sound4, R.raw.sound4, "Demo4"));
        SOUNDS.add(new Sound(R.id.btn_sound5, R.raw.sound5, "Demo5"));
        SOUNDS.add(new Sound(R.id.btn_sound6, R.raw.sound6, "Demo6"));
        SOUNDS.add(new Sound(R.id.btn_sound7, R.raw.sound7, "Demo7"));
        SOUNDS.add(new Sound(R.id.btn_sound8, R.raw.sound8, "Demo8"));
        SOUNDS.add(new Sound(R.id.btn_sound9, R.raw.sound9, "Demo9"));
        SOUNDS.add(new Sound(R.id.btn_sound10, R.raw.sound10, "Demo10"));
        SOUNDS.add(new Sound(R.id.btn_sound11, R.raw.sound11, "Demo11"));
        SOUNDS.add(new Sound(R.id.btn_sound12, R.raw.sound12, "Demo12"));
        SOUNDS.add(new Sound(R.id.btn_sound13, R.raw.sound13, "Demo13"));
        SOUNDS.add(new Sound(R.id.btn_sound14, R.raw.sound14, "Demo14"));
        SOUNDS.add(new Sound(R.id.btn_sound15, R.raw.sound15, "Demo15"));
        SOUNDS.add(new Sound(R.id.btn_sound16, R.raw.sound16, "Demo16"));
        SOUNDS.add(new Sound(R.id.btn_sound17, R.raw.sound17, "Demo17"));
        SOUNDS.add(new Sound(R.id.btn_sound18, R.raw.sound18, "Demo18"));
        SOUNDS.add(new Sound(R.id.btn_sound19, R.raw.sound19, "Demo19"));
        SOUNDS.add(new Sound(R.id.btn_sound20, R.raw.sound20, "Demo20"));
        SOUNDS.add(new Sound(R.id.btn_sound21, R.raw.sound21, "Demo21"));
        SOUNDS.add(new Sound(R.id.btn_sound22, R.raw.sound22, "Demo22"));
        SOUNDS.add(new Sound(R.id.btn_sound23, R.raw.sound23, "Demo23"));
    }

    public static List<Sound> getSounds() {
        return SOUNDS;
    }

    private static final List<Video> VIDEOS = new ArrayList<Video>();

    static {
        VIDEOS.add(new Video(R.id.btn_video1, "http://www.youtube.com/watch?v=X3VNyhfPafI"));
        VIDEOS.add(new Video(R.id.btn_video2, "http://www.youtube.com/watch?v=Q7eKc7FxwOw"));
        VIDEOS.add(new Video(R.id.btn_video3, "http://www.youtube.com/watch?v=1houWKxjFtQ"));
        VIDEOS.add(new Video(R.id.btn_video4, "http://www.youtube.com/watch?v=ONXGT2RQv9c"));
        VIDEOS.add(new Video(R.id.btn_video5, "http://www.youtube.com/watch?v=9pxafvDolMc"));
        VIDEOS.add(new Video(R.id.btn_video6, "http://www.youtube.com/watch?v=GgCkk9zEnjs"));
        VIDEOS.add(new Video(R.id.btn_video7, "http://www.youtube.com/watch?v=ZNZvG7IJwLs"));
        VIDEOS.add(new Video(R.id.btn_video8, "http://www.youtube.com/watch?v=CSIoRM8HqgM"));
        VIDEOS.add(new Video(R.id.btn_video9, "http://www.youtube.com/watch?v=UMd4f6taWvM"));
    }

    public static List<Video> getVideos() {
        return VIDEOS;
    }
}
