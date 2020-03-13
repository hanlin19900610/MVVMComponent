package com.mufeng.mvvmlib.utilcode.ext

import android.content.ClipData
import android.content.Context
import android.net.Uri
import android.content.Intent


/**
 * @创建者 MuFeng-T
 * @创建时间 2020/3/6 16:56
 * @描述
 */

/**
 * copyText  : 复制文本到剪贴板
 * getText   : 获取剪贴板的文本
 * copyUri   : 复制 uri 到剪贴板
 * getUri    : 获取剪贴板的 uri
 * copyIntent: 复制意图到剪贴板
 * getIntent : 获取剪贴板的意图
 */

/**
 * 复制文本到剪贴板
 *
 * @param text 文本
 */
fun Context.copyText(text: CharSequence) {
    clipboardManager?.setPrimaryClip(ClipData.newPlainText("text", text))
}

/**
 * 获取剪贴板的文本
 *
 * @return 剪贴板的文本
 */
fun Context.getText(): CharSequence? {
    val clip = clipboardManager?.primaryClip
    return if (clip != null && clip.itemCount > 0) {
        clip.getItemAt(0).coerceToText(this)
    } else null
}

/**
 * 复制uri到剪贴板
 *
 * @param uri uri
 */
fun Context.copyUri(uri: Uri) {
    clipboardManager?.setPrimaryClip(ClipData.newUri(contentResolver, "uri", uri))
}

/**
 * 获取剪贴板的uri
 *
 * @return 剪贴板的uri
 */
fun Context.getUri(): Uri? {
    val clip = clipboardManager?.primaryClip
    return if (clip != null && clip.itemCount > 0) {
        clip.getItemAt(0).uri
    } else null
}

/**
 * 复制意图到剪贴板
 *
 * @param intent 意图
 */
fun Context.copyIntent(intent: Intent) {
    clipboardManager?.setPrimaryClip(ClipData.newIntent("intent", intent))
}

/**
 * 获取剪贴板的意图
 *
 * @return 剪贴板的意图
 */
fun Context.getIntent(): Intent? {
    val clip = clipboardManager?.primaryClip
    return if (clip != null && clip.itemCount > 0) {
        clip.getItemAt(0).intent
    } else null
}