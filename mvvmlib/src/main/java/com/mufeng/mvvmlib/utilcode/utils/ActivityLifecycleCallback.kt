package com.mufeng.mvvmlib.utilcode.utils

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.mufeng.mvvmlib.utilcode.ext.logd

/**
 * @创建者 MuFeng-T
 * @创建时间 2020/3/6 15:22
 * @描述
 */
class ActivityLifecycleCallback : Application.ActivityLifecycleCallbacks {
    override fun onActivityPaused(activity: Activity) {
        logd { "onActivityPaused : ${activity.localClassName}" }
    }

    override fun onActivityStarted(activity: Activity) {
        logd { "onActivityStarted : ${activity.localClassName}" }
    }

    override fun onActivityDestroyed(activity: Activity) {
        logd { "onActivityDestroyed : ${activity.localClassName}" }
        ActivityUtils.finishActivity(activity)
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityStopped(activity: Activity) {
        logd { "onActivityStopped : ${activity.localClassName}" }
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        ActivityUtils.pushActivity(activity)
    }

    override fun onActivityResumed(activity: Activity) {
        logd { "onActivityResumed : ${activity.localClassName}" }
    }
}