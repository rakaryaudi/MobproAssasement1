package org.d3if0104.assasement1raka

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import org.d3if0104.assasement1raka.databinding.TambahKendaraanBinding


class TambahKendaraan : AppCompatActivity() {
    lateinit var binding: TambahKendaraanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = TambahKendaraanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tbhKendaraanBtn.setOnClickListener { tambah() }

    }

    fun tambah(){
        val noKendaraan = binding.inpNoKendaraan.text.toString()
        val namaKendaraan = binding.inpNamaKendaraan.text.toString()
        val pemilik = binding.inpNamaPemilik.text.toString()
        val jenis = binding.spinner.selectedItem.toString()

        if(TextUtils.isEmpty(noKendaraan) || TextUtils.isEmpty(namaKendaraan) || TextUtils.isEmpty(pemilik)){
            Toast.makeText(this, "Data Tidak Boleh Kosong!", Toast.LENGTH_LONG).show()
        } else {
            val builder = AlertDialog.Builder(this)
            //set title for alert dialog
            builder.setTitle("Tambah Kendaraan")
            //set message for alert dialog
            builder.setMessage(
                "Data Kendaraan\n\n" +
                        "No Polisi :$noKendaraan\n" +
                        "Nama Kendaraan : $namaKendaraan\n" +
                        "Nama Pemilik : $pemilik\n" +
                        "Jenis Kendaraan : $jenis\n" +
                        "\nBerhasil ditambahkan!"
            )

            // Data Kendaraan
            // NO POL
            // Nama Pemilik
            // Berhasil Ditambahkan

            //performing positive action
            builder.setPositiveButton("Ok") { dialogInterface, which ->
            }
            // Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }
}