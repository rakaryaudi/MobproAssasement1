package org.d3if0104.assasement1raka

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.d3if0104.assasement1raka.databinding.FragmentTarifBinding
import org.d3if0104.assasement1raka.db.KendaraanDb
import org.d3if0104.assasement1raka.kendaraan.KendaraanViewModel
import org.d3if0104.assasement1raka.kendaraan.KendaraanViewModelFactory
import org.d3if0104.assasement1raka.model.DumKendaraan
import org.d3if0104.assasement1raka.network.KendaraanApi

class TarifFragment : Fragment() {
    private lateinit var binding: FragmentTarifBinding
    private val tarifList: MutableList<DumKendaraan> = mutableListOf()
    private lateinit var tarifAdapter: TarifAdapter

    private val viewModel: KendaraanViewModel by lazy {
        val db = KendaraanDb.getInstance(requireContext())
        val factory = KendaraanViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[KendaraanViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTarifBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tarifAdapter = TarifAdapter(tarifList)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = tarifAdapter
        fetchDataFromApi()
        viewModel.scheduleUpdater(requireActivity().application)
    }
    private fun fetchDataFromApi() {
        binding.progressBar.visibility = View.VISIBLE
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = KendaraanApi.service.getKendaraan()
                tarifList.clear()
                tarifList.addAll(response)
                tarifAdapter.notifyDataSetChanged()
            } catch (e: Exception) {
                binding.networkError.visibility = View.VISIBLE
            } finally {
                binding.progressBar.visibility = View.GONE
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    requestNotificationPermission()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestNotificationPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                MainActivity.PERMISSION_REQUEST_CODE
            )
        }
    }
}