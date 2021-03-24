package sebgg.dev.workoutdiary.ui.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import sebgg.dev.workoutdiary.R
import sebgg.dev.workoutdiary.activities.MainActivity
import sebgg.dev.workoutdiary.adapters.WorkoutAdapter
import sebgg.dev.workoutdiary.database.dao.Exercise
import sebgg.dev.workoutdiary.databinding.ExerciseDialogBinding
import sebgg.dev.workoutdiary.databinding.NewWorkoutFragmentBinding
import sebgg.dev.workoutdiary.helpers.showShortToast
import sebgg.dev.workoutdiary.viewmodels.WorkoutViewModel

class WorkoutFragment : Fragment() {
    companion object {
        fun newInstance() = WorkoutFragment()
    }

    private val viewModel: WorkoutViewModel by activityViewModels()
    private lateinit var wAdapter: WorkoutAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
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
            findNavController().navigate(
                    WorkoutFragmentDirections.actionWorkoutFragmentToMainFragment()
            )
//            (activity as MainActivity).finishWorkout()
        }

        binding.newWToolbar.setupWithNavController(findNavController())

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
        val calories = binding.inputExerciseWork.text.toString()
        val duration = binding.inputExerciseTime.text.toString()

        return if (name.isBlank() or weight.isBlank() or reps.isBlank()) {
            dialog.cancel()
            showShortToast(requireView(), "One input field was not filled in!")
            Exercise("-a", 0, 0,
                    0.0, 0.0, viewModel.currentWorkoutID)
        } else {
            showShortToast(requireView(), "$name added to exercises!")
            Exercise(name, weight.toInt(), reps.toInt(), calories.toDouble(),
                    duration.toDouble(), viewModel.currentWorkoutID)
        }
    }
}