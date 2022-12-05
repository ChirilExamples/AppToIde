package com.example.apptoide.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptoide.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelATI @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _itemsList: MutableStateFlow<Resource<List<com.example.apptoide.data.structure.List>>> =
        MutableStateFlow(Resource.Success(listOf()))
    val itemsList: StateFlow<Resource<List<com.example.apptoide.data.structure.List>>> = _itemsList

    fun getDataATI() = viewModelScope.launch(Dispatchers.IO) {
        if (_itemsList.value is Resource.Loading) return@launch
        repository.getAllData(_itemsList.value.data!!).collectLatest {
            _itemsList.emit(it)
        }
    }
}
