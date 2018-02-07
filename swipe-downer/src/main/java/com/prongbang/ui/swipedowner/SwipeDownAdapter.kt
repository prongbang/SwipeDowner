package com.prongbang.ui.swipedowner

import android.view.View

/**
 * Created by prongbang on 2/7/2018 AD.
 */
interface SwipeDownAdapter<T> {

    fun builder(view: View?): T

    fun setHeight(h: Int): T

    fun setBackground(color: Int): T

    fun setTextColor(color: Int): T

    fun setImageSuccess(imageRes: Int): T

    fun setImageError(imageRes: Int): T

    fun setImageWarning(imageRes: Int): T

    fun isSuccess(): T

    fun isError(): T

    fun isWarning(): T

    fun message(msg: String): T

    fun message(msg: Int): T

    fun show()

    fun hide()

    fun setOnItemClickListener(onItemClickListener: OnSwipeDownItemClickListener): T

    fun setDuration(duration: Long): T

}