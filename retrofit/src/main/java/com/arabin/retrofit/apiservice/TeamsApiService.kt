package com.arabin.retrofit.apiservice

import com.arabin.retrofit.ApiEndPoints
import com.arabin.retrofit.model.BaseResponse
import retrofit2.http.GET

/**
 * @author Arabin
 * @since 12/28/2022
 * Api service place all api call here
 * @see [ApiEndPoints] holds all endpoints
 * */
interface TeamsApiService: ApiEndPoints {

    /**Get all schools*/
    @GET(ApiEndPoints.GET_TEAMS)
    suspend fun getTeams() : BaseResponse

}