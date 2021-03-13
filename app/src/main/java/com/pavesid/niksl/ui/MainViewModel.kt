package com.pavesid.niksl.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavesid.niksl.data.DataRepository
import com.pavesid.niksl.data.model.Achievement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: DataRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    val notViewedAchievements: LiveData<List<Achievement>> = repository.getNotViewedAchievements()

    val doneAchievements: LiveData<List<Achievement>> = repository.getDoneAchievements()

    val notYetAchievements: LiveData<List<Achievement>> = repository.getNotYetAchievements()

    fun updateAchievement(done: Boolean, id: Long) = viewModelScope.launch(dispatcher) {
        repository.updateAchievement(done, id)
    }
}
