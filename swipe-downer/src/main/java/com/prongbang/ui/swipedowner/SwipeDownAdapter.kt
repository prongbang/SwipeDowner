package com.prongbang.ui.swipedowner

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Created by prongbang on 2/7/2018 AD.
 */
interface SwipeDownAdapter<T> {

    fun builder(view: View?): T

    fun builder(): T

    fun setHeight(h: Int): T

    fun setBackground(@ColorRes color: Int): T

    fun setTextColor(@ColorRes color: Int): T

    fun setImageSuccess(@DrawableRes imageRes: Int): T

    fun setImageError(@DrawableRes imageRes: Int): T

    fun setImageWarning(@DrawableRes imageRes: Int): T

    fun isSuccess(): T

    fun isError(): T

    fun isWarning(): T

    fun message(msg: String): T

    fun message(@StringRes msg: Int): T

    fun show()

    fun hide()

    fun setOnItemClickListener(onItemClickListener: OnSwipeDownItemClickListener): T

    fun setDuration(duration: Long): T

    fun onClosed(onCloseListener: OnCloseListener): T

}