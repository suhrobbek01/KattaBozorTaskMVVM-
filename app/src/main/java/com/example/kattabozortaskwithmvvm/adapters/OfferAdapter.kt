package com.example.kattabozortaskwithmvvm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kattabozortaskwithmvvm.databinding.ItemOfferBinding
import com.example.kattabozortaskwithmvvm.models.Offer
import com.example.kattabozortaskwithmvvm.utils.setImage
import com.example.kattabozortaskwithmvvm.utils.setSelected
import com.example.kattabozortaskwithmvvm.utils.setText
import com.squareup.picasso.Picasso

class OfferAdapter(var list: List<Offer>, var listener: OfferAdapter.OnItemClickListener) :
    RecyclerView.Adapter<OfferAdapter.Vh>() {

    inner class Vh(var itemOfferBinding: ItemOfferBinding) :
        RecyclerView.ViewHolder(itemOfferBinding.root) {

        fun onBind(offer: Offer) {
            itemOfferBinding.apply {
                name.setText(offer.name)
                name.setSelected()
                brand.setText(offer.brand)
                brand.setSelected()
                category.setText(offer.category)
                category.setSelected()
                image.setImage(offer.image.url)
            }
            itemView.setOnClickListener {
                listener.onOfferClick(offer)
            }
        }
    }

    interface OnItemClickListener {
        fun onOfferClick(offer: Offer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemOfferBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

}