package com.mufeng.mvvmlib.bean

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

data class IntentClass(
    var targetClass: Class<out AppCompatActivity>,
    var bundle: Bundle? = null,
    var isFinish: Boolean = false
)