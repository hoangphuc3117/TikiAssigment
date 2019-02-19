package ppapps.tikiexercise.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import rx.Observable

interface NetworkService {
    @GET("keywords.json")
    @Headers("Content-Type: application/json")
    fun getKeywords(): Observable<Response<ResponseBody>>
}