package com.prongbang.ui.swipedowner

import android.app.Activity
import android.support.v4.content.ContextCompat
import android.support.v4.view.MotionEventCompat
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.util.Log
import android.view.*
import android.view.animation.AnimationUtils

/**
 * Created by prongbang on 2/7/2018 AD.
 */
open class BaseSwipeDowner(private val activity: Activity) : SwipeDownAdapter<BaseSwipeDowner> {

    private var imageSuccess: Int = R.drawable.ic_check_circle
    private var imageError: Int = R.drawable.ic_sentiment_dissatisfied
    private var imageWarning: Int = R.drawable.ic_warning
    private var duration: Long = 2000
    private var isClosed = false
    private var onItemClickListener: OnSwipeDownItemClickListener? = null
    private var mViewLayout: View? = null
    private var mTvMessage: AppCompatTextView? = null
    private var mImage: AppCompatImageView? = null
    private var onCloseListener: OnCloseListener? = null

    override fun builder(view: View?): BaseSwipeDowner {
        if (view != null) {
            val v = LayoutInflater.from(activity.applicationContext).inflate(R.layout.item_swipe_downer, view as ViewGroup, false)
            if (v != null) {
                // check view added
                val findView = view.findViewById<View>(R.id.vMsgContainer)
                if (findView == null) {
                    view.addView(v)
                    if (BuildConfig.DEBUG)
                        Log.i("BaseSwipeDowner", "Create swipe down message")
                } else {
                    // found
                    if (BuildConfig.DEBUG)
                        Log.i("BaseSwipeDowner", "Swipe down message created.")
                }
                mViewLayout = view.findViewById(R.id.vMsgContainer)
                mImage = view.findViewById(R.id.ivNotiImage)
                mTvMessage = view.findViewById(R.id.tvMessage)
            }
        }

        return this
    }

    override fun builder(): BaseSwipeDowner {

        return builder(activity.window.decorView.rootView ?: getRootView(activity))
    }

    private fun getRootView(activity: Activity) : View {

        return activity.findViewById<View>(android.R.id.content).rootView
    }

    override fun setHeight(h: Int): BaseSwipeDowner {
        val params = mViewLayout?.layoutParams
        params?.height = h
        return this
    }

    override fun setBackground(color: Int): BaseSwipeDowner {
        mViewLayout?.setBackgroundColor(ContextCompat.getColor(activity.applicationContext, color))
        return this
    }

    override fun setTextColor(color: Int): BaseSwipeDowner {
        mTvMessage?.setTextColor(ContextCompat.getColor(activity.applicationContext, color))
        return this
    }

    override fun setImageSuccess(imageRes: Int): BaseSwipeDowner {
        imageSuccess = imageRes
        return this
    }

    override fun setImageError(imageRes: Int): BaseSwipeDowner {
        imageError = imageRes
        return this
    }

    override fun setImageWarning(imageRes: Int): BaseSwipeDowner {
        imageWarning = imageRes
        return this
    }

    override fun isSuccess(): BaseSwipeDowner {

        mViewLayout?.background = ContextCompat.getDrawable(activity.applicationContext, R.drawable.bg_swipe_down_success)
        setTextColor(R.color.white)
        mImage?.setImageDrawable(ContextCompat.getDrawable(activity.applicationContext, imageSuccess))

        return this
    }

    override fun isError(): BaseSwipeDowner {

        mViewLayout?.background = ContextCompat.getDrawable(activity.applicationContext, R.drawable.bg_swipe_down_fail)
        setTextColor(R.color.white)
        mImage?.setImageDrawable(ContextCompat.getDrawable(activity.applicationContext, imageError))

        return this
    }

    override fun isWarning(): BaseSwipeDowner {

        mViewLayout?.background = ContextCompat.getDrawable(activity.applicationContext, R.drawable.bg_swipe_down_warning)
        setTextColor(R.color.white)
        mImage?.setImageDrawable(ContextCompat.getDrawable(activity.applicationContext, imageWarning))

        return this
    }

    override fun message(msg: String): BaseSwipeDowner {
        mTvMessage?.text = msg
        return this
    }

    override fun message(msg: Int): BaseSwipeDowner {
        mTvMessage?.text = activity.applicationContext.getString(msg)
        return this
    }

    override fun show() {
        isClosed = false
        val gestureDetector = GestureDetector(activity.applicationContext, SingleTapConfirm())
        val slideDown = AnimationUtils.loadAnimation(activity.applicationContext, R.anim.slide_down)

        if (mViewLayout != null) {
            mViewLayout!!.visibility = View.VISIBLE
            mViewLayout!!.startAnimation(slideDown)
            mViewLayout!!.setOnTouchListener(View.OnTouchListener { v, event ->
                val action = MotionEventCompat.getActionMasked(event)
                if (gestureDetector.onTouchEvent(event)) {

                    if (BuildConfig.DEBUG)
                        Log.i("BaseSwipeDowner", "Click it!")

                    onItemClickListener?.onClick(v)

                } else {
                    when (action) {
                        MotionEvent.ACTION_MOVE -> return@OnTouchListener true
                        MotionEvent.ACTION_UP -> {
                            hide()
                            return@OnTouchListener true
                        }
                    }
                }
                true
            })

            mViewLayout!!.postDelayed({
                if (!isClosed) {
                    hide()
                }
            }, duration)
        }
    }

    override fun hide() {
        val slideUp = AnimationUtils.loadAnimation(activity.applicationContext, R.anim.slide_up)
        mViewLayout?.startAnimation(slideUp)
        mViewLayout?.postDelayed({
            isClosed = true
            mViewLayout?.visibility = View.GONE
            onCloseListener?.onClosed()
        }, 500)
    }

    override fun setOnItemClickListener(onItemClickListener: OnSwipeDownItemClickListener): BaseSwipeDowner {
        this.onItemClickListener = onItemClickListener
        return this
    }

    override fun setDuration(duration: Long): BaseSwipeDowner {
        this.duration = duration
        return this
    }

    override fun onClosed(onCloseListener: OnCloseListener): BaseSwipeDowner {
        this.onCloseListener = onCloseListener
        return this
    }

}