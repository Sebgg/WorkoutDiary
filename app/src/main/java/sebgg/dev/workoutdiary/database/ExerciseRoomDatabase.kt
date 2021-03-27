package sebgg.dev.workoutdiary.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import sebgg.dev.workoutdiary.database.dao.*

@Database(entities = [Exercise::class, Workout::class, Measurement::class], version = 5, exportSchema = false)
    abstract class ExerciseRoomDatabase:  RoomDatabase(){

    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun measurementDao(): MeasurementDao

    companion object {
        // Singleton instance of database
        @Volatile
        private var INSTANCE: ExerciseRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ExerciseRoomDatabase {

            // if instance isn't null, return it. Else
            // create the database and return instance
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExerciseRoomDatabase::class.java,
                    "workout_database"
                )
                .addCallback(ExerciseDatabaseCallback(scope))
                .addMigrations(MIGRATION_3_4)
                .fallbackToDestructiveMigration() // THIS IS ONLY FOR PRE-RELEASE DEV
                .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class ExerciseDatabaseCallback(
            private val scope: CoroutineScope
    ): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { _ ->
                scope.launch {
                    // Read in latest workout id and so forth
                }
            }
        }
    }
}