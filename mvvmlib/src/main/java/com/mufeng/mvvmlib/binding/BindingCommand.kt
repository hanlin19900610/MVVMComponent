package com.mufeng.mvvmlib.binding

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/10/16 20:19
 * @描述
 */
interface BindingCommand{
    fun apply()
}
interface BindingCommand1<T1> {
    fun apply(t1: T1)
}

interface BindingCommand2<T1, T2> {
    fun apply(t1: T1, t2: T2)
}

interface BindingCommand3<T1, T2, T3> {
    fun apply(t1: T1, t2: T2, t3: T3)
}

interface BindingCommand4<T1, T2, T3, T4> {
    fun apply(t1: T1, t2: T2, t3: T3, t4: T4)
}

interface BindingCommand5<T1, T2, T3, T4, T5> {
    fun apply(t1: T1, t2: T2, t3: T3, t4: T4, t5: T5)
}

interface BindingCommand6<T1, T2, T3, T4, T5, T6> {
    fun apply(t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6)
}

interface BindingCommand7<T1, T2, T3, T4, T5, T6, T7> {
    fun apply(t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6, t7: T7)
}

interface BindingCommand8<T1, T2, T3, T4, T5, T6, T7, T8> {
    fun apply(t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6, t7: T7, t8: T8)
}

interface BindingCommand9<T1, T2, T3, T4, T5, T6, T7, T8, T9> {
    fun apply(t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6, t7: T7, t8: T8, t9: T9)
}