package com.adroits.smartmedcare.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils.TruncateAt;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.adroits.smartmedcare.R;


public class ClearableEditText extends RelativeLayout
{
	LayoutInflater inflater = null;
	EditText edit_text;
	Button btn_clear;
    ImageView icon_start;

	public ClearableEditText(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		initViews();
	}
	
	public ClearableEditText(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		initViews();
	
	}
	
	public ClearableEditText(Context context)
	{
		super(context);
		initViews();
	}
	
	void initViews()
	{
		inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.clearable_edit_text, this, true);
		edit_text = (EditText) findViewById(R.id.clearable_edit);

        icon_start = (ImageView) findViewById(R.id.clearable_icon_start);
        icon_start.setVisibility(RelativeLayout.INVISIBLE);

		btn_clear = (Button) findViewById(R.id.clearable_button_clear);
		btn_clear.setVisibility(RelativeLayout.INVISIBLE);
		clearText();
        edit_text.setHintTextColor(Color.parseColor("#AAE8E8E8"));
		//edit_text.setHint("Search Itumo...");
        edit_text.setTextColor(Color.WHITE);
		edit_text.setSingleLine();
		edit_text.setLines(1);
		edit_text.setEllipsize(TruncateAt.END);
        edit_text.setImeOptions(EditorInfo.IME_ACTION_GO);
		showHideClearButton();
	}

    public void setHintText(String hint)
    {
        edit_text.setHint(hint);
    }

    public void setHintTextColor(int color)
    {
        edit_text.setHintTextColor(color);
    }

    public void setTextColor(int color)
    {
        edit_text.setTextColor(color);
    }

    public void setCloseIcon(int resId){btn_clear.setBackgroundResource(resId);}

    public void setStartIcon(int resId){icon_start.setImageResource(resId);icon_start.setVisibility(RelativeLayout.VISIBLE);}

    public void setMultiLine(int lines)
    {
        edit_text.setSingleLine(false);
        edit_text.setLines(lines);
        edit_text.setGravity(Gravity.LEFT | Gravity.TOP);
        edit_text.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);

    }

    @Override
    public void setEnabled(boolean enabled)
    {
        btn_clear.setEnabled(enabled);
        edit_text.setEnabled(enabled);

        super.setEnabled(enabled);
    }

	void clearText()
	{
		btn_clear.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				edit_text.setText("");
			}
		});
	}
	
	void showHideClearButton()
	{
		edit_text.addTextChangedListener(new TextWatcher()
			{
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				if (s.length() > 0)
				btn_clear.setVisibility(RelativeLayout.VISIBLE);
				else
				btn_clear.setVisibility(RelativeLayout.INVISIBLE);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{
			
			}
			
			@Override
			public void afterTextChanged(Editable s)
			{
			
			}
		});
	}
	
	public Editable getText()
	{
		Editable text = edit_text.getText();
		return text;
	}
	
	public EditText getInternalEditText()
	{
		return edit_text;
	}
}
