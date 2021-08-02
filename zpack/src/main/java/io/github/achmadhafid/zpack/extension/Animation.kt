package io.github.achmadhafid.zpack.extension

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.transition.MaterialContainerTransform
import io.github.achmadhafid.zpack.extension.view.invisible
import io.github.achmadhafid.zpack.extension.view.visible

fun ViewGroup.containerTransition(views: Pair<View, View>, listenerAdapter: TransitionListenerAdapter? = null) {
    TransitionManager.beginDelayedTransition(this, MaterialContainerTransform().apply {
        scrimColor = Color.TRANSPARENT
        startView = views.first
        endView = views.second
        addTarget(views.second)
        listenerAdapter?.let {
            addListener(it)
        }
    })

    views.second.visible()
    views.first.invisible()
}

interface TransitionListenerAdapter : Transition.TransitionListener {
    override fun onTransitionStart(transition: Transition) = Unit
    override fun onTransitionEnd(transition: Transition) = Unit
    override fun onTransitionCancel(transition: Transition) = Unit
    override fun onTransitionPause(transition: Transition) = Unit
    override fun onTransitionResume(transition: Transition) = Unit
}

infix fun <T : View> T.fadingOutBy(durationInMilis: Long) {
    animate()
        .alpha(0F)
        .setDuration(durationInMilis)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                invisible()
            }
        })
}

fun ExtendedFloatingActionButton.changeText(
    @StringRes newTextRes: Int,
    @DrawableRes newIconRes: Int? = null
) {
    if (isShown && isExtended) {
        shrink(object : ExtendedFloatingActionButton.OnChangedCallback() {
            override fun onShrunken(extendedFab: ExtendedFloatingActionButton?) {
                setText(newTextRes)
                newIconRes?.let {
                    setIconResource(it)
                }
                extend()
            }
        })
    } else {
        setText(newTextRes)
        newIconRes?.let {
            setIconResource(it)
        }
        extend()
        show()
    }
}

fun ExtendedFloatingActionButton.changeText(
    newText: CharSequence,
    @DrawableRes newIconRes: Int? = null
) {
    if (isShown && isExtended) {
        shrink(object : ExtendedFloatingActionButton.OnChangedCallback() {
            override fun onShrunken(extendedFab: ExtendedFloatingActionButton?) {
                text = newText
                newIconRes?.let {
                    setIconResource(it)
                }
                extend()
            }
        })
    } else {
        text = newText
        newIconRes?.let {
            setIconResource(it)
        }
        extend()
        show()
    }
}
