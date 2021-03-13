package com.pavesid.niksl.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavesid.niksl.data.DataRepository
import com.pavesid.niksl.data.model.Achievement
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: DataRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _notViewedAchievements = repository.getNotViewedAchievements()
    val notViewedAchievements: LiveData<List<Achievement>> = _notViewedAchievements

    private val _doneAchievements = repository.getDoneAchievements()
    val doneAchievements: LiveData<List<Achievement>> = _doneAchievements

    private val _notYetAchievements = repository.getNotYetAchievements()
    val notYetAchievements: LiveData<List<Achievement>> = _notYetAchievements

//    fun loadDoneAchievements() = viewModelScope.launch(dispatcher) {
//        _doneAchievements.postValue(repository.getDoneAchievements())
//    }
//
//    fun loadNotYetAchievements() = viewModelScope.launch(dispatcher) {
//        _notYetAchievements.postValue(repository.getNotYetAchievements())
//    }
//
//    fun loadNotViewedAchievements() = viewModelScope.launch(dispatcher) {
//        _notViewedAchievements.postValue(repository.getNotViewedAchievements())
//    }

    fun updateAchievement(done: Boolean, id: Long) = viewModelScope.launch(dispatcher) {
        repository.updateAchievement(done, id)
    }
}
