package sebgg.dev.workoutdiary.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sebgg.dev.workoutdiary.MainActivity
import sebgg.dev.workoutdiary.R
import sebgg.dev.workoutdiary.database.dao.Workout
import sebgg.dev.workoutdiary.ui.fragments.HistoryFragment

class WorkoutHistoryAdapter(private val listener: OnItemClickListener)
    : ListAdapter<Workout, WorkoutHistoryAdapter.WorkoutViewHolder>(WorkoutComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        return WorkoutViewHolder.create(parent, listener)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class WorkoutViewHolder(view: View, private val listener: OnItemClickListener)
        : RecyclerView.ViewHolder(view), View.OnClickListener {
        // Workout date
        private val date: TextView = view.findViewById(R.id.history_workout_date)

        // Workout exercise count
        private val count: TextView = view.findViewById(R.id.history_workout_count)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

        companion object {
            fun create(parent: ViewGroup, listener: OnItemClickListener): WorkoutViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.history_workout_item, parent, false)

                return WorkoutViewHolder(view, listener)
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

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}