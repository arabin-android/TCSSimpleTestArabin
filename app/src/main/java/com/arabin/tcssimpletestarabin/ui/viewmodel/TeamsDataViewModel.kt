package com.arabin.tcssimpletestarabin.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arabin.retrofit.model.BaseResponse
import com.arabin.retrofit.restapihelper.RestAPIState
import com.arabin.tcssimpletestarabin.respository.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * @author Arabin
 * @since 12/16/2022
 * Main view model to communicate with Main Repo
 * calls db queries and API's
 * */
@HiltViewModel
class TeamsDataViewModel @Inject constructor(private val mainRepo: MainRepo) : ViewModel() {

    /**live school list data to be observed in UI*/
    private val _localSchoolData = MutableLiveData<Event<RestAPIState>>()
    var localSchoolData: LiveData<Event<RestAPIState>> =
        _localSchoolData


    init {
        loadSchoolDetails()
    }


    /** 1st check if data already populated in roomDb or not
     * if not fetch api and insert it to roomdb
     * runs in background thread both operations api and room insertion*/
    fun loadSchoolDetails() {
        viewModelScope.launch (Dispatchers.IO){
            _localSchoolData.postValue(Event(RestAPIState.Loading(null, "Loading")))
            try {
                _localSchoolData.postValue(Event(RestAPIState.Success(mainRepo.getTeamsDetails(), "Success")))
            } catch (e: Exception) {
                _localSchoolData.postValue(Event(RestAPIState.Error(e, "Error")))
            }
        }
    }
}