package com.tunc.wayfolk.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavKey
import com.tunc.wayfolk.navigation.model.router.ComposeRouter
import com.tunc.wayfolk.navigation.model.router.ComposeTabScreenRouter


class TopLevelBackStack (
    startKey: ComposeRouter,
    private val homeTab: ComposeTabScreenRouter,
    private val onReselectTabAtRoot: (ComposeTabScreenRouter) -> Unit,
    private val onExit: () -> Unit
){

    private val topLevelStacks = linkedMapOf<NavKey, SnapshotStateList<NavKey>>(
        startKey to mutableStateListOf<NavKey>(startKey)
    )

    var topLevelKey by mutableStateOf<NavKey>(startKey)
        private set

    /** The single, flattened list rendered by [androidx.navigation3.ui.NavDisplay]. */
    val backStack: SnapshotStateList<NavKey> = mutableStateListOf(startKey)

    /** Currently selected bottom-navigation tab, or null while a non-tab root (e.g. splash) is shown. */
    val currentTab: ComposeTabScreenRouter?
        get() = topLevelKey as? ComposeTabScreenRouter

    private fun currentStack(): SnapshotStateList<NavKey> = topLevelStacks.getValue(topLevelKey)

    private fun updateBackStack() {
        backStack.apply {
            clear()
            addAll(topLevelStacks.values.flatten())
        }
    }

    /** Re-root the whole back stack (splash → home, or a session reset). Discards every tab stack. */
    fun setRoot(root: ComposeRouter) {
        topLevelStacks.clear()
        topLevelStacks[root] = mutableStateListOf<NavKey>(root)
        topLevelKey = root
        updateBackStack()
    }

    fun add(key: ComposeRouter) {
        currentStack().add(key)
        updateBackStack()
    }

    /** Replaces the top entry of the current stack (Nav2 `isReplaceScreen`). */
    fun replaceTop(key: ComposeRouter) {
        currentStack().apply {
            removeLastOrNull()
            add(key)
        }
        updateBackStack()
    }

    /** Pops the current stack up to the last entry whose type matches [target] (Nav2 `popUpTo(route)`). */
    fun popUpTo(target: ComposeRouter, inclusive: Boolean) {
        val stack = currentStack()
        val index = stack.indexOfLast { it::class == target::class }
        if (index < 0) return
        val keepCount = if (inclusive) index else index + 1
        while (stack.size > keepCount) {
            stack.removeAt(stack.lastIndex)
        }
        updateBackStack()
    }

    /** Switches to a bottom-navigation tab, reproducing Nav2's `navigateToTab`. */
    fun switchTab(
        target: ComposeTabScreenRouter,
        clearToRoot: Boolean,
        popupRouter: ComposeRouter?
    ) {
        popupRouter?.let { popUpTo(it, inclusive = false) }

        val isSameTab = topLevelKey == target
        val existingStack = topLevelStacks[target]

        when {
            clearToRoot -> {
                topLevelStacks.remove(target)
                topLevelStacks[target] = mutableStateListOf<NavKey>(target)
                topLevelKey = target
            }

            isSameTab -> {
                val stack = existingStack ?: mutableStateListOf<NavKey>(target).also {
                    topLevelStacks[target] = it
                }
                if (stack.size <= 1) {
                    onReselectTabAtRoot(target)
                } else {
                    while (stack.size > 1) stack.removeAt(stack.lastIndex)
                }
            }

            existingStack == null -> {
                topLevelStacks[target] = mutableStateListOf<NavKey>(target)
                topLevelKey = target
            }

            else -> {
                // Move the already-visited tab to the end so it renders on top, keeping its inner stack.
                topLevelStacks.remove(target)
                topLevelStacks[target] = existingStack
                topLevelKey = target
            }
        }
        updateBackStack()
    }

    /** System back / NavigateUp. Returns having mutated the stack, or invokes [onExit] when nothing is left. */
    fun onSystemBack() {
        val stack = currentStack()
        if (stack.size > 1) {
            stack.removeAt(stack.lastIndex)
            updateBackStack()
            return
        }

        // At the root of the current top-level stack.
        if (topLevelKey == homeTab) {
            onExit()
            return
        }

        topLevelStacks.remove(topLevelKey)
        val previous = topLevelStacks.keys.lastOrNull()
        if (previous == null) {
            // Nothing left (e.g. a non-tab splash root) – restore and exit.
            topLevelStacks[topLevelKey] = stack
            onExit()
            return
        }
        topLevelKey = previous
        updateBackStack()
    }
}