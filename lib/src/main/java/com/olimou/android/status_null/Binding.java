package com.olimou.android.status_null;

import android.databinding.BindingAdapter;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by emersonmoura on 10/10/17.
 */

public class Binding {
    @BindingAdapter({"bind:SN_title"})
    public static void setTitle(final StatusNull statusNull, final String title) {
        Log.d(TAG, "setHtml() called with: statusNull = [" + statusNull + "], title = [" + title + "]");
        statusNull.setTitle(title);
    }

    @BindingAdapter({"bind:SN_content"})
    public static void setContent(final StatusNull statusNull, final String message) {
        Log.d(TAG, "setContent() called with: statusNull = [" + statusNull + "], message = [" + message + "]");
        statusNull.setContent(message);
    }

    @BindingAdapter({"bind:SN_button_text"})
    public static void setButtonText(final StatusNull statusNull, final String buttonText) {
        statusNull.setButtonText(buttonText);
    }
}
