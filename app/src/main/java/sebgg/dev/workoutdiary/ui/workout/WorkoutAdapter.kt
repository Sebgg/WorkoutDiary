package sebgg.dev.workoutdiary.ui.workout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sebgg.dev.workoutdiary.R
import sebgg.dev.workoutdiary.ui.database.dao.Exercise

//class WorkoutAdapter(private var dataSet: MutableList<Exercise>):
//        RecyclerView.Adapter<WorkoutAdapter.ViewHolder>() {
//
////    private val dataSet = listOf<Exercise>()
//    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
//        // Exercise name
//        private val name: TextView
//
//        // Exercise weight
//        private val weight: TextView
//
//        // Exercise reps
//        private val reps: TextView
//
//        init {
//            name = view.findViewById(R.id.exercise_name)
//            weight = view.findViewById(R.id.exercise_weight)
//            reps = view.findViewById(R.id.exercise_reps)
//        }
//
//        fun bind(exercise: Exercise) {
//            name.text = exercise.exerciseName
//            weight.text = exercise.exerciseWeight.toString()
//            reps.text = exercise.exerciseReps.toString()
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.exercise, parent, false)
//
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = dataSet[position]
//        holder.bind(item)
//    }
//
//    override fun getItemCount() = dataSet.size
//}

class WorkoutAdapter: ListAdapter<Exercise, WorkoutAdapter.ExerciseViewHolder>(ExerciseComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        return ExerciseViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class ExerciseViewHolder(view: View): RecyclerView.ViewHolder(view) {
        // Exercise name
        private val name: TextView = view.findViewById(R.id.exercise_name)

        // Exercise weight
        private val weight: TextView = view.findViewById(R.id.exercise_weight)

        // Exercise reps
        private val reps: TextView = view.findViewById(R.id.exercise_reps)

        companion object {
            fun create(parent: ViewGroup): ExerciseViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.exercise, parent, false)
                return ExerciseViewHolder(view)
            }
        }

        fun bind(exercise: Exercise) {
            name.text = exercise.exerciseName
            weight.text = exercise.exerciseWeight.toString()
            reps.text = exercise.exerciseReps.toString()
        }
    }

    class ExerciseComparator: DiffUtil.ItemCallback<Exercise>() {
        override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem.exerciseName == newItem.exerciseName
                    && oldItem.exerciseWeight == newItem.exerciseWeight
                    && oldItem.exerciseReps == newItem.exerciseReps
        }
    }
}