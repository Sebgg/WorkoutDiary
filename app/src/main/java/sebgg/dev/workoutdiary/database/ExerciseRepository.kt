package sebgg.dev.workoutdiary.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import sebgg.dev.workoutdiary.database.dao.Exercise
import sebgg.dev.workoutdiary.database.dao.ExerciseDao
import sebgg.dev.workoutdiary.database.dao.Workout
import sebgg.dev.workoutdiary.database.dao.WorkoutDao

class ExerciseRepository(
    private val exerciseDao: ExerciseDao,
    private val workoutDao: WorkoutDao
) {
    // Getters
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun getExercises(wid: Int): Flow<List<Exercise>> {
        return exerciseDao.loadAllByWorkout(wid)
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

    // Counters
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun countExercises(wid: Int): Int {
        return exerciseDao.countByWId(wid)
    }

    // Deleters
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun nukeDB() {
        workoutDao.deleteAll()
        exerciseDao.deleteAll()
    }
}