package com.arabin.tcssimpletestarabin.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.arabin.retrofit.model.TeamDetails
import com.arabin.retrofit.restapihelper.RestAPIState
import com.arabin.tcssimpletestarabin.R
import com.arabin.tcssimpletestarabin.databinding.ActivityMainBinding
import com.arabin.tcssimpletestarabin.databinding.ActivityMainBindingImpl
import com.arabin.tcssimpletestarabin.ui.viewmodel.TeamsDataViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: TeamsDataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }


    /**
     * Observe data here
     * */
    override fun onResume() {
        super.onResume()
        mainViewModel.localSchoolData.observe(this) { apiState ->
            apiState.getContentIfNotHandled()?.let { it ->
                when (it) {
                    is RestAPIState.Success -> {
                        if (it.data?.data?.isNotEmpty() == true)
                            setItems(it.data?.data!!)
                    }
                    is RestAPIState.Error -> {
                    }
                    is RestAPIState.Loading -> {
                    }
                }
            }
        }
    }

    /**
     * set data to spinner adapter
     * and on selection
     * */
    private fun setItems(data: List<TeamDetails>) {
        val items = mutableListOf<String>()
        data.forEach {
            it.name?.let { it1 -> items.add(it1) }
        }
        binding.teamNames.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        binding.teamNames.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                updateUi(data[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    /**
     * update ui using databinding
     * */
    private fun updateUi(teamDetails: TeamDetails) {
        binding.teamDetails = teamDetails
        binding.executePendingBindings()
    }

}