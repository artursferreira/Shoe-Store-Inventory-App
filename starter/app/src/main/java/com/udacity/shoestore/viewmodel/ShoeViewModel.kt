package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    var shoeItem = Shoe()
    private var shoesList = mutableListOf<Shoe>()

    private val _shoes = MutableLiveData<List<Shoe>>()
    val shoes: LiveData<List<Shoe>>
        get() = _shoes

    private val _detailReturnToList = MutableLiveData<Boolean>()
    val detailReturnToList: LiveData<Boolean>
        get() = _detailReturnToList


    private val _shouldShowWarning = MutableLiveData<Boolean>()
    val shouldShowWarning: LiveData<Boolean>
        get() = _shouldShowWarning

    fun addShoe() {
        if (shoeItem.isComplete()) {
            shoesList.add(shoeItem)
            _shoes.value = shoesList
            shoeItem = Shoe()
            _detailReturnToList.value = true
        } else {
            _shouldShowWarning.value = true
        }
    }

    fun onShoeAdded() {
        _detailReturnToList.value = false
    }

    fun onWarningShowed() {
        _shouldShowWarning.value = false
    }

}