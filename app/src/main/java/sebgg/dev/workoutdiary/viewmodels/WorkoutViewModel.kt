package sebgg.dev.workoutdiary.viewmodels

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import sebgg.dev.workoutdiary.database.ExerciseRepository
import sebgg.dev.workoutdiary.database.dao.Exercise
import sebgg.dev.workoutdiary.database.dao.Measurement
import sebgg.dev.workoutdiary.database.dao.Workout
import java.util.*

class WorkoutViewModel(private val repository: ExerciseRepository): ViewModel() {

    // Keeps track current workout id
    var currentWorkoutID: Int = 1

    // Live data passed to the recyclerview
    var currentList: LiveData<List<Exercise>> = repository.getExercises(currentWorkoutID).asLiveData()

    lateinit var date: String

    init {
        nukedb()
    }

    // Can be called to clear the database, mostly for testing and during development
    private fun nukedb() = viewModelScope.launch {
        repository.nukeDB()
    }

    fun setWID(wID: Int) {
        currentWorkoutID = wID
        updateCList()
    }

    private fun updateCList() {
        currentList = repository.getExercises(currentWorkoutID).asLiveData()
    }

    fun addExercise(exercise: Exercise) = viewModelScope.launch {
        repository.insertExercise(exercise)
    }

    fun addMeasurements(measurement: Measurement) = viewModelScope.launch {
        repository.insertMeasurement(measurement)
    }

    fun saveExercise() = viewModelScope.launch {
        val workout = Workout(currentWorkoutID, date = Date(), 0)
        repository.insertWorkout(workout)
        currentWorkoutID += 1
        updateCList()
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