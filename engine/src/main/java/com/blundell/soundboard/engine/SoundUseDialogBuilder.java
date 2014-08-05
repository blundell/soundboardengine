package com.blundell.soundboard.engine;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.blundell.soundboard.domain.Sound;
import com.blundell.soundboard.util.MediaUtils;

public class SoundUseDialogBuilder {
    private final Context context;
    private final MediaUtils mediaUtils;

    private AlertDialog currentDialog;

    // TODO move the on clicks back into the activity and add interfaces
    SoundUseDialogBuilder(Context context, MediaUtils mediaUtils) {
        this.context = context;
        this.mediaUtils = mediaUtils;
    }

    static SoundUseDialogBuilder newInstance(Context context) {
        return new SoundUseDialogBuilder(context, new MediaUtils(context));
    }

    public void promptForUseOfSound(final Sound sound) {
        currentDialog = new AlertDialog.Builder(context)
                .setTitle(R.string.sbe__save_dialog_title)
                .setMessage(R.string.sbe__save_dialog_msg)
                .setPositiveButton(R.string.sbe__save_dialog_button_yes_ringtone, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        mediaUtils.saveSoundAsRingtone(sound.getResourceId(), sound.getName());
                    }
                })
                .setNeutralButton(R.string.sbe__save_dialog_button_yes_notification, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        mediaUtils.saveSoundAsNotification(sound.getResourceId(), sound.getName());
                    }
                })
                .show();
    }

    public void dismissCurrentDialog() {
        if (currentDialog != null) {
            currentDialog.dismiss();
        }
    }
}
