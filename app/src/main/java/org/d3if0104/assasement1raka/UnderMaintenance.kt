package org.d3if0104.assasement1raka

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import org.d3if0104.assasement1raka.databinding.UnderMaintenanceBinding

class UnderMaintenance : AppCompatActivity() {
    lateinit var binding: UnderMaintenanceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = UnderMaintenanceBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}