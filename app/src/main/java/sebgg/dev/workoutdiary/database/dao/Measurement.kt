package sebgg.dev.workoutdiary.database.dao

import androidx.room.*

@Entity(tableName = "measurements")
data class Measurement(
        val useMetric: Boolean,

        val upperArm: Double?,

        val lowerArm: Double?,

        val chest: Double?,

        val shoulders: Double?,

        val waist: Double?
) {
        @PrimaryKey(autoGenerate = true) var uid: Int = 0
}

@Dao
interface MeasurementDao {
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(measurement: Measurement)
}