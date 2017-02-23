package com.olimou.android.status_null;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.text.SpannableString;
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

	public AppCompatButton getButton() {
		return mCompatButton;
	}

	private void init(AttributeSet _attrs, int _defStyle) {
		inflate(getContext(), R.layout.component_status_null, this);

		mIconBackground = findViewById(R.id.frame_background);
		mImgIcon = (AppCompatImageView) findViewById(R.id.img_icon);
		mTxtTitle = (TextView) findViewById(R.id.txt_title);
		mTxtContent = (TextView) findViewById(R.id.txt_content);

		if (_attrs != null) {
			TypedArray lTypedArray = getContext().getTheme()
					.obtainStyledAttributes(_attrs, R.styleable.StatusNull, 0, 0);

			int lCircleColor = lTypedArray.getColor(R.styleable.StatusNull_circleColor,
					getContext().getResources().getColor(R.color.status_null_default_circle_color));

			Drawable lDrawableBackground = ContextCompat.
					getDrawable(getContext(), R.drawable.background_circle_grey);

			DrawableCompat.setTint(DrawableCompat.wrap(lDrawableBackground).mutate(), lCircleColor);

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				mIconBackground.setBackground(lDrawableBackground);
			} else {
				mIconBackground.setBackgroundDrawable(lDrawableBackground);
			}

			float lCircleDimension = lTypedArray.getDimension(R.styleable.StatusNull_circleSize,
					getContext().getResources()
							.getDimension(R.dimen.status_null_circle_background_size));

			mIconBackground.getLayoutParams().height = (int) lCircleDimension;
			mIconBackground.getLayoutParams().width = (int) lCircleDimension;

			Drawable lDrawableIcon;

			int lIconColor = lTypedArray.getColor(R.styleable.StatusNull_iconColor, 0);

			int lResourceId = lTypedArray.getResourceId(R.styleable.StatusNull_icon, -1);

			if (lResourceId != 1) {
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
					lDrawableIcon = lTypedArray.getDrawable(R.styleable.StatusNull_icon);

					if (lDrawableIcon != null) {

						if (lIconColor != 0) {
							lDrawableIcon.setTint(lIconColor);
						}

						mImgIcon.setImageDrawable(lDrawableIcon);
					}
				} else {
					lDrawableIcon = AppCompatResources.getDrawable(getContext(), lResourceId);

					if (lDrawableIcon != null) {
						lDrawableIcon = DrawableCompat.wrap(lDrawableIcon).mutate();

						if (lIconColor != 0) {
							DrawableCompat.setTint(lDrawableIcon, lIconColor);
						}

						mImgIcon.setImageDrawable(lDrawableIcon);
					}
				}
			}

			float lIconDimension = lTypedArray.getDimension(R.styleable.StatusNull_iconSize,
					getContext().getResources().getDimension(R.dimen.status_null_icon_size));

			mImgIcon.getLayoutParams().height = (int) lIconDimension;
			mImgIcon.getLayoutParams().width = (int) lIconDimension;

			String lTitle = lTypedArray.getString(R.styleable.StatusNull_title);

			if (lTitle != null) {
				mTxtTitle.setText(lTitle);

				mTxtTitle.setTextColor(lTypedArray.getColor(R.styleable.StatusNull_titleColor,
						ContextCompat
								.getColor(getContext(), R.color.status_null_text_disable_light)));
			} else {
				mTxtTitle.setVisibility(GONE);
			}

			CharSequence lText = lTypedArray.getText(R.styleable.StatusNull_content);

			if (lText != null) {
				SpannableString lContent = new SpannableString(lText);

				if (!isInEditMode()) {
					mTxtContent.setText(lContent);
				} else {
					mTxtContent.setText(lContent);
				}

				mTxtContent.setTextColor(lTypedArray.getColor(R.styleable.StatusNull_contentColor,
						ContextCompat
								.getColor(getContext(), R.color.status_null_text_disable_light)));
			} else {
				mTxtContent.setVisibility(GONE);
			}

			mCompatButton = (AppCompatButton) findViewById(R.id.btn);

			String lButtonText = lTypedArray.getString(R.styleable.StatusNull_button_text);

			if (lButtonText != null) {
				mCompatButton.setText(lButtonText);

				int lColor = lTypedArray.getColor(R.styleable.StatusNull_button_text_color, 0);

				if (lColor != 0) {
					mCompatButton.setTextColor(lColor);
				}
			} else {
				mCompatButton.setVisibility(GONE);
			}
		}
	}

	public void setCircleColor(Drawable _color) {
		mIconBackground.setBackgroundDrawable(_color);
	}

	public void setIcon(int _resource) {
		Drawable lDrawable = ContextCompat.getDrawable(getContext(), _resource);

		mImgIcon.setImageDrawable(lDrawable);
	}

	public void setIconColor(@ColorRes int _color) {
		Drawable lDrawable = mImgIcon.getDrawable();

		DrawableCompat.setTint(lDrawable, ContextCompat.getColor(getContext(), _color));

	}

	public void setMessage(String _message) {
		mTxtContent.setText(_message);
		mTxtContent.setVisibility(VISIBLE);
	}

	public void setMessage(Spanned _spanned) {
		mTxtContent.setText(_spanned);
		mTxtContent.setVisibility(VISIBLE);
	}

	public void setTitle(String _title) {
		mTxtTitle.setText(_title);
		mTxtTitle.setVisibility(VISIBLE);
	}


}
