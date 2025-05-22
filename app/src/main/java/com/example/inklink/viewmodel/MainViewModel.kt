package com.example.inklink.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inklink.data.model.Usuario

class MainViewModel : ViewModel() {

    private val _usuario: MutableLiveData<Usuario?> = MutableLiveData()
    val usuario: LiveData<Usuario?> = _usuario

    // Si necesitas usar DataViewModel, hazlo en el MainActivity, no aquí.
}
