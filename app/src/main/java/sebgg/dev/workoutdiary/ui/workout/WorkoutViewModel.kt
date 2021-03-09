package sebgg.dev.workoutdiary.ui.workout

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import sebgg.dev.workoutdiary.MainActivity
import sebgg.dev.workoutdiary.ui.database.ExerciseRepository
import sebgg.dev.workoutdiary.ui.database.dao.Exercise
import sebgg.dev.workoutdiary.ui.database.dao.Workout
import java.util.*

class WorkoutViewModel(private val repository: ExerciseRepository): ViewModel() {

    // Keeps track of all ids in current workout
    var currentWorkout: Int = 1

    // Live data passed to the recyclerview
    var currentList: LiveData<List<Exercise>> = repository.getExercises(currentWorkout).asLiveData()

    lateinit var date: String

    init {
        nukedb()
    }

    private fun nukedb() = viewModelScope.launch {
        repository.nukeDB()
    }

    fun addExercise(exercise: Exercise) = viewModelScope.launch {
        repository.insertExercise(exercise)
    }

    fun saveExercise() = viewModelScope.launch {
        val workout = Workout(currentWorkout, date = Date())
        repository.insertWorkout(workout)
        currentWorkout += 1
        currentList = repository.getExercises(currentWorkout).asLiveData()
    }
}

class WorkoutViewModelFactory(private val repository: ExerciseRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create (modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkoutViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WorkoutViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}