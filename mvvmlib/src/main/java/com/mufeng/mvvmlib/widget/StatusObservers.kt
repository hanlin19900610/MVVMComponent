package com.mufeng.mvvmlib.widget

import androidx.lifecycle.Observer

class StatefulLayoutObserver(private val statefulLayout: StatefulLayout) : Observer<State> {
    override fun onChanged(state: State) {
        statefulLayout.state = state
    }
}


class StatefulMessageObserver(private val statefulLayout: StatefulLayout) : Observer<Pair<State, String>> {
    override fun onChanged(pair: Pair<State, String>) {
        val (status, message) = pair
        statefulLayout.state = status
        when (status) {
            State.Empty -> statefulLayout.setEmptyText(message)
            State.Loading -> statefulLayout.setLoadingText(message)
            State.Failure -> statefulLayout.setFailureText(message)
            State.Success -> Unit
        }
    }
}