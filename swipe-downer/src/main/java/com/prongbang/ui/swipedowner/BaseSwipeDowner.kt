package com.prongbang.ui.swipedowner

import android.app.Activity
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.view.MotionEventCompat

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
	private var mViewParent: ViewGroup? = null

	override fun builder(view: View?): BaseSwipeDowner {
		if (view != null) {
			mViewParent = view as ViewGroup
			val v = LayoutInflater.from(activity.applicationContext)
					.inflate(R.layout.item_swipe_downer, mViewParent, false)
			if (v != null) {
				// Check Swipe down message created.
				val findView = view.findViewById<View>(R.id.vMsgContainer)
				if (findView == null) {
					view.addView(v)
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

	private fun getRootView(activity: Activity): View {

		return activity.findViewById<View>(android.R.id.content).rootView
	}

	override fun setHeight(h: Int): BaseSwipeDowner {
		val params = mViewLayout?.layoutParams
		params?.height = h
		return this
	}

	override fun setBackground(@ColorRes color: Int): BaseSwipeDowner {
		mViewLayout?.setBackgroundColor(ContextCompat.getColor(activity.applicationContext, color))
		return this
	}

	override fun setTextColor(@ColorRes color: Int): BaseSwipeDowner {
		mTvMessage?.setTextColor(ContextCompat.getColor(activity.applicationContext, color))
		return this
	}

	override fun setImageSuccess(@DrawableRes imageRes: Int): BaseSwipeDowner {
		imageSuccess = imageRes
		return this
	}

	override fun setImageError(@DrawableRes imageRes: Int): BaseSwipeDowner {
		imageError = imageRes
		return this
	}

	override fun setImageWarning(@DrawableRes imageRes: Int): BaseSwipeDowner {
		imageWarning = imageRes
		return this
	}

	override fun isSuccess(): BaseSwipeDowner {

		mViewLayout?.background = ContextCompat.getDrawable(activity.applicationContext,
				R.drawable.bg_swipe_down_success)
		setTextColor(R.color.white)
		mImage?.setImageDrawable(
				ContextCompat.getDrawable(activity.applicationContext, imageSuccess))

		return this
	}

	override fun isError(): BaseSwipeDowner {

		mViewLayout?.background = ContextCompat.getDrawable(activity.applicationContext,
				R.drawable.bg_swipe_down_fail)
		setTextColor(R.color.white)
		mImage?.setImageDrawable(ContextCompat.getDrawable(activity.applicationContext, imageError))

		return this
	}

	override fun isWarning(): BaseSwipeDowner {

		mViewLayout?.background = ContextCompat.getDrawable(activity.applicationContext,
				R.drawable.bg_swipe_down_warning)
		setTextColor(R.color.white)
		mImage?.setImageDrawable(
				ContextCompat.getDrawable(activity.applicationContext, imageWarning))

		return this
	}

	override fun message(msg: String): BaseSwipeDowner {
		mTvMessage?.text = msg
		return this
	}

	override fun message(@StringRes msg: Int): BaseSwipeDowner {
		mTvMessage?.text = activity.applicationContext.getString(msg)
		return this
	}

	override fun show() {
		isClosed = false
		val gestureDetector = GestureDetector(activity.applicationContext, SingleTapConfirm())
		val slideDown = AnimationUtils.loadAnimation(activity.applicationContext, R.anim.slide_down)

		mViewLayout?.apply {
			visibility = View.VISIBLE
			startAnimation(slideDown)
			setOnTouchListener(View.OnTouchListener { v, event ->
				val action = MotionEventCompat.getActionMasked(event)
				if (gestureDetector.onTouchEvent(event)) {
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

			postDelayed({
				if (!isClosed) {
					hide()
				}
			}, duration)
		}

	}

	override fun hide() {
		val slideUp = AnimationUtils.loadAnimation(activity.applicationContext, R.anim.slide_up)
		mViewLayout?.apply {
			startAnimation(slideUp)
			postDelayed({
				isClosed = true
				mViewLayout?.visibility = View.GONE
				mViewParent?.removeView(mViewLayout)
				onCloseListener?.onClosed()
			}, 500)
		}
	}

	override fun setOnItemClickListener(
			onItemClickListener: OnSwipeDownItemClickListener): BaseSwipeDowner {
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