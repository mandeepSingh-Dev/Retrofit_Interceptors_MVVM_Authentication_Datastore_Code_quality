package com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.utils.interceptor

import android.content.Context
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.utils.CheckInternetConnectivity
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.concurrent.TimeUnit

class CacheInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        val cacheControl =  CacheControl.Builder()
            .maxAge(10,TimeUnit.DAYS)
            .build()

        return response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }
}

class ForceCacheInterceptor(val context : Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder= chain.request().newBuilder()
        val isInternet = CheckInternetConnectivity.isInternet(context)
        if (!isInternet) {
            builder.cacheControl(CacheControl.FORCE_CACHE);
        }
        return chain.proceed(builder.build());
    }
}

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
