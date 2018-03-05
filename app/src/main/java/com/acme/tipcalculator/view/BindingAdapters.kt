package com.acme.tipcalculator.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View

/**
 * TODO Lab 4: Adapters and Converters
 *
 * Wire this function up as a Binding Adapter for TextViews to support the custom
 * View Attribute pair
 *
 * app:text - A nullable String?
 * app:animate - A Boolean
 *
 * After defining the proper signature with any required annotations
 * be sure to uncomment the body of this function and make use in `content_tip_calculator.xml`.
 *
 * You should not need to change the body of this function or any of the private functions
 * in this file to get the animation / visibility functionality to work.
 *
 * Bonus Questions:
 * - Will this work for EditText views too?
 * - How about ImageViews?
 * - What would it take to apply this to all Views?
 * - What happens if you change the order of the arguments?
 */
fun magicTextWithFade(/* Hint, this method needs 3 arguments.  Remember Order is important! */) {

    /*
    if (text.isNullOrEmpty()) {
        hideView(tv, animate) {
            tv.text = ""
        }
    } else {
        tv.text = text
        showView(tv, animate)
    }
    */
}

private fun showView(v: View, animate: Boolean, then: () -> Unit = {}) {

    // do nothing if view already visible
    if(v.visibility == View.VISIBLE) { return }

    if(animate) {
        v.alpha = 0.0f
        v.visibility = View.VISIBLE
        animateToAlpha(v, alpha = 1.0f)
    } else {
        v.visibility = View.VISIBLE
        v.alpha = 1.0f
    }

    then()
}

private fun hideView(v: View, animate: Boolean, then: () -> Unit = {}) {

    // do nothing if view already NOT visible
    if(v.visibility != View.VISIBLE) { return }

    if(animate) {
        animateToAlpha(v, alpha = 0.0f) {
            v.visibility = View.GONE
            then()
        }
    } else {
        v.alpha = 0.0f
        v.visibility = View.GONE
        then()
    }
}


private fun animateToAlpha(v: View, alpha: Float, duration: Long = 500, then: () -> Unit = {}) {
    v.animate()
            .alpha(alpha)
            .setDuration(duration)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    then()
                }
            })
}

