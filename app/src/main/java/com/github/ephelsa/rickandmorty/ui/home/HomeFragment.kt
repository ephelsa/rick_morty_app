package com.github.ephelsa.rickandmorty.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ephelsa.rickandmorty.R
import com.github.ephelsa.rickandmorty.databinding.FragmentHomeBinding
import com.github.ephelsa.rickandmorty.resourceoption.domain.ResourceOption
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class HomeFragment : Fragment(), ResourceOptionAdapter.ViewHolder.ActionCallback {

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this, HomeViewModelProvider()).get(HomeViewModel::class.java)
    }
    private val resourceOptionAdapter: ResourceOptionAdapter by lazy { ResourceOptionAdapter(actionCallback = this) }
    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init(view)
    }

    private fun init(view: View) {
        bindInit(view)
        uiChangeObserverInit()
    }

    private fun bindInit(view: View) {
        binding = FragmentHomeBinding.bind(view)

        configureResourceOptionRecycler()
    }

    private fun configureResourceOptionRecycler() {
        binding.optionsRecycler.apply {
            adapter = resourceOptionAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(false)
        }
    }

    private fun uiChangeObserverInit() {
        lifecycleScope.launchWhenCreated {
            launch {
                viewModel.uiChange.collect { uiChangeHandler(it) }
            }
        }
    }

    private fun uiChangeHandler(change: HomeViewModel.UIChange) {
        when (change) {
            is HomeViewModel.UIChange.AllResourceOptions -> resourceOptionAdapter.updateResourceOptions(
                change.resourceOptions
            )
        }
    }

    override fun onResourceOptionSelected(resourceOption: ResourceOption) {
        Toast.makeText(context, "Resource selected -> $resourceOption", Toast.LENGTH_LONG).show()
    }
}