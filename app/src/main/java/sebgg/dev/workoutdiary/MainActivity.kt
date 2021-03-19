package sebgg.dev.workoutdiary

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.SavedStateViewModelFactory
import sebgg.dev.workoutdiary.ui.fragments.HistoryFragment
import sebgg.dev.workoutdiary.ui.fragments.MainFragment
import sebgg.dev.workoutdiary.ui.fragments.WorkoutFragment
import sebgg.dev.workoutdiary.viewmodels.HistoryViewModel
import sebgg.dev.workoutdiary.viewmodels.HistoryViewModelFactory
import sebgg.dev.workoutdiary.viewmodels.WorkoutViewModel
import sebgg.dev.workoutdiary.viewmodels.WorkoutViewModelFactory
import sebgg.dev.workoutdiary.viewmodels.SStateViewModel
import java.util.*

class MainActivity : AppCompatActivity() {

    private val viewModel: WorkoutViewModel by viewModels {
        WorkoutViewModelFactory((application as WorkoutApplication).repository)
    }

    private val historyViewModel: HistoryViewModel by viewModels {
        HistoryViewModelFactory((application as WorkoutApplication).repository)
    }

    private val savedStateViewModel: SStateViewModel by viewModels {
        SavedStateViewModelFactory(application, this)
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
        viewModel.setWID(savedStateViewModel.getLatestWorkout() + 1)
        workoutFragment = WorkoutFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, workoutFragment)
        // TODO: add cool animation
            .commit()
    }

    fun finishWorkout() {
        savedStateViewModel.saveLatestWorkout(viewModel.currentWorkoutID)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment.newInstance())
            .commit()
    }

    fun showHistory() {
        // this is needed, otherwise it crashes
        historyViewModel.dummy = 0

        supportFragmentManager.beginTransaction()
                .replace(R.id.container, HistoryFragment.newInstance())
                .commit()
    }

    override fun onBackPressed() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment.newInstance())
            .commit()
    }

    // TODO: ask storage permission
}