package com.blundell.soundboard.engine;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.SparseIntArray;

class SoundManager {

    private static MediaPlayer mMediaPlayer;
    private static SparseIntArray mSoundPoolMap;
    private static Context mContext;

    public static void initSounds(Context theContext) {
        mContext = theContext;
        mSoundPoolMap = new SparseIntArray();
    }

    public static void addSound(final int index, final int soundID) {
        mSoundPoolMap.put(index, soundID);
    }

    public static void playSound(final int index) {
        try {
            mMediaPlayer.stop();
            mMediaPlayer.release();
        } catch (Exception e1) {
            // null first time round
        }
        try {
            mMediaPlayer.reset();
        } catch (Exception e1) {
            // null first time round
        }
        mMediaPlayer = MediaPlayer.create(mContext, mSoundPoolMap.get(index));

        try {
            mMediaPlayer.prepare();
        } catch (Exception e) {
            // rules are rules
        }
        try {
            mMediaPlayer.start();
        } catch (IllegalStateException e) {
            //everyone else is doing it
        }
    }

    public static void finish() {
        try {
            mMediaPlayer.release();
        } catch (Exception ignore) {
        }
    }
}
