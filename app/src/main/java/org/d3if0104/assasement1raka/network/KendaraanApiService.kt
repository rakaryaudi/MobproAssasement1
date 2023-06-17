package org.d3if0104.assasement1raka.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if0104.assasement1raka.model.DumKendaraan
import org.d3if0104.assasement1raka.model.Kendaraan
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/rakaryaudi/MobproAssasement1/jsonassasement3/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface KendaraanApiService {
    @GET("apikendaraan.json")
    suspend fun getKendaraan(): List<DumKendaraan>
}
object KendaraanApi {

    enum class ApiStatus { LOADING, SUCCESS, FAILED }

    val service: KendaraanApiService by lazy {
        retrofit.create(KendaraanApiService::class.java)
    }

    fun getKendaraanUrl(imageId: String): String {
        return "$BASE_URL$imageId.png"
    }
}
