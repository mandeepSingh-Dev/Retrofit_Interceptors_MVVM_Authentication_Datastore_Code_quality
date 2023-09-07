package com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.utils.views

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.R
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.databinding.LoadingDialogBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject
import javax.inject.Scope
import javax.inject.Singleton

@AndroidEntryPoint
@ActivityScoped
class LoadingBarDialog @Inject constructor(): DialogFragment() {

    var _binding : LoadingDialogBinding? = null
    val binding : LoadingDialogBinding  get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoadingDialogBinding.inflate(layoutInflater)

        return binding.root

    }

    @SuppressLint("ResourceAsColor")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialog = Dialog(requireContext())

        /// Fullscreen and dialog_window_color to transparent
        dialog.setOnShowListener {
            dialog.window?.decorView?.setBackgroundColor(android.R.color.transparent)
            dialog.window?.setLayout(resources.displayMetrics.widthPixels,resources.displayMetrics.heightPixels)
        }
        dialog.setCancelable(false)

        return dialog
    }

    override fun show(manager: FragmentManager, tag: String?) {
        super.show(manager, tag)
    }

    override fun dismiss() {
        super.dismiss()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
    }

}