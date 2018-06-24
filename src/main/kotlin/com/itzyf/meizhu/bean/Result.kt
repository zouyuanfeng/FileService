package com.itzyf.meizhu.bean


/**
 * @author 依风听雨
 * @version 创建时间：2018/06/22 11:46
 */

data class Result<T>(var code: Int = 200, var msg: String = "") {

    var data: T? = null
}