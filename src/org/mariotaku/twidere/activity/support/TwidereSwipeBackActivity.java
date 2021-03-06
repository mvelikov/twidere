/*
 * 				Twidere - Twitter client for Android
 *
 *  Copyright (C) 2012-2013 Mariotaku Lee <mariotaku.lee@gmail.com>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mariotaku.twidere.activity.support;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;

@SuppressLint("Registered")
public class TwidereSwipeBackActivity extends BaseSupportThemedSwipeBackActivity {

	private static final int[] ANIM_STYLE_ATTRS = { android.R.attr.activityOpenEnterAnimation,
			android.R.attr.activityOpenExitAnimation, android.R.attr.activityCloseEnterAnimation,
			android.R.attr.activityCloseExitAnimation };
	private int mActivityOpenEnterAnimation;
	private int mActivityOpenExitAnimation;
	private int mActivityCloseEnterAnimation;
	private int mActivityCloseExitAnimation;

	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(mActivityCloseEnterAnimation, mActivityCloseExitAnimation);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		TypedArray activityStyle = getTheme().obtainStyledAttributes(new int[] { android.R.attr.windowAnimationStyle });
		final int windowAnimationStyleResId = activityStyle.getResourceId(0, 0);
		activityStyle.recycle();

		// Now retrieve the resource ids of the actual animations used in the
		// animation style pointed to by
		// the window animation resource id.
		activityStyle = getTheme().obtainStyledAttributes(windowAnimationStyleResId, ANIM_STYLE_ATTRS);
		mActivityOpenEnterAnimation = activityStyle.getResourceId(0, 0);
		mActivityOpenExitAnimation = activityStyle.getResourceId(1, 0);
		mActivityCloseEnterAnimation = activityStyle.getResourceId(2, 0);
		mActivityCloseExitAnimation = activityStyle.getResourceId(3, 0);
		activityStyle.recycle();
		overridePendingTransition(mActivityOpenEnterAnimation, mActivityOpenExitAnimation);
		super.onCreate(savedInstanceState);
	}
}
