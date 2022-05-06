package com.newcore.letstryit.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.newcore.letstryit.ui.MainActivity

open class BaseFragment<T:ViewBinding>(val inflater:(LayoutInflater)->T): Fragment() {
    val binding by lazy{
        inflater(layoutInflater)
    }

    val mainActivity by lazy{
        activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return  binding.root
    }

    fun toggleLoading(isLoading: Boolean){
        mainActivity.toggleLoading(isLoading)
    }

    fun isLoading():Boolean = mainActivity.isLoading()


}