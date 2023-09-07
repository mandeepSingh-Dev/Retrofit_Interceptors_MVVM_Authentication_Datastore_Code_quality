package com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.ui.fragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.work.CoroutineWorker
import com.google.android.material.snackbar.Snackbar
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.Base.BaseFragment
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.databinding.FragmentDashboardBinding
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.ui.adapters.WallpapersAdapter
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.ui.viewmodels.DashboardViewmodel
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.utils.NetworkResult
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.utils.SpaceItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import okhttp3.internal.wait
import javax.inject.Inject
import kotlin.properties.Delegates

@AndroidEntryPoint
class Dashboard_Fragment : BaseFragment() {

    var _binding: FragmentDashboardBinding? = null
    val binding : FragmentDashboardBinding get() = _binding!!

    val dashboardViewmodel : DashboardViewmodel by viewModels()

    @Inject
    lateinit var spaceItemDecoration: SpaceItemDecoration

    val wallpaperAdapter : WallpapersAdapter by lazy {
        WallpapersAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDashboardBinding.inflate(layoutInflater)
        Log.d("dlkmdkmd","onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("dlkmdkmd","onViewCreate")

        Log.d("dkckdcmd",dashboardViewmodel.toString())





        lifecycleScope.launch {
            dashboardViewmodel.hits_list.collect{

                Log.d("flvmfkvmf","stateflow collect \n")
                wallpaperAdapter.submitList(it)
            }
        }

        setUpRecyclerView()

        lifecycleScope.launch {
            observePixabayApiResponse()
        }

    }

    fun setUpRecyclerView(){
      //  binding.wallpapersRV.layoutManager = GridLayoutManager(requireContext(),2)
        binding.wallpapersRV.adapter = wallpaperAdapter

        binding.wallpapersRV.addItemDecoration(spaceItemDecoration)
    }

    suspend fun observePixabayApiResponse(){

        dashboardViewmodel.pixabayResponse?.collect{

            when(it){
                is NetworkResult.Loading -> {
                    showLoading()
                }
                is NetworkResult.Success -> {
                    hideLoading()
                    Log.d("flvmfkvmf"," collect")
                    //here we are set data to stateflow list bcoz it is a dataholder observer and
                    // Flow is not a dataholder observer Means when we rotate the screen then Flow observer will not run
                    // and data will not set to recyclerView but Stateflow observer will observe and set data to Recyclerview/
                    dashboardViewmodel.hits_list.value = it.data?.hits

                }
                is NetworkResult.Error -> {
                    hideLoading()
                    val snackbar = Snackbar.make(binding.root,it.error.toString(),Snackbar.LENGTH_SHORT)
                    snackbar.setAction("Retry") {
                        dashboardViewmodel.getWallpapers()
                    }
                    snackbar.setTextMaxLines(5)
                    snackbar.show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.wallpapersRV.removeItemDecoration(spaceItemDecoration)
    }

}


