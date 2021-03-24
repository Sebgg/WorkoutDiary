package sebgg.dev.workoutdiary.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.SavedStateViewModelFactory
import sebgg.dev.workoutdiary.R
import sebgg.dev.workoutdiary.WorkoutApplication
import sebgg.dev.workoutdiary.viewmodels.*

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        viewModel.setWID(savedStateViewModel.getLatestWorkout()+1)
        historyViewModel.workouts
    }

    // TODO: ask storage permission?
}