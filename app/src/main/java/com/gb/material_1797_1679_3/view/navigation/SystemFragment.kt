package com.gb.material_1797_1679_3.view.navigation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.gb.material_1797_1679_3.R
import com.gb.material_1797_1679_3.databinding.FragmentSystemBinding
import com.gb.material_1797_1679_3.viewmodel.PictureOfTheDayState
import com.gb.material_1797_1679_3.viewmodel.PictureOfTheDayViewModel
import com.google.android.material.snackbar.Snackbar


class SystemFragment : Fragment() {


    private var _binding: FragmentSystemBinding? = null
    val binding: FragmentSystemBinding
        get() = _binding!!

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSystemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner,  { renderData(it) })
        binding.contentText.text = ""
        viewModel.getSolarFlare(TODAY)
    }


    private fun renderData(appState: PictureOfTheDayState) {
        when (appState) {
            is PictureOfTheDayState.Error ->{
                binding.loadingImageView.visibility = View.GONE
                Snackbar.make(binding.root, appState.error.toString(), Snackbar.LENGTH_SHORT).show()
            }
            is PictureOfTheDayState.SuccessWeather -> {
                binding.loadingImageView.visibility = View.GONE
                binding.contentText.visibility = View.VISIBLE
                setData(appState)
            }
        }
    }

    private fun setData(appState: PictureOfTheDayState.SuccessWeather) {
        binding.contentText.text = appState.solarFlareResponseData.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }



    companion object {
        @JvmStatic
        fun newInstance() = SystemFragment()


        private const val TODAY = 0
        private const val MONTH = 30
    }
}