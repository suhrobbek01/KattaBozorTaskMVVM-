package com.example.kattabozortaskwithmvvm.utils

import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

fun TextView.setText(text: String) {
    this.setText(text)
}

fun TextView.setSelected() {
    this.isSelected = true
}
fun ImageView.setImage(url:String){
    Picasso.get().load(url).into(this)
}