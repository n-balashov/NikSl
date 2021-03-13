package com.pavesid.niksl.ui.done

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavesid.niksl.data.DataRepository
import com.pavesid.niksl.data.model.Achievement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoneViewModel @Inject constructor(
    private val repository: DataRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _achievements = MutableLiveData<List<Achievement>>()
    val achievements: LiveData<List<Achievement>> = _achievements

    init {
        viewModelScope.launch(dispatcher) {
            _achievements.postValue(repository.getAllAchievements())
        }
    }
}
