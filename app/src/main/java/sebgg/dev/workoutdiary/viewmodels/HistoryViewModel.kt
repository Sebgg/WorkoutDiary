package sebgg.dev.workoutdiary.ui.history

import androidx.lifecycle.*
import sebgg.dev.workoutdiary.database.ExerciseRepository
import sebgg.dev.workoutdiary.database.dao.Workout

class HistoryViewModel(repository: ExerciseRepository): ViewModel() {
    var dummy: Int = 1
    val workouts: LiveData<List<Workout>> = repository.getAllWorkouts().asLiveData()
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