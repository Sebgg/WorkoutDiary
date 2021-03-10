package sebgg.dev.workoutdiary.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import sebgg.dev.workoutdiary.MainActivity
import sebgg.dev.workoutdiary.R
import sebgg.dev.workoutdiary.databinding.HistoryFragmentBinding

class HistoryFragment: Fragment() {

    companion object {
        fun newInstance() = HistoryFragment()
    }

    private val viewModel: HistoryViewModel by activityViewModels()
    private lateinit var adapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<HistoryFragmentBinding>(
                inflater,
                R.layout.history_fragment,
                container,
                false
        )

        binding.historyRecycler.adapter = HistoryAdapter()
        viewModel.workouts.observe((activity as MainActivity)) {
            it.let {(binding.historyRecycler.adapter as HistoryAdapter).submitList(it)}
        }
        return binding.root
    }
}