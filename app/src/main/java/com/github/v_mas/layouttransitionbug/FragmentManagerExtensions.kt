package com.github.v_mas.layouttransitionbug

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction


fun FragmentManager.replace(@IdRes id: Int, fragment: Fragment) {
    beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out).replace(id, fragment, null).commitAllowingStateLoss()
//    beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(id, fragment, null).commitAllowingStateLoss()
}

fun FragmentManager.remove(@IdRes id: Int) {
    findFragmentById(id)?.also {
        beginTransaction().remove(it).commitAllowingStateLoss()
    }
}
