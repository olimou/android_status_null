package com.olimou.android.status_null;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.text.Html;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class StatusNull extends FrameLayout {

    public static final String TAG = StatusNull.class.getSimpleName();
    private int mButtonTextColor;
    private AppCompatButton mCompatButton;
    private View mIconBackground;
    private AppCompatImageView mImgIcon;
    private int mMessageColor;
    private int mTitleColor;
    private TextView mTxtContent;
    private TextView mTxtTitle;

    public StatusNull(Context context) {
        super(context);
        init(null, 0);
    }

    public StatusNull(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public StatusNull(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    public AppCompatButton getButton() {
        return mCompatButton;
    }

    private void init(AttributeSet _attrs, int _defStyle) {
        inflate(getContext(), R.layout.component_status_null, this);

        mIconBackground = findViewById(R.id.frame_background);
        mImgIcon = findViewById(R.id.img_icon);
        mTxtTitle = findViewById(R.id.txt_title);
        mTxtContent = findViewById(R.id.txt_content);

        Drawable lDrawableBackground = ContextCompat.
                getDrawable(getContext(), R.drawable.background_circle_grey);

        DrawableCompat.setTint(lDrawableBackground, ContextCompat.getColor(getContext(), R.color.status_null_default_circle_color));

        mCompatButton = findViewById(R.id.btn);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mIconBackground.setBackground(lDrawableBackground);
        } else {
            mIconBackground.setBackgroundDrawable(lDrawableBackground);
        }

        if (_attrs != null) {
            TypedArray lTypedArray = getContext().getTheme().obtainStyledAttributes(_attrs, R.styleable.StatusNull, 0, 0);

            int lCircleColor = lTypedArray.getColor(R.styleable.StatusNull_SN_circle_color, ContextCompat.getColor(getContext(), R.color.status_null_default_circle_color));

            DrawableCompat.setTint(DrawableCompat.wrap(lDrawableBackground).mutate(), lCircleColor);

            float lCircleDimension = lTypedArray.getDimension(R.styleable.StatusNull_SN_circle_size, getContext().getResources().getDimension(R.dimen.status_null_circle_background_size));

            mIconBackground.getLayoutParams().height = (int) lCircleDimension;
            mIconBackground.getLayoutParams().width = (int) lCircleDimension;

            Drawable lDrawableIcon;

            int lIconColor = lTypedArray.getColor(R.styleable.StatusNull_SN_icon_color, 0);

            int lIconRes = lTypedArray.getResourceId(R.styleable.StatusNull_SN_icon, -1);

            if (lIconRes != 1) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    lDrawableIcon = lTypedArray.getDrawable(R.styleable.StatusNull_SN_icon);

                    if (lDrawableIcon != null) {

                        if (lIconColor != 0) {
                            lDrawableIcon.setTint(lIconColor);
                        }

                        mImgIcon.setImageDrawable(lDrawableIcon);
                    }
                } else {
                    lDrawableIcon = AppCompatResources.getDrawable(getContext(), lIconRes);

                    if (lDrawableIcon != null) {
                        lDrawableIcon = DrawableCompat.wrap(lDrawableIcon).mutate();

                        if (lIconColor != 0) {
                            DrawableCompat.setTint(lDrawableIcon, lIconColor);
                        }

                        mImgIcon.setImageDrawable(lDrawableIcon);
                    }
                }
            }

            float lIconDimension = lTypedArray.getDimension(R.styleable.StatusNull_SN_icon_size, getContext().getResources().getDimension(R.dimen.status_null_icon_size));

            mImgIcon.getLayoutParams().height = (int) lIconDimension;
            mImgIcon.getLayoutParams().width = (int) lIconDimension;

            mTitleColor = lTypedArray.getColor(R.styleable.StatusNull_SN_title_color, ContextCompat.getColor(getContext(), R.color.status_null_text_disable_light));

            String lTitleText = lTypedArray.getString(R.styleable.StatusNull_SN_title);

            setTitle(lTitleText);

            mMessageColor = lTypedArray.getColor(R.styleable.StatusNull_SN_content_color, ContextCompat.getColor(getContext(), R.color.status_null_text_disable_light));

            CharSequence lMessageText = lTypedArray.getText(R.styleable.StatusNull_SN_content);

            setContent(lMessageText);

            mButtonTextColor = lTypedArray.getColor(R.styleable.StatusNull_SN_button_text_color, 0);

            String lButtonText = lTypedArray.getString(R.styleable.StatusNull_SN_button_text);

            setButtonText(lButtonText);
        }
    }

    public void setButtonText(String buttonText) {
        if (buttonText != null) {

            if (!isInEditMode()) {
                mCompatButton.setText(Html.fromHtml(buttonText));
            }else{
                mCompatButton.setText(buttonText);
            }

            if (mButtonTextColor != 0) {
                mCompatButton.setTextColor(mButtonTextColor);
            }
            mCompatButton.setVisibility(VISIBLE);
        } else {
            mCompatButton.setVisibility(GONE);
        }
    }

    public void setCircleColor(@ColorRes int _color) {
        Drawable lBackground = mIconBackground.getBackground();

        DrawableCompat.setTint(lBackground, ContextCompat.getColor(getContext(), _color));
    }

    private void setContent(CharSequence messageText) {
        if (messageText != null) {
            setContent(messageText.toString());
        } else {
            setContent(null);
        }
    }

    public void setContent(String _message) {
        if (_message != null) {
            SpannableString lContent = new SpannableString(_message);

            if (!isInEditMode()) {
                mTxtContent.setText(Html.fromHtml(_message));
            } else {
                mTxtContent.setText(lContent);
            }

            mTxtContent.setTextColor(mMessageColor);

            mTxtContent.setVisibility(VISIBLE);
        } else {
            mTxtContent.setVisibility(GONE);
        }
    }

    public void setIcon(@DrawableRes int _resource) {
        Drawable lDrawable = ContextCompat.getDrawable(getContext(), _resource);

        mImgIcon.setImageDrawable(lDrawable);
    }

    public void setIconColor(@ColorRes int _color) {
        Drawable lDrawable = mImgIcon.getDrawable();

        DrawableCompat.setTint(lDrawable, ContextCompat.getColor(getContext(), _color));

    }

    public void setTitle(String _title) {
        if (_title != null) {

            if(!isInEditMode()){
                mTxtTitle.setText(Html.fromHtml(_title));
            }else{
                mTxtTitle.setText(_title);
            }

            mTxtTitle.setTextColor(mTitleColor);

            mTxtTitle.setVisibility(VISIBLE);
        } else {
            mTxtTitle.setVisibility(GONE);
        }
    }
}
