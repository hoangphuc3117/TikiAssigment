package ppapps.tikiexercise.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

interface NetworkService {
    @GET("keywords.json")
    @Headers("Content-Type: application/json")
    fun getKeywords(): Observable<Response<ResponseBody>>

    @GET("users/sr/repos")
    @Headers(
        "Accept: application/json; charset=utf-8",
        "Accept-Language: en"
    )
    fun getSrUsers(@Query("per_page") pageSize: Int, @Query("page") pageIndex:Int): Observable<Response<ResponseBody>>
}