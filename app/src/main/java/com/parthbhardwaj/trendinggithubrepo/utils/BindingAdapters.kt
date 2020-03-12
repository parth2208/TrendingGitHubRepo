package com.github.trandrepo.utils

import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.parthbhardwaj.trendinggithubrepo.utils.getParentActivity

/**
 * Setting up the visibility of parentActivity
 */
@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?:View.VISIBLE})
    }
}

@BindingAdapter("mutableError")
fun setMutableError(view: View, error: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && error != null) {
        error.observe(parentActivity, Observer { value -> view.visibility = value?:View.VISIBLE})
    }
}

/**
 * Setting the profile avatar into the circular view
 */
@BindingAdapter("src_profile")
fun setMainImage(view: ImageView, text: String?) {
    Glide.with(view.context).load(text).apply(RequestOptions.circleCropTransform()).into(view);
}

/**
 * binding the adaptar to the view
 */
@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}
