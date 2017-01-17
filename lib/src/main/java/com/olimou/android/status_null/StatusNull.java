package com.olimou.android.status_null;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.text.Html;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class StatusNull extends FrameLayout {

	public static final String TAG = StatusNull.class.getSimpleName();
	private AppCompatButton    mCompatButton;
	private View               mIconBackground;
	private AppCompatImageView mImgIcon;
	private TextView           mTxtContent;
	private TextView           mTxtTitle;
	private TypedArray         mTypedArray;

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
		View lView = inflate(getContext(), R.layout.component_status_null, this);

		mIconBackground = findViewById(R.id.frame_background);
		mImgIcon = (AppCompatImageView) findViewById(R.id.img_icon);
		mTxtTitle = (TextView) findViewById(R.id.txt_title);
		mTxtContent = (TextView) findViewById(R.id.txt_content);

		mTypedArray = getContext().getTheme()
				.obtainStyledAttributes(_attrs, R.styleable.StatusNull, 0, 0);

		int lCircleColor = mTypedArray.getColor(R.styleable.StatusNull_circleColor,
				getContext().getResources().getColor(R.color.default_circle_color));

		Drawable lDrawableBackground = getContext().getResources()
				.getDrawable(R.drawable.background_circle_grey);

		DrawableCompat.setTint(lDrawableBackground, lCircleColor);

		mIconBackground.setBackgroundDrawable(lDrawableBackground);

		float lDimension = mTypedArray.getDimension(R.styleable.StatusNull_circleSize, 0);

		if (lDimension == 0) {
			lDimension = getContext().getResources().getDimension(R.dimen.circle_background_size);
		}

		mIconBackground.getLayoutParams().height = (int) lDimension;
		mIconBackground.getLayoutParams().width = (int) lDimension;

		Drawable lDrawableIcon = mTypedArray.getDrawable(R.styleable.StatusNull_icon);

		float lIconDimension = mTypedArray.getDimension(R.styleable.StatusNull_iconSize, 0);

		if (lIconDimension == 0) {
			lIconDimension = getContext().getResources().getDimension(R.dimen.icon_size);
		}

		mImgIcon.getLayoutParams().height = (int) lIconDimension;
		mImgIcon.getLayoutParams().width = (int) lIconDimension;

		if (lDrawableIcon != null) {
			int lColor = mTypedArray.getColor(R.styleable.StatusNull_iconColor, -1);

			if (lColor != -1) {
				DrawableCompat.setTint(lDrawableIcon, lColor);
			}

			mImgIcon.setImageDrawable(lDrawableIcon);
		}

		String lTitle = mTypedArray.getString(R.styleable.StatusNull_title);

		if (lTitle != null) {
			mTxtTitle.setText(lTitle);
		} else {
			mTxtTitle.setVisibility(GONE);
		}

		String lString = mTypedArray.getString(R.styleable.StatusNull_content);

		if (lString != null) {
			if (!isInEditMode()) {
				mTxtContent.setText(Html.fromHtml(lString));
			} else {
				mTxtContent.setText(lString);
			}
		} else {
			mTxtContent.setVisibility(GONE);
		}

		mCompatButton = (AppCompatButton) findViewById(R.id.btn);

		String lButtonText = mTypedArray.getString(R.styleable.StatusNull_button_text);

		if (lButtonText != null) {
			mCompatButton.setText(lButtonText);

			int lColor = mTypedArray.getColor(R.styleable.StatusNull_button_text_color, -1);

			if (lColor != -1) {
				mCompatButton.setTextColor(lColor);
			}
		} else {
			mCompatButton.setVisibility(GONE);
		}
	}

	public void setIcon(int _resource) {
		Drawable lDrawable = getContext().getResources().getDrawable(_resource);

		DrawableCompat.setTint(lDrawable, mTypedArray.getColor(R.styleable.StatusNull_iconColor,
				getContext().getResources().getColor(android.R.color.white)));

		mImgIcon.setImageDrawable(lDrawable);
	}

	public void setIconColor(Drawable _color) {
		mIconBackground.setBackgroundDrawable(_color);
	}

	public void setMessage(String _message) {
		mTxtContent.setText(_message);
	}

	public void setMessage(Spanned _spanned) {
		mTxtContent.setText(_spanned);
	}

	public void setTitle(String _title) {
		mTxtTitle.setText(_title);
	}


}
