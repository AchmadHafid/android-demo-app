package com.example.demoapp.productsearch

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.example.demoapp.databinding.DialogProductSearchBinding
import com.example.demoapp.util.showKeyboardOn
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ProductSearchDialog : BottomSheetDialogFragment() {

    //region View Binding

    private var _viewBinding: DialogProductSearchBinding? = null
    private val viewBinding get() = _viewBinding!!

    //endregion
    //region Lifecycle Callback

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = DialogProductSearchBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSaveButton()
    }

    override fun onResume() {
        super.onResume()
        Handler(Looper.getMainLooper()).post {
            showKeyboardOn(viewBinding.input)
        }
    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }

    //endregion
    //region Ui Setup

    private fun setupSaveButton() {
        viewBinding.btnSave.setOnClickListener {
            setResult(viewBinding.input.text.toString())
            dismiss()
        }
    }

    //endregion

}

//region Fragment Result Helper

internal fun Fragment.onSearchDialogResult(block: (keyword: String) -> Unit) {
    setFragmentResultListener(REQUEST_KEY) { _, bundle ->
        bundle.getString(BUNDLE_KEY)?.let(block)
    }
}

private fun ProductSearchDialog.setResult(keyword: String) {
    setFragmentResult(REQUEST_KEY, bundleOf(BUNDLE_KEY to keyword))
}

private const val REQUEST_KEY = "search_dialog"
private const val BUNDLE_KEY = "keyword"

//endregion