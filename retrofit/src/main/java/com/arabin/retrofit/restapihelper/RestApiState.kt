package com.arabin.retrofit.restapihelper

import com.arabin.retrofit.model.BaseResponse


/**
 * @author Arabin
 * @since 12/16/2022
 * Gives api response
 * set data ans response state
 * */
sealed class RestAPIState {
    data class Success(val data: BaseResponse?, val message: String?): RestAPIState()
    data class Error(val exception: Exception, val message: String?): RestAPIState()
    data class Loading(val data: Any?, val message: String?): RestAPIState()
}