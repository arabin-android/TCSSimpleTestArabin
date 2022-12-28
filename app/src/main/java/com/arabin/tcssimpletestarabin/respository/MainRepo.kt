package com.arabin.tcssimpletestarabin.respository

import com.arabin.retrofit.api.TeamsApiHelperInterface
import javax.inject.Inject


/**
 * @author Arabin
 * @since 12/16/2022
 * Main repo this should
 * communicate with viewModel
 * MVVM, hilt injected
 * check dependency graph to see the tree
 * */
class MainRepo @Inject constructor(
    private val teamsApiHelper: TeamsApiHelperInterface,
    ){

    /**API call's to get data from server*/
    suspend fun getTeamsDetails() = teamsApiHelper.getTeams()
}