package org.d3if0104.assasement1raka.kendaraan

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0104.assasement1raka.db.KendaraanDao
import org.d3if0104.assasement1raka.db.KendaraanEntity
import org.d3if0104.assasement1raka.model.DumKendaraan
import org.d3if0104.assasement1raka.model.Kendaraan
import org.d3if0104.assasement1raka.model.hasilKendaraan
import org.d3if0104.assasement1raka.network.KendaraanApi
import org.d3if0104.assasement1raka.network.UpdateWorker
import java.util.concurrent.TimeUnit

class KendaraanViewModel(private val db: KendaraanDao) : ViewModel() {
    private val hasilKend = MutableLiveData<Kendaraan>()
    private val dataKend = MutableLiveData<List<DumKendaraan>>()


    init{
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            try {
                dataKend.postValue(KendaraanApi.service.getKendaraan())
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
            }
        }
    }

    fun hasilKendaraan(noKendaraan: String, namaKendaraan: String, pemilik: String, jenis: String) {
        val dataKendaraan = Kendaraan(
            noKendaraan = noKendaraan,
            namaKendaraan = namaKendaraan,
            pemilik = pemilik,
            jenis = jenis
        )
        hasilKend.value = dataKendaraan.hasilKendaraan()

        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val dataKendaraan = KendaraanEntity(
                    noKendaraan = noKendaraan,
                    namaKendaraan = namaKendaraan,
                    pemilik = pemilik,
                    jenis = jenis
                )
                db.insert(dataKendaraan)
            }
        }
    }
    fun getHasilKendaraan(): LiveData<Kendaraan?> = hasilKend
    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            UpdateWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}
