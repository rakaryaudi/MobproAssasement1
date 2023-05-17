package org.d3if0104.assasement1raka.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if0104.assasement1raka.model.Kendaraan
import org.d3if0104.assasement1raka.model.hasilKendaraan

class KendaraanViewModel : ViewModel() {
    private val hasilBmi = MutableLiveData<Kendaraan?>()

    fun hasilKendaraan(noKendaraan: String, namaKendaraan: String, pemilik: String, jenis: String) {
        val dataKendaraan = Kendaraan(
            noKendaraan = noKendaraan,
            namaKendaraan = namaKendaraan,
            pemilik = pemilik,
            jenis = jenis
        )
        hasilBmi.value = dataKendaraan.hasilKendaraan()
    }

    fun getHasilKendaraan(): LiveData<Kendaraan?> = hasilBmi
}