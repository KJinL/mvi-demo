package com.kajin.model.response

data class BaseResponse<T>(
    val code: Int,
    val msg: String,
    val data: T
)
