package com.mufeng.mvvmlib.binding

import android.annotation.SuppressLint
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import com.jakewharton.rxbinding3.appcompat.itemClicks
import com.jakewharton.rxbinding3.appcompat.navigationClicks
import io.reactivex.functions.Consumer
import java.util.concurrent.TimeUnit

interface ToolbarMenuItemClicks: Consumer<MenuItem>

@SuppressLint("CheckResult")
@BindingAdapter("bind_toolbar_navigationClicks")
fun setNavigationClicks(toolbar: Toolbar,command: ViewClickCommand){
    toolbar.navigationClicks()
        .throttleFirst(1,TimeUnit.SECONDS)
        .subscribe {
            command.accept(toolbar)
        }
}

@SuppressLint("CheckResult")
@BindingAdapter("bind_toolbar_itemClicks")
fun setMenuItemClicks(toolbar: Toolbar, command: ToolbarMenuItemClicks){
    toolbar.itemClicks()
        .subscribe { item ->
            command.accept(item)
        }
}