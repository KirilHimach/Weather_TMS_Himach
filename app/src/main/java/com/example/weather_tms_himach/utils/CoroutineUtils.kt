package com.example.weather_tms_himach.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

inline fun <reified T> Flow<T>.observeWithLifecycle(
    fragment: Fragment,
    noinline action: suspend (T) -> Unit,
    mainActiveState: Lifecycle.State = Lifecycle.State.STARTED
): Job = fragment.viewLifecycleOwner.lifecycleScope.launch {
    flowWithLifecycle(fragment.viewLifecycleOwner.lifecycle, mainActiveState).collect() {
        action(it)
    }
}