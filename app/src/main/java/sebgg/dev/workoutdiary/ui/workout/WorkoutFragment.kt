package sebgg.dev.workoutdiary.ui.workout

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import sebgg.dev.workoutdiary.MainActivity
import sebgg.dev.workoutdiary.R
import sebgg.dev.workoutdiary.databinding.ExerciseDialogBinding
import sebgg.dev.workoutdiary.databinding.NewWorkoutFragmentBinding
import sebgg.dev.workoutdiary.ui.database.dao.Exercise
import sebgg.dev.workoutdiary.ui.helpers.showShortToast

class WorkoutFragment : Fragment() {
    companion object {
        fun newInstance() = WorkoutFragment()
    }

    private val viewModel: WorkoutViewModel by activityViewModels()
    private lateinit var wAdapter: WorkoutAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<NewWorkoutFragmentBinding>(
            inflater,
            R.layout.new_workout_fragment,
            container,
            false
        )

        binding.buttonAddExercise.setOnClickListener {
            addExercise(inflater)
        }

        binding.buttonFinishWorkout.setOnClickListener {
            viewModel.saveExercise()
            (activity as MainActivity).finishWorkout()
        }

        wAdapter = WorkoutAdapter()
        viewModel.currentList.observe((activity as MainActivity)) { exercises ->
            exercises.let { wAdapter.submitList(it) }
        }
        binding.newWRecycler.adapter = wAdapter

        return binding.root
    }

    private fun addExercise(inflater: LayoutInflater) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle(getString(R.string.exercise_dialog_title))

        val binding = DataBindingUtil.inflate<ExerciseDialogBinding>(
                inflater,
                R.layout.exercise_dialog,
                view as ViewGroup,
                false
        )

        builder.setView(binding.root)

        builder.setPositiveButton(
                android.R.string.ok,
            fun(dialog: DialogInterface, _: Number) {
                dialog.dismiss()
                val exercise = createExercise(binding, dialog)

                if (exercise.exerciseName != "-a") {
                    viewModel.addExercise(exercise)
                }
            })

        builder.setNegativeButton(
                "Cancel",
                fun(dialog: DialogInterface, _: Number) {
                    dialog.cancel()
                }
        )

        builder.show()
    }

    private fun createExercise(binding: ExerciseDialogBinding, dialog: DialogInterface): Exercise {
        val name = binding.inputExerciseName.text.toString()
        val weight = binding.inputExerciseWeight.text.toString()
        val reps = binding.inputExerciseReps.text.toString()

        return if (name.isBlank() or weight.isBlank() or reps.isBlank()) {
            dialog.cancel()
            showShortToast(requireView(), "One input field was not filled in!")
            Exercise("-a", 0, 0, viewModel.currentWorkout)
        } else {
            Exercise(name, weight.toInt(), reps.toInt(), viewModel.currentWorkout)
        }
    }
}