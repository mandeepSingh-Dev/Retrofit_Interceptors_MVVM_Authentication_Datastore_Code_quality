package com.whitzapp.WallWhitz.utils.interceptor

import android.content.Context
import com.whitzapp.WallWhitz.utils.CheckInternetConnectivity
import okhttp3.Interceptor
import okhttp3.Response

class OnlineInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val response = chain.proceed(chain.request())

        val maxAge = 60

        return response.newBuilder()
            .header("Cache-Control", "public, max-age=$maxAge")
            .removeHeader("pragma")
            .build()

    }
}
class OfflineInterceptor(val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()

        val isInternet = CheckInternetConnectivity.isInternet(context)
        if( !isInternet ){
            val maxstale = 60 * 60 *24 * 30

            request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxstale")
                .removeHeader("pragma")
                .build()
        }

        return chain.proceed(request)

    }
}
