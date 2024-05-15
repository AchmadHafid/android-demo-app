package com.example.demoapp.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.demoapp.R
import com.example.demoapp.databinding.FragmentProductListBinding
import com.example.demoapp.productlist.adapter.ProductListAdapter
import com.example.demoapp.productlist.model.ProductListState
import com.example.demoapp.productsearch.onSearchDialogResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductListFragment : Fragment() {

    //region View Binding

    private var _viewBinding: FragmentProductListBinding? = null
    private val viewBinding get() = _viewBinding!!

    //endregion
    //region View Model

    private val viewModel: ProductListViewModel by viewModels()

    //endregion
    //region Lifecycle Callback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onSearchDialogResult { keyword ->
            viewModel searchBy keyword
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentProductListBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupRecyclerView()
        observeState()
    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }

    //endregion
    //region Ui Setup

    private fun setupToolbar() {
        viewBinding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_search -> true.also {
                    viewModel.onSearchMenuClicked {
                        findNavController().navigate(
                            ProductListFragmentDirections.showSearchDialog()
                        )
                    }
                }

                R.id.action_favourite -> true.also {
                    viewModel.onFavouriteMenuClicked()
                }

                else -> false
            }
        }
    }

    private val productListAdapter = ProductListAdapter {
        viewModel onProductClicked it.id
    }

    private fun setupRecyclerView() {
        viewBinding.rvProduct.adapter = productListAdapter
    }

    //endregion
    //region State Handler

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collectLatest(::bind)
        }
    }

    private fun bind(state: ProductListState) {
        viewBinding.toolbar.menu.apply {
            findItem(R.id.action_search)?.setIcon(state.searchIconRes)
            findItem(R.id.action_favourite)?.setIcon(state.favouriteIconRes)
        }
        productListAdapter.submitList(state.products)
    }

    //endregion

}