package org.d3if0104.assasement1raka.model

fun Kendaraan.hasilKendaraan(): Kendaraan {
    val no = noKendaraan
    val nama = namaKendaraan
    val pemilikKendaraan = pemilik
    val jenisKendaraan = jenis
    return Kendaraan(no, nama, pemilikKendaraan, jenisKendaraan)
}