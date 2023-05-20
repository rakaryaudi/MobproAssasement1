package org.d3if0104.assasement1raka.histori

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if0104.assasement1raka.R
import org.d3if0104.assasement1raka.databinding.ItemHistoriBinding
import org.d3if0104.assasement1raka.db.KendaraanEntity
import org.d3if0104.assasement1raka.model.Kendaraan

class HistoriAdapter :
    ListAdapter<KendaraanEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<KendaraanEntity>() {
                override fun areItemsTheSame(
                    oldData: KendaraanEntity, newData: KendaraanEntity
                ): Boolean {
                    return oldData.id == newData.id
                }
                override fun areContentsTheSame(
                    oldData: KendaraanEntity, newData: KendaraanEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }
    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: KendaraanEntity) = with(binding){
            judulTextView.text = "DATA KENDARAAN PARKIR"
            dataNoKendTextView.text = "No Kendaraan: "+ item.noKendaraan.toString()
            dataNamaKendTextView.text = "Nama Kendaraan: "+ item.namaKendaraan.toString()
            dataPemilikKendTextView.text ="Nama Pemilik: "+ item.pemilik.toString()
            dataJenisKendTextView.text ="Jenis Kendaraan: "+ item.jenis.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
