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
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import sebgg.dev.workoutdiary.R
import sebgg.dev.workoutdiary.activities.MainActivity
import sebgg.dev.workoutdiary.adapters.WorkoutAdapter
import sebgg.dev.workoutdiary.databinding.HistoryFragmentBinding
import sebgg.dev.workoutdiary.viewmodels.HistoryViewModel

class SingleHistoryFragment: Fragment() {

    private val viewModel: HistoryViewModel by activityViewModels()
    private val args: SingleHistoryFragmentArgs by navArgs()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = DataBindingUtil.inflate<HistoryFragmentBinding>(
                inflater,
                R.layout.history_fragment,
                container,
                false
        )

        binding.historyRecycler.adapter = WorkoutAdapter()

        viewModel.updateExercisesById(args.position)

        viewModel.exercises.observe((activity as MainActivity)) {
            it.let {(binding.historyRecycler.adapter as WorkoutAdapter).submitList(it)}
        }

        binding.historyToolbar.setupWithNavController(findNavController())
//        binding.historyToolbar.setNavigationOnClickListener {
//            view?.findNavController()?.navigateUp()
//        }

        return binding.root
    }
}