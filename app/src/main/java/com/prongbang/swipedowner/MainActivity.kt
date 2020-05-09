package com.prongbang.swipedowner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prongbang.ui.swipedowner.SwipeDowner
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		setSupportActionBar(toolbar)

		fab1.setOnClickListener { view ->
			SwipeDowner(this).builder()
					.isError()
					.message("Error")
					.show()
		}

		fab2.setOnClickListener { view ->
			SwipeDowner(this).builder()
					.isWarning()
					.message("Warning")
					.show()
		}

		fab3.setOnClickListener { view ->
			SwipeDowner(this).builder()
					.isSuccess()
					.message("Success")
					.show()
		}

	}
}
