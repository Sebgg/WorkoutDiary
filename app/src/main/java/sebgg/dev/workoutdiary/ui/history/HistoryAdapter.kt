package sebgg.dev.workoutdiary.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import sebgg.dev.workoutdiary.R
import sebgg.dev.workoutdiary.WorkoutApplication
import sebgg.dev.workoutdiary.ui.database.dao.Exercise
import sebgg.dev.workoutdiary.ui.database.dao.Workout

class HistoryAdapter: ListAdapter<Workout, HistoryAdapter.WorkoutViewHolder>(WorkoutComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        return WorkoutViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class WorkoutViewHolder(view: View): RecyclerView.ViewHolder(view) {
        // Workout date
        private val date: TextView = view.findViewById(R.id.history_workout_date)

        // Workout exercise count
        private val count: TextView = view.findViewById(R.id.history_workout_count)

        companion object {
            fun create(parent: ViewGroup): WorkoutViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.history_workout_item, parent, false)
                return WorkoutViewHolder(view)
            }
        }

        fun bind(workout: Workout) {
            date.text = workout.date.toString() // TODO: add formatting for this
            count.text = "0" // TODO: this should get the length of the list from
        }
    }

    class WorkoutComparator: DiffUtil.ItemCallback<Workout>() {
        override fun areItemsTheSame(oldItem: Workout, newItem: Workout): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Workout, newItem: Workout): Boolean {
            return oldItem.uid == newItem.uid && oldItem.date == newItem.date
        }
    }
}