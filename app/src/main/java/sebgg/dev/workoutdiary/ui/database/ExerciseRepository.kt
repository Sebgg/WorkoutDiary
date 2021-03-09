package sebgg.dev.workoutdiary.ui.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import sebgg.dev.workoutdiary.ui.database.dao.Exercise
import sebgg.dev.workoutdiary.ui.database.dao.ExerciseDao
import sebgg.dev.workoutdiary.ui.database.dao.Workout
import sebgg.dev.workoutdiary.ui.database.dao.WorkoutDao

class ExerciseRepository(
    private val exerciseDao: ExerciseDao,
    private val workoutDao: WorkoutDao
) {
    fun getExercises(wid: Int): Flow<List<Exercise>> {
        return exerciseDao.loadAllByWorkout(wid)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getAllWorkouts(): List<Workout> {
        return workoutDao.getAll()
    }

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
    suspend fun nukeDB() {
        workoutDao.deleteAll()
        exerciseDao.deleteAll()
    }
}