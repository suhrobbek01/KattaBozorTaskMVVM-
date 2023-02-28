package com.example.kattabozortaskwithmvvm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kattabozortaskwithmvvm.R
import com.example.kattabozortaskwithmvvm.databinding.FragmentInsideBinding
import com.example.kattabozortaskwithmvvm.models.Offer
import com.squareup.picasso.Picasso
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding

class InsideFragment : Fragment(R.layout.fragment_inside) {
    private val binding by viewBinding(FragmentInsideBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val offer = arguments?.getSerializable("offer") as Offer

        binding.apply {
            Picasso.get().load(offer.image.url).into(image)
            name.text = "Name: " + offer.name
            name.isSelected = true

            brand.text = "Brand: " + offer.brand
            brand.isSelected = true

            category.text = "Category: " + offer.category
            category.isSelected = true

            merchant.text = "Merchant: " + offer.merchant
            merchant.isSelected = true
        }
    }
}