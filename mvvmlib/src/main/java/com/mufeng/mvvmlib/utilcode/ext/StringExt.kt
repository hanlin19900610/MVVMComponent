package com.mufeng.mvvmlib.utilcode.ext

/**
 * @创建者 MuFeng-T
 * @创建时间 2020/3/6 16:03
 * @描述
 */

/**
 * if [String.isNullOrEmpty], invoke f()
 * otherwise invoke t()
 */
fun <T> String?.notNull(f: () -> T, t: () -> T): T {
    return if (isNullOrEmpty()) f() else t()
}

/**
 * whether string only contains digits
 */
fun String.areDigitsOnly() = matches(Regex("[0-9]+"))