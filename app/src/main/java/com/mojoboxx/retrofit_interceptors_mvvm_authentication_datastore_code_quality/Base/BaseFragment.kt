package com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.Base

import android.util.Log
import androidx.fragment.app.Fragment
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.utils.views.LoadingBarDialog
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