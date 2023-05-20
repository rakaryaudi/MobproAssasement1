package org.d3if0104.assasement1raka.kendaraan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0104.assasement1raka.db.KendaraanDao
import org.d3if0104.assasement1raka.db.KendaraanEntity
import org.d3if0104.assasement1raka.model.Kendaraan
import org.d3if0104.assasement1raka.model.hasilKendaraan

class KendaraanViewModel(private val db: KendaraanDao) : ViewModel() {
    private val hasilKend = MutableLiveData<Kendaraan?>()


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
}