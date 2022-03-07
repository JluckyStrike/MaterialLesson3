package com.gb.material_1797_1679_3.view.navigation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gb.material_1797_1679_3.R
import com.gb.material_1797_1679_3.databinding.FragmentMarsBinding
import com.gb.material_1797_1679_3.viewmodel.PictureOfTheDayViewModel
import androidx.lifecycle.Observer
import coil.load
import com.gb.material_1797_1679_3.viewmodel.PictureOfTheDayMarsState
import com.gb.material_1797_1679_3.viewmodel.PictureOfTheDayState

class MarsFragment : Fragment() {


    private var _binding: FragmentMarsBinding? = null
    val binding: FragmentMarsBinding
        get() = _binding!!

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData2().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.sendServerRequest2()
    }

    private fun renderData(pictureOfTheDayMarsState: PictureOfTheDayMarsState) {
        when (pictureOfTheDayMarsState) {
           /* is PictureOfTheDayMarsState.Error -> {
                // TODO HW
            }
            is PictureOfTheDayMarsState.Loading -> {
                // TODO HW
            }
            is PictureOfTheDayMarsState.Success -> {
                binding.imageMars.load(pictureOfTheDayMarsState.serverResponseData.latestPhotos[0].imgSrc)

            }*/

            is PictureOfTheDayMarsState.Success ->{
                binding.imageMars.load(pictureOfTheDayMarsState.serverResponseData.imgSrc)
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }




    companion object {
        @JvmStatic
        fun newInstance() = MarsFragment()
    }
}