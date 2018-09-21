package com.github.v_mas.layouttransitionbug

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "v_bughunt"

class MainActivity : AppCompatActivity() {
    private inline val fm get() = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fm.replace(R.id.container_main, MainFragment.newInstance())
    }

    fun logAction(action: String) {
        tv_log?.apply {
            text = "$text\n$action"
        }
        Log.d(TAG, action)
    }

    fun logAction(fragment: RandomFragment, action: String) {
        logAction("frag@${System.identityHashCode(fragment)}(${fragment.anim}) $action")
    }
}
