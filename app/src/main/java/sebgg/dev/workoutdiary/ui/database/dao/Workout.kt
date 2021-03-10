package sebgg.dev.workoutdiary.ui.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import sebgg.dev.workoutdiary.ui.database.converters.DateConverter
import java.util.*

@Entity(tableName = "workouts")
@TypeConverters(DateConverter::class)
data class Workout(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "date") val date: Date?
)  {
}

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM workouts")
    fun getAll(): Flow<List<Workout>>

    @Query("SELECT * FROM workouts WHERE uid == (:date)")
    suspend fun getAllByDate(date: String): List<Workout>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg workouts: Workout)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(workout: Workout)

    @Delete
    fun delete(workout: Workout)

    @Query("DELETE FROM workouts")
    suspend fun deleteAll()
}