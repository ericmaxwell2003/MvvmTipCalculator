package com.acme.tipcalculator.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.databinding.BindingAdapter
import android.view.View
import android.widget.TextView


@BindingAdapter(value = ["app:text", "app:animate"])
fun magicTextWithFade(tv: TextView, text: String?, animate: Boolean) {

    if (text.isNullOrEmpty()) {
        hideView(tv, animate) {
            tv.text = ""
        }
    } else {
        tv.text = text
        showView(tv, animate)
    }
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

