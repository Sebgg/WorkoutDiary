package sebgg.dev.workoutdiary

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import sebgg.dev.workoutdiary.database.ExerciseRepository
import sebgg.dev.workoutdiary.database.ExerciseRoomDatabase

class WorkoutApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { ExerciseRoomDatabase.getDatabase(this, applicationScope)}

    val repository by lazy { ExerciseRepository(
        database.exerciseDao(),
        database.workoutDao()
    ) }
}