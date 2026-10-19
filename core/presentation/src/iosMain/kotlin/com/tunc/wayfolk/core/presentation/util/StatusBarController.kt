package com.tunc.wayfolk.core.presentation.util

object StatusBarController {
    private var visible = true
    private var listener: ((Boolean) -> Unit)? = null

    fun setListener(listener: (Boolean) -> Unit) {
        this.listener = listener
        listener(visible)
    }

    internal fun update(visible: Boolean) {
        if (this.visible == visible) return
        this.visible = visible
        listener?.invoke(visible)
    }
}
