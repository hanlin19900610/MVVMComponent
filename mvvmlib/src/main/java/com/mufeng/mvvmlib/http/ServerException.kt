package com.mufeng.mvvmlib.http

class ServerException (throwable : Throwable,var code: Int): RuntimeException(throwable) {
}