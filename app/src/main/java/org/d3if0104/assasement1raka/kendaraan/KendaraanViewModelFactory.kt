package org.d3if0104.assasement1raka.kendaraan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if0104.assasement1raka.db.KendaraanDao

class KendaraanViewModelFactory(
    private val db: KendaraanDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KendaraanViewModel::class.java)) {
            return KendaraanViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
