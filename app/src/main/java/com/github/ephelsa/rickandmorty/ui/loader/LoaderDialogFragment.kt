package com.github.ephelsa.rickandmorty.ui.loader

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.github.ephelsa.rickandmorty.R
import com.github.ephelsa.rickandmorty.databinding.LayoutLoaderBinding

class LoaderDialogFragment : DialogFragment() {

    private lateinit var binding: LayoutLoaderBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_loader, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init(view)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun init(view: View) {
        requireDialog().window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        bindView(view)
    }

    private fun bindView(view: View) {
        binding = LayoutLoaderBinding.bind(view)
    }
}