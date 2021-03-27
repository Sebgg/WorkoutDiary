package sebgg.dev.workoutdiary.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_3_4 = object: Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE `measurements` (" +
                "`uid` INTEGER, " +
                "`useMetric` BOOLEAN, " +
                "`upperArm` REAL, " +
                "`lowerArm` REAL, " +
                "`chest` REAL, " +
                "`shoulders` REAL, " +
                "`waist` REAL, PRIMARY KEY(`uid`))")

        database.execSQL("ALTER TABLE workouts ADD COLUMN" +
                " measurementsID INTEGER")
    }
}