package com.olimou.android.status_null;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
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

	private void init(AttributeSet _attrs, int _defStyle) {
		View lView = inflate(getContext(), R.layout.componente_status_null, this);

		mIconBackground = findViewById(R.id.frame_background);
		mImgIcon = (AppCompatImageView) findViewById(R.id.img_icon);
		mTxtTitle = (TextView) findViewById(R.id.txt_title);
		mTxtContent = (TextView) findViewById(R.id.txt_content);

		TypedArray a = getContext().getTheme()
				.obtainStyledAttributes(_attrs, R.styleable.StatusNull, 0, 0);

		Drawable lDrawable = a.getDrawable(R.styleable.StatusNull_iconColor);

		if (lDrawable != null) {
			mIconBackground.setBackgroundDrawable(lDrawable);
			mTxtTitle.setTextColor(
					getContext().getResources().getColor(android.R.color.primary_text_light));
		}

		//		mImgIcon.setImageResource(lDrawable1);

		mTxtTitle.setText(a.getString(R.styleable.StatusNull_title));

		String lString = a.getString(R.styleable.StatusNull_content);

		if (!isInEditMode() && lString != null) {
			mTxtContent.setText(Html.fromHtml(lString));
		}

		mCompatButton = (AppCompatButton) findViewById(R.id.btn);
		mCompatButton.setVisibility(GONE);
	}

	public void setButton(String _title, OnClickListener _onClickListener) {
		mCompatButton.setText(_title);

		mCompatButton.setOnClickListener(_onClickListener);

		mCompatButton.setVisibility(VISIBLE);
	}

	public void setIcon(int _resource) {
		Drawable lDrawable = getContext().getResources().getDrawable(_resource);

		DrawableCompat.setTint(lDrawable, Color.WHITE);

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
