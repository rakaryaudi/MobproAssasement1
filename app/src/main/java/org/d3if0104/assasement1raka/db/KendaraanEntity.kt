package org.d3if0104.assasement1raka.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kendaraan")
data class KendaraanEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var noKendaraan: String,
    var namaKendaraan: String,
    var pemilik: String,
    var jenis: String
)
