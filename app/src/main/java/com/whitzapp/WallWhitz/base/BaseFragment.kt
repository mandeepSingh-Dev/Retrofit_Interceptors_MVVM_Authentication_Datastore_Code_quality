package com.whitzapp.WallWhitz.base

import androidx.fragment.app.Fragment
import com.whitzapp.WallWhitz.utils.views.LoadingBarDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class BaseFragment : Fragment() {

    @Inject
    lateinit var loadingDialog : LoadingBarDialog



    fun showLoading() {
        loadingDialog.show(parentFragmentManager, "")
    }

    fun hideLoading(){
        loadingDialog.dismiss()
    }
}