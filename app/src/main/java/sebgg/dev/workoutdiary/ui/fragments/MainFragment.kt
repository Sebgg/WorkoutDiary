package sebgg.dev.workoutdiary.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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

        val navController = findNavController()


        binding.buttonNewWorkout.setOnClickListener {
            navController.navigate(
                    MainFragmentDirections.actionMainFragmentToWorkoutFragment()
            )
        }

        binding.buttonViewOld.setOnClickListener {
            navController.navigate(
                    MainFragmentDirections.actionMainFragmentToHistoryFragment()
            )
        }

        return binding.root
    }

    @Suppress("DEPRECATION")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }
}