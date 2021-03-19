package sebgg.dev.workoutdiary.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import sebgg.dev.workoutdiary.MainActivity
import sebgg.dev.workoutdiary.R
import sebgg.dev.workoutdiary.databinding.MainFragmentBinding
import sebgg.dev.workoutdiary.viewmodels.MainViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = DataBindingUtil.inflate<MainFragmentBinding>(
            inflater,
            R.layout.main_fragment,
            container,
            false
        )

        binding.buttonNewWorkout.setOnClickListener {
            (activity as MainActivity).createNewWorkout()
        }

        binding.buttonViewOld.setOnClickListener {
            (activity as MainActivity).showHistory()
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }
}