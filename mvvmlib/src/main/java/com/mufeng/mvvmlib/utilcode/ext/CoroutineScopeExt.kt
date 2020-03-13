package com.mufeng.mvvmlib.utilcode.ext

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/6 18:02
 * @描述
 */
fun IOScope(): CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
