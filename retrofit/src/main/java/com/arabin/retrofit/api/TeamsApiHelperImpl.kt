package com.arabin.retrofit.api

import com.arabin.retrofit.apiservice.TeamsApiService
import com.arabin.retrofit.model.BaseResponse
import javax.inject.Inject

/**
 * @author Arabin
 * @since 12/28/2022
 * Implements [TeamsApiHelperInterface]
 * Dependency injected constructor injection
 * calls api service to get result
 * @see [TeamsApiService]
 * */
class TeamsApiHelperImpl @Inject constructor(private val apiSvc: TeamsApiService):
    TeamsApiHelperInterface {

    /**A suspend function to get school details response must run under coroutine*/
    override suspend fun getTeams(): BaseResponse = apiSvc.getTeams()


}