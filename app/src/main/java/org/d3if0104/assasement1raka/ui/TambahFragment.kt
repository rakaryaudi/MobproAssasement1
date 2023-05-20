package org.d3if0104.assasement1raka.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.d3if0104.assasement1raka.R
import org.d3if0104.assasement1raka.data.SettingDataStore
import org.d3if0104.assasement1raka.data.dataStore
import org.d3if0104.assasement1raka.databinding.FragmentTambahBinding
import org.d3if0104.assasement1raka.db.KendaraanDb
import org.d3if0104.assasement1raka.kendaraan.KendaraanViewModel
import org.d3if0104.assasement1raka.kendaraan.KendaraanViewModelFactory
import org.d3if0104.assasement1raka.model.Kendaraan

class TambahFragment : Fragment() {
    private lateinit var binding: FragmentTambahBinding

    private val viewModel: KendaraanViewModel by lazy {
        val db = KendaraanDb.getInstance(requireContext())
        val factory = KendaraanViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[KendaraanViewModel::class.java]
    }

    private val layoutDataStore: SettingDataStore by lazy {
        SettingDataStore(requireContext().dataStore)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTambahBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tbhKendaraanBtn.setOnClickListener { tambah() }
        binding.shareButton.setOnClickListener { shareData() }
        viewModel.getHasilKendaraan().observe(viewLifecycleOwner, { showResult(it) })
    }

    private fun tambah() {
        val noKendaraan = binding.inpNoKendaraan.text.toString()
        val namaKendaraan = binding.inpNamaKendaraan.text.toString()
        val pemilik = binding.inpNamaPemilik.text.toString()
        val jenis = binding.spinner.selectedItem.toString()

        if (TextUtils.isEmpty(noKendaraan) || TextUtils.isEmpty(namaKendaraan) || TextUtils.isEmpty(pemilik)) {
            Toast.makeText(requireContext(), "Data Tidak Boleh Kosong!", Toast.LENGTH_LONG).show()
        } else {
            viewModel.hasilKendaraan(noKendaraan, namaKendaraan, pemilik, jenis)
        }
    }

    private fun showResult(result: Kendaraan?) {
        if (result == null) return
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Tambah Kendaraan")
        builder.setMessage(
            "Data Kendaraan\n\n" +
                    "No Polisi: ${result.noKendaraan}\n" +
                    "Nama Kendaraan: ${result.namaKendaraan}\n" +
                    "Nama Pemilik: ${result.pemilik}\n" +
                    "Jenis Kendaraan: ${result.jenis}\n" +
                    "\nBerhasil ditambahkan!"
        )
        builder.setPositiveButton("Ok") { dialogInterface, which -> }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun shareData(){
        val noKendaraan = binding.inpNoKendaraan.text.toString()
        val namaKendaraan = binding.inpNamaKendaraan.text.toString()
        val pemilik = binding.inpNamaPemilik.text.toString()
        val jenis = binding.spinner.selectedItem.toString()

        val message = getString(R.string.bagikan_template,
            noKendaraan, namaKendaraan, pemilik, jenis
        )

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }
}