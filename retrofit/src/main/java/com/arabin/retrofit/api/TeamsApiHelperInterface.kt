package com.arabin.retrofit.api

import com.arabin.retrofit.model.BaseResponse


/**
 * @author Arabin
 * @since 12/28/2022
 * An interface that holds
 * all api call abstraction
 * @see [TeamsApiHelperImpl]
 * */
interface TeamsApiHelperInterface {
    suspend fun getTeams(): BaseResponse
}