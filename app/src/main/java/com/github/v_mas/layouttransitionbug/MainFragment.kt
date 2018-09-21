package com.github.v_mas.layouttransitionbug

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*

class MainFragment : Fragment() {
    private inline val fm get() = childFragmentManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val random = Random(System.currentTimeMillis())
        btn_replace.setOnClickListener {
            val bg = random.nextInt(0xFF_FF_FF)
            val w = random.nextInt(300) + 50
            val h = random.nextInt(300) + 50
            log("replacing fragments")
            fm.replace(CONTAINER_ANIM, RandomFragment.newInstance(true, bg, w, h))
            fm.replace(CONTAINER_NO_ANIM, RandomFragment.newInstance(false, bg, w, h))
            fm.executePendingTransactions()
        }
        btn_remove.setOnClickListener {
            log("removing fragments")
            fm.remove(CONTAINER_ANIM)
            fm.remove(CONTAINER_NO_ANIM)
            fm.executePendingTransactions()
        }
    }

    private fun log(action: String) {
        (activity as? MainActivity)?.logAction(action)
    }

    companion object {
        private const val CONTAINER_ANIM = R.id.container_bottom_animate
        private const val CONTAINER_NO_ANIM = R.id.container_bottom_noanim

        @JvmStatic
        fun newInstance() = MainFragment()
    }
}
