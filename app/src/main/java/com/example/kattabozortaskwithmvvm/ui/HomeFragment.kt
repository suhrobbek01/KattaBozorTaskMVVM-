package com.example.kattabozortaskwithmvvm.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.kattabozortaskwithmvvm.R
import com.example.kattabozortaskwithmvvm.adapters.OfferAdapter
import com.example.kattabozortaskwithmvvm.databinding.FragmentHomeBinding
import com.example.kattabozortaskwithmvvm.models.Offer
import com.example.kattabozortaskwithmvvm.repository.OfferRepository
import com.example.kattabozortaskwithmvvm.retrofit.ApiClient
import com.example.kattabozortaskwithmvvm.retrofit.ApiService
import com.example.kattabozortaskwithmvvm.utils.NetworkHelper
import com.example.kattabozortaskwithmvvm.utils.OffersResource
import com.example.kattabozortaskwithmvvm.viewmodels.OfferViewModel
import com.example.kattabozortaskwithmvvm.viewmodels.ViewModelFactory
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val TAG = "HomeFragment"

    private lateinit var offerViewModel: OfferViewModel
    private lateinit var adapter: OfferAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        offerViewModel = ViewModelProvider(
            this,
            ViewModelFactory(OfferRepository(ApiClient.apiService), NetworkHelper(requireContext()))
        )[OfferViewModel::class.java]
        loadUI()
    }

    private fun loadUI() {
        lifecycleScope.launch {
            offerViewModel.fetchOffers().collect {
                when (it) {
                    is OffersResource.Loading -> {
                        Log.d(TAG, "loadUI: Loading........................")
                    }
                    is OffersResource.Succes -> {
                        adapter = OfferAdapter(it.offersObject!!,
                            object : OfferAdapter.OnItemClickListener {
                                override fun onOfferClick(offer: Offer) {
                                    val bundle = Bundle()
                                    bundle.putSerializable("offer", offer)
                                    findNavController().navigate(
                                        R.id.action_homeFragment_to_insideFragment,
                                        bundle
                                    )
                                }
                            })
                        binding.rv.adapter=adapter
                        Log.d(TAG, "loadUI: ${it.offersObject}")
                    }
                    is OffersResource.Error -> {
                        Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "loadUI: Error ${it.message}")
                    }
                }
            }
        }
    }

}