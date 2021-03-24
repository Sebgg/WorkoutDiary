package sebgg.dev.workoutdiary.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "exercises")
data class Exercise(
        @ColumnInfo(name = "exercise_name") val exerciseName: String,
        @ColumnInfo(name = "exercise_weight") val exerciseWeight: Int?,
        @ColumnInfo(name = "exercise_reps") val exerciseReps: Int?,
        @ColumnInfo(name = "exercise_work") val exerciseWork: Double?,
        @ColumnInfo(name = "exercise_duration") val exerciseDuration: Double?,
        @ColumnInfo(name = "workout_id") val workoutId: Int
) {
    @PrimaryKey(autoGenerate = true) var uid: Int = 0
}

@Dao
interface ExerciseDao {
    // getters
    @Query("SELECT * FROM exercises")
    fun getAll(): Flow<List<Exercise>>

    @Query("SELECT * FROM exercises WHERE workout_id == (:wID)")
    fun loadAllByWorkout(wID: Int): Flow<List<Exercise>>

    @Query("SELECT * FROM exercises WHERE workout_id == (:wID)")
    fun loadDeadByWorkout(wID: Int): List<Exercise>

    // Inserters
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg exercises: Exercise)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(exercise: Exercise)

    // Deleters
    @Query("DELETE FROM exercises")
    suspend fun deleteAll()

    // Counters
    @Query("SELECT count(*) FROM exercises WHERE workout_id == (:wID)")
    suspend fun countByWId(wID: Int): Int
}