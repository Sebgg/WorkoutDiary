package sebgg.dev.workoutdiary.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import sebgg.dev.workoutdiary.database.dao.*

class ExerciseRepository(
    private val exerciseDao: ExerciseDao,
    private val workoutDao: WorkoutDao,
    private val measurementDao: MeasurementDao
) {
    // Getters
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun getExercises(wid: Int): Flow<List<Exercise>> {
        return exerciseDao.loadAllByWorkout(wid)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun getDeadExercises(wID: Int): List<Exercise> {
        return exerciseDao.loadDeadByWorkout(wID)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun getAllExercises(): Flow<List<Exercise>> {
        return exerciseDao.getAll()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun getAllWorkouts(): Flow<List<Workout>> {
        return workoutDao.getAll()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getLatestWorkoutID(): Int {
        return workoutDao.getLatest()
    }

    // Inserters
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertExercise(exercise: Exercise) {
        exerciseDao.insert(exercise)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertWorkout(workout: Workout) {
        workoutDao.insert(workout)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertMeasurement(measurement: Measurement) {
        measurementDao.insert(measurement)
    }

    // Counters
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun countExercises(wID: Int): Int {
        return exerciseDao.countByWId(wID)
    }

    // Deleters
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun nukeDB() {
        workoutDao.deleteAll()
        exerciseDao.deleteAll()
    }
}