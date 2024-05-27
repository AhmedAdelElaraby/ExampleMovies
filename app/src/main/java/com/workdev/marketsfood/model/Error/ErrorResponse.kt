package com.workdev.marketsfood.model.Error

import com.mg_group.womniz.ResponseDataClass.ErrorResponse.ErrorData

data class ErrorResponse(
    val status_code: Int,
    val status_message: String,
    val success: Boolean
)

