package sebgg.dev.workoutdiary

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import sebgg.dev.workoutdiary.ui.history.HistoryFragment
import sebgg.dev.workoutdiary.ui.history.HistoryViewModel
import sebgg.dev.workoutdiary.ui.history.HistoryViewModelFactory
import sebgg.dev.workoutdiary.ui.main.MainFragment
import sebgg.dev.workoutdiary.ui.workout.WorkoutFragment
import sebgg.dev.workoutdiary.ui.workout.WorkoutViewModel
import sebgg.dev.workoutdiary.ui.workout.WorkoutViewModelFactory
import java.util.*

class MainActivity : AppCompatActivity() {

    private val viewModel: WorkoutViewModel by viewModels {
        WorkoutViewModelFactory((application as WorkoutApplication).repository)
    }

    private val historyViewModel: HistoryViewModel by viewModels {
        HistoryViewModelFactory((application as WorkoutApplication).repository)
    }

    private lateinit var workoutFragment: WorkoutFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }

    fun createNewWorkout() {
        viewModel.date = Date().toString()
        workoutFragment = WorkoutFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, workoutFragment)
        // TODO: add cool animation
            .commit()
    }

    fun showHistory() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, HistoryFragment.newInstance())
                .commit()
    }

    fun finishWorkout() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commit()
    }

    override fun onBackPressed() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment.newInstance())
            .commit()
    }

    // TODO: ask storage permission
}