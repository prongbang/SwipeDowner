package com.prongbang.ui.swipedowner

import android.view.GestureDetector
import android.view.MotionEvent

/**
 * Created by prongbang on 2/7/2018 AD.
 */
class SingleTapConfirm: GestureDetector.SimpleOnGestureListener() {

    override fun onSingleTapUp(event: MotionEvent): Boolean {

        return true
    }

}