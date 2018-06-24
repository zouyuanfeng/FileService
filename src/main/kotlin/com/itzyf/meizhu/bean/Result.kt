package com.itzyf.meizhu.bean


/**
 * @author 依风听雨
 * @version 创建时间：2018/06/22 11:46
 */

data class Result<T>(var code: Int) {

    var data: T? = null
    var msg: String? = null
    val isSuccess: Boolean
        get() = code == 0
}