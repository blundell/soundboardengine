package com.blundell.soundboard.util;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MediaUtils {

    private final Context context;

    public MediaUtils(Context context) {
        this.context = context;
    }

    public void saveSoundAsRingtone(int soundId, String name) {
        saveSound(soundId, name, true);
    }

    public void saveSoundAsNotification(int soundId, String name) {
        saveSound(soundId, name, false);
    }

    // TODO Toast's should not be in this class
    private void saveSound(int soundId, String name, boolean asRingtone) {
        byte[] buffer = null;
        InputStream fIn = context.getResources().openRawResource(soundId);
        int size = 0;

        try {
            size = fIn.available();
            buffer = new byte[size];
            fIn.read(buffer);
            fIn.close();
        } catch (IOException e) {
            Toast.makeText(context, "1 Error reading from phone memory.", Toast.LENGTH_LONG).show();
            return;
        }

        String sdDir = Environment.getExternalStorageDirectory().toString();
        String path = sdDir;
        if (asRingtone) {
            path += "/media/audio/ringtones/";
        } else {
            path += "/media/audio/notifications/";
        }
        String filename = name + ".ogg";

        boolean exists = (new File(path)).exists();
        if (!exists) {
            new File(path).mkdirs();
        }

        FileOutputStream save;
        try {
            save = new FileOutputStream(path + filename);
            save.write(buffer);
            save.flush();
            save.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(context, "File not found error.", Toast.LENGTH_LONG).show();
            return;
        } catch (IOException e) {
            Toast.makeText(context, "2 Error writing to phone memory.", Toast.LENGTH_LONG).show();
            return;
        }

        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path + filename)));

        File k = new File(path, filename);

        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DATA, k.getAbsolutePath());
        values.put(MediaStore.MediaColumns.TITLE, name);
        values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/ogg");
        values.put(MediaStore.Audio.Media.ARTIST, "JK ");
        values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
        values.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);
        values.put(MediaStore.Audio.Media.IS_ALARM, true);
        values.put(MediaStore.Audio.Media.IS_MUSIC, false);

        //Insert it into the database
        context.getContentResolver().insert(MediaStore.Audio.Media.getContentUriForPath(k.getAbsolutePath()), values);

        // done message
        if (asRingtone) {
            Toast.makeText(context, "Ringtone saved", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Notification saved", Toast.LENGTH_LONG).show();
        }
    }

}
