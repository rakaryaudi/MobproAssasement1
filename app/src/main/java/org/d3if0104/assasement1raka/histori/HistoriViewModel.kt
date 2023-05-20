package org.d3if0104.assasement1raka.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0104.assasement1raka.db.KendaraanDao

class HistoriViewModel(private val db: KendaraanDao) : ViewModel() {
    val data = db.getLastKendaraan()

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }
}