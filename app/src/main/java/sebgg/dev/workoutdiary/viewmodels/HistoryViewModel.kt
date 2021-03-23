package sebgg.dev.workoutdiary.viewmodels

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import sebgg.dev.workoutdiary.database.ExerciseRepository
import sebgg.dev.workoutdiary.database.dao.Exercise
import sebgg.dev.workoutdiary.database.dao.Workout

class HistoryViewModel(private val repository: ExerciseRepository): ViewModel() {
    val workouts: LiveData<List<Workout>> = repository.getAllWorkouts().asLiveData()

    lateinit var exercises: LiveData<List<Exercise>>

    fun updateExercisesById(wID: Int) {
        viewModelScope.launch {
            exercises = repository.getExercises(wID).asLiveData()
        }
    }
}

class HistoryViewModelFactory(private val repository: ExerciseRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create (modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HistoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}