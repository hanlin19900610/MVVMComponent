package com.mufeng.mvvmlib.utilcode.utils

import android.app.Activity
import java.util.*
import android.content.Intent
import androidx.annotation.NonNull



/**
 * @创建者 MuFeng-T
 * @创建时间 2020/3/6 15:13
 * @描述
 */
object ActivityUtils {

    private val mActivityList = LinkedList<Activity>()

    /**
     * 获取当前Activity
     */
    val currentActivity: Activity?
        get() =
            if (mActivityList.isEmpty()) null
            else mActivityList.last

    /**
     * push the specified [activity] into the list
     * @param activity Activity
     */
    fun pushActivity(activity: Activity) {
        if (mActivityList.contains(activity)) {
            if (mActivityList.last != activity) {
                mActivityList.remove(activity)
                mActivityList.add(activity)
            }
        }else {
            mActivityList.add(activity)
        }
    }

    /**
     * pop the specified [activity] into the list
     * @param activity Activity
     */
    fun popActivity(activity: Activity) {
        mActivityList.remove(activity)
    }

    /**
     * 结束当前Activity
     */
    fun finishCurrentActivity() {
        currentActivity?.finish()
    }

    /**
     * 结束Activity
     * @param activity Activity
     */
    fun finishActivity(activity: Activity) {
        mActivityList.remove(activity)
        activity.finish()
    }

    /**
     * 结束指定Activity
     * @param clazz Class<*>
     */
    fun finishActivity(clazz: Class<*>) {
        mActivityList.forEach {
            if (it.javaClass == clazz){
                it.finish()
            }
        }
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        mActivityList.forEach {
            it.finish()
        }
    }

    /**
     * 返回Activity是否存在
     * @param pkg String
     * @param cls String
     * @return Boolean
     */
    fun isActivityExists(
        pkg: String,
        cls: String
    ): Boolean {
        val intent = Intent()
        intent.setClassName(pkg, cls)
        return !(context.packageManager.resolveActivity(intent, 0) == null ||
                intent.resolveActivity(context.packageManager) == null ||
                context.packageManager.queryIntentActivities(intent, 0).size === 0)
    }



}