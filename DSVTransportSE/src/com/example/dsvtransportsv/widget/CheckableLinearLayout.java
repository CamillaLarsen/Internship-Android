package com.example.dsvtransportsv.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.LinearLayout;
/**
 * 
 * @author Camilla
 *
 * Checkable linear layout for the dialog.
 * This class makes my radio buttons active on click
 * and only activates one radio button at a time.
 *
 */

public class CheckableLinearLayout extends LinearLayout implements Checkable {

	private boolean isChecked;
	private List<Checkable> checkableViews;

	public CheckableLinearLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		initialise(attrs);
	}

	public CheckableLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialise(attrs);
	}

	public CheckableLinearLayout(Context context, int checkableId) {
		super(context);
		initialise(null);
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
		for (Checkable c : checkableViews) {
			c.setChecked(isChecked);
		}
	}

	public void toggle() {
		this.isChecked = !this.isChecked;
		for (Checkable c : checkableViews) {
			c.toggle();
		}
	}
	
	/**
	 * Checks how many children there is.
	 */
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

		final int childCount = this.getChildCount();
		for (int i = 0; i < childCount; ++i) {
			findCheckableChildren(this.getChildAt(i));
		}
	}


	private void initialise(AttributeSet attrs) {
		this.isChecked = false;
		this.checkableViews = new ArrayList<Checkable>(5);
	}

	/**
	 * All the children, the list items, of the view, implement the
	 * interface Checkable
	 */
	private void findCheckableChildren(View v) {
		if (v instanceof Checkable) {
			this.checkableViews.add((Checkable) v);
		}

		if (v instanceof ViewGroup) {
			final ViewGroup vg = (ViewGroup) v;
			final int childCount = vg.getChildCount();
			for (int i = 0; i < childCount; ++i) {
				findCheckableChildren(vg.getChildAt(i));
			}
		}
	}
}