package sebgg.dev.workoutdiary.ui.helpers

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class SStateViewModel(private val state: SavedStateHandle): ViewModel() {
    private val wIDKey: String = "latest_workout"

    fun getLatestWorkout(): Int {
        return if (state.contains(wIDKey)) {
            state.get(wIDKey)
        } else {
            0
        }!!
    }

    fun saveLatestWorkout(wID: Int) {
        state.set(wIDKey, wID)
    }
}