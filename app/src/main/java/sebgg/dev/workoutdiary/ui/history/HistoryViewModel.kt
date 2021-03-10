package sebgg.dev.workoutdiary.ui.history

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import sebgg.dev.workoutdiary.ui.database.ExerciseRepository
import sebgg.dev.workoutdiary.ui.database.dao.Workout
import sebgg.dev.workoutdiary.ui.workout.WorkoutViewModel

class HistoryViewModel(private val repository: ExerciseRepository): ViewModel() {

    val workouts: LiveData<List<Workout>> = repository.getAllWorkouts().asLiveData()
}

class HistoryViewModelFactory(private val repository: ExerciseRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create (modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WorkoutViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}