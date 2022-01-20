package com.harun.testtechnopartner.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.harun.testtechnopartner.R
import com.harun.testtechnopartner.databinding.FragmentHomeBinding
import com.harun.testtechnopartner.network.response.ResponseHome
import com.harun.testtechnopartner.utils.SharedPref
import com.harun.testtechnopartner.viewModel.CaffeViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: CaffeViewModel
    private lateinit var sharedPref: SharedPref

    private val imageList = ArrayList<SlideModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = SharedPref(requireContext())
        viewModel  = ViewModelProvider(this)[CaffeViewModel::class.java]

        viewModel.home("${sharedPref.token_type} ${sharedPref.access_token}")
        viewModel.responseHome.observe(viewLifecycleOwner, { responseHome(it) })
    }

    private fun responseHome(it: ResponseHome) {
        binding.labelWelcome.text = it.result?.greeting
        binding.tvName.text = it.result?.name
        binding.tvSaldo.text = it.result?.saldo.toString()
        binding.tvPoint.text = it.result?.point.toString()
        Glide.with(requireContext())
            .load(it.result?.qrcode)
            .into(binding.imgQr)

        val img = binding.imgSlider

        imageList.add(SlideModel(it.result?.banner?.get(0)))
        imageList.add(SlideModel(it.result?.banner?.get(1)))
        imageList.add(SlideModel(it.result?.banner?.get(2)))

        img.setImageList(imageList, ScaleTypes.CENTER_CROP)
    }

}