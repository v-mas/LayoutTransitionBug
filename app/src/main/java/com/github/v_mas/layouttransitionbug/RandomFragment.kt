package com.github.v_mas.layouttransitionbug

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_random.*

private const val ARG_ANIM = "RF_a"
private const val ARG_BG = "RF_bg"
private const val ARG_WIDTH = "RF_w"
private const val ARG_HEIGHT = "RF_h"

class RandomFragment : Fragment() {
    var anim = false
    private var bgColor: Int = 0
    private var width: Int = 50
    private var height: Int = 50

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        log("attach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.run {
            anim = getBoolean(ARG_ANIM, false)
            bgColor = getInt(ARG_BG, bgColor)
            width = getInt(ARG_WIDTH, width)
            height = getInt(ARG_HEIGHT, height)
        }
        log("create")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_random, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        log("viewCreated")
        view.setBackgroundColor(bgColor or (255 shl 24))
        with(text1) {
            layoutParams = layoutParams.apply {
                width = this@RandomFragment.width
                height = this@RandomFragment.height
            }
            text = "${bgColor.toString(16)} @ ${System.identityHashCode(this@RandomFragment)}"
        }
    }

    override fun onDestroyView() {
        log("destroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        log("destroy")
        super.onDestroy()
    }

    override fun onDetach() {
        log("detach")
        super.onDetach()
    }

    private fun log(action: String) {
        (activity as? MainActivity)?.also { it.logAction(this, action) }
    }

    companion object {
        @JvmStatic
        fun newInstance(anim: Boolean, bg: Int, w: Int, h: Int) = RandomFragment().apply {
            arguments = Bundle().apply {
                putBoolean(ARG_ANIM, anim)
                putInt(ARG_BG, bg)
                putInt(ARG_WIDTH, w)
                putInt(ARG_HEIGHT, h)
            }
        }
    }
}
