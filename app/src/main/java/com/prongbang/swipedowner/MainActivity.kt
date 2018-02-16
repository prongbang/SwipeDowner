package com.prongbang.swipedowner

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.prongbang.ui.swipedowner.OnCloseListener
import com.prongbang.ui.swipedowner.SwipeDowner

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        var closedSuccess = true
        var closedWarning = true
        var closedError = true

        fab1.setOnClickListener { view ->
            if (closedError) {
                closedError = false
                SwipeDowner(this).builder(window.decorView.rootView).onClosed(object : OnCloseListener {
                    override fun onClosed() {
                        closedError = true
                    }
                }).isError().message("Error").show()
            }
        }

        fab2.setOnClickListener { view ->
            if (closedWarning) {
                closedWarning = false
                SwipeDowner(this).builder(window.decorView.rootView).onClosed(object : OnCloseListener {
                    override fun onClosed() {
                        closedWarning = true
                    }
                }).isWarning().message("Warning").show()
            }
        }

        fab3.setOnClickListener { view ->
            if (closedSuccess) {
                closedSuccess = false
                SwipeDowner(this).builder(window.decorView.rootView).onClosed(object : OnCloseListener {
                    override fun onClosed() {
                        closedSuccess = true
                    }
                }).isSuccess().message("Success").show()
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
