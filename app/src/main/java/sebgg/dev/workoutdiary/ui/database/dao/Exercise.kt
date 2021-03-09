package sebgg.dev.workoutdiary.ui.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "exercises")
data class Exercise(
        @ColumnInfo(name = "exercise_name") val exerciseName: String,
        @ColumnInfo(name = "exercise_weight") val exerciseWeight: Int,
        @ColumnInfo(name = "exercise_reps") val exerciseReps: Int,
        @ColumnInfo(name = "workout_id") val workoutId: Int
) {
    @PrimaryKey(autoGenerate = true) var uid: Int = 0
}

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercises")
    fun getAll(): List<Exercise>

    @Query("DELETE FROM exercises")
    suspend fun deleteAll()

    @Query("SELECT * FROM exercises WHERE workout_id == (:wid)")
    fun loadAllByWorkout(wid: Int): Flow<List<Exercise>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg exercises: Exercise)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(exercise: Exercise)
}