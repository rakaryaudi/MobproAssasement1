package org.d3if0104.assasement1raka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.d3if0104.assasement1raka.databinding.HomeBinding
import org.d3if0104.assasement1raka.databinding.UnderMaintenanceBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: HomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = HomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tambahIcon.setOnClickListener { tambah() }
        binding.pemasukanIcon.setOnClickListener { keluar() }
        binding.keluarIcon.setOnClickListener { keluar() }
        binding.dataIcon.setOnClickListener { keluar() }
    }

    fun tambah(){
        val intent = Intent(this, TambahKendaraan::class.java)
        startActivity(intent)
    }

    fun keluar(){
        val intent = Intent(this, UnderMaintenance::class.java)
        startActivity(intent)
    }
}