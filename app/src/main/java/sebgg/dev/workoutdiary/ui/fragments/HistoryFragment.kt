package sebgg.dev.workoutdiary.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import sebgg.dev.workoutdiary.activities.MainActivity
import sebgg.dev.workoutdiary.R
import sebgg.dev.workoutdiary.adapters.WorkoutAdapter
import sebgg.dev.workoutdiary.adapters.WorkoutHistoryAdapter
import sebgg.dev.workoutdiary.databinding.HistoryFragmentBinding
import sebgg.dev.workoutdiary.viewmodels.HistoryViewModel

class HistoryFragment: Fragment(), WorkoutHistoryAdapter.OnItemClickListener {

    companion object {
        fun newInstance() = HistoryFragment()
    }

    private val viewModel: HistoryViewModel by activityViewModels()
    private lateinit var binding: HistoryFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.history_fragment,
                container,
                false
        )

        binding.historyRecycler.adapter = WorkoutHistoryAdapter(this)

        viewModel.workouts.observe((activity as MainActivity)) {
            it.let {(binding.historyRecycler.adapter as WorkoutHistoryAdapter).submitList(it)}
        }

        binding.historyToolbar.setupWithNavController(findNavController())
//
//        binding.historyToolbar.setNavigationOnClickListener {
//            view?.findNavController()?.navigateUp()
//        }

        return binding.root
    }

    override fun onItemClick(position: Int) {
        val pos = position + 1
        findNavController().navigate(
                HistoryFragmentDirections.actionHistoryFragmentToSingleHistoryFragment(pos)
        )
//        val workout: Int = position + 1
//        binding.historyRecycler.adapter = WorkoutAdapter()
//
//        viewModel.updateExercisesById(workout)
//
//        viewModel.exercises.observe((activity as MainActivity)) {
//            it.let {(binding.historyRecycler.adapter as WorkoutAdapter).submitList(it)}
//        }
    }
}