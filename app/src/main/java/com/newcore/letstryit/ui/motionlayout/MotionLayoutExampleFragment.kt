package com.newcore.letstryit.ui.motionlayout

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.viewModels
import com.newcore.letstryit.R
import com.newcore.letstryit.core.BaseFragment
import com.newcore.letstryit.databinding.FragmentMotionLayoutExampleBinding

class MotionLayoutExampleFragment :
    BaseFragment<FragmentMotionLayoutExampleBinding>(FragmentMotionLayoutExampleBinding::inflate) {

    private val vm: MotionLayoutExampleFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.motionLayout.addTransitionListener(object : MotionLayout.TransitionListener {

            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
            ) {
                Log.i(TAG, "onTransitionStarted: ")
            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float,
            ) {


            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                Log.i(TAG, "start: ${currentId == R.id.start}")
                Log.i(TAG, "midle: ${currentId == R.id.midle}")
                Log.i(TAG, "end: ${currentId == R.id.end}")

                if (currentId == R.id.midle && motionLayout?.startState == R.id.start) {
                    motionLayout.setTransition(R.id.transitionTwo)
                    motionLayout.transitionToEnd()
                } else if (currentId == R.id.midle && motionLayout?.endState == R.id.end) {
                    motionLayout.setTransition(R.id.transitionThree)
                    motionLayout.transitionToStart()
                    Log.i(TAG, "start: hrererer")

                }


                Log.i(TAG, "start: ${motionLayout?.endState == R.id.start}")
                Log.i(TAG, "midle: ${motionLayout?.endState == R.id.midle}")
                Log.i(TAG, "end: ${motionLayout?.endState == R.id.end}")

            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float,
            ) {
                Log.i(TAG, "onTransitionTrigger: ")
            }

        })

    }

    private val TAG = "MotionLayoutExampleFrag"

}

