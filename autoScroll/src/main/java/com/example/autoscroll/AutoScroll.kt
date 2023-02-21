package com.example.autoscroll

import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class AutoScroll {
    companion object{
        private var spotLightReverse = false
        private var spotLightTouched = false
        private var scrollPosition = 0
        private lateinit var recyclerview: RecyclerView
        private var totalItems = 0
        private var delayMilliSecs: Long = 3000

        fun setAutoScrollInfo(actualTotalItems: Int, actualRecyclerView: RecyclerView,delayMilliSecs: Long){
            this.totalItems = actualTotalItems-1
            this.recyclerview = actualRecyclerView
            this.delayMilliSecs = delayMilliSecs
            recyclerview.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener{
                private var timer = Timer()
                private val TOUCH_DELAY: Long = 2000 // milliseconds

                override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                    spotLightTouched = true
                    timer.cancel()
                    timer = Timer()
                    scrollPosition = (recyclerview.computeHorizontalScrollOffset()/recyclerview.width)
                    timer.schedule(
                        object : TimerTask() {
                            override fun run() {
                                // here, you're not typing anymore
                                spotLightTouched = false
                                spotlightAutoScroll()
                            }
                        },
                        TOUCH_DELAY
                    )
                    return false
                }

                override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                    //
                }

                override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                    //
                }
            })
            spotlightAutoScroll()
        }

        private fun spotlightAutoScroll(){
            if (!spotLightTouched){
                Handler(Looper.getMainLooper()).postDelayed({
                    recyclerview.smoothScrollToPosition(scrollPosition)
                    if (spotLightReverse){
                        if (scrollPosition == 0){
                            spotLightReverse = false
                            scrollPosition = 1
                            spotlightAutoScroll()
                        }else{
                            scrollPosition -= 1
                            spotlightAutoScroll()
                        }
                    }else{
                        if (scrollPosition == totalItems){
                            spotLightReverse = true
                            scrollPosition = totalItems-1
                            spotlightAutoScroll()
                        }else{
                            scrollPosition += 1
                            spotlightAutoScroll()
                        }
                    }
                }, delayMilliSecs)
            }
        }
    }
}