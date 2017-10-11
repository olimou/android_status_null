package com.olimou.android.content;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;

import com.olimou.android.BR;

/**
 * Created by emersonmoura on 10/10/17.
 */

public class ContentModel extends BaseObservable {
    private static final String TAG = ContentModel.class.getSimpleName();
    @Bindable
    String button = "Action <b>bold</b>";
    @Bindable
    String content = "Status <b>bold</b>";
    @Bindable
    String title = "Title <b>bold</b>";

    public void buttonToggle(View view) {
        Log.d(TAG, "buttonToggle: " + button);

        if (button == null) {
            button = "Action <b>bold</b>";
        } else {
            button = null;
        }

        notifyPropertyChanged(BR.button);
    }

    public void contentToggle(View view) {
        Log.d(TAG, "titleToggle() called with: view = [" + view + "]");

        if (content == null) {
            content = "Content <b>bold</b>";
        } else {
            content = null;
        }

        notifyPropertyChanged(BR.content);
    }

    public String getButton() {
        return button;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public void titleToggle(View view) {
        Log.d(TAG, "titleToggle() called with: view = [" + view + "]");

        if (title == null) {
            title = "Title <b>Bold</b>";
        } else {
            title = null;
        }

        notifyPropertyChanged(BR.title);
    }
}
