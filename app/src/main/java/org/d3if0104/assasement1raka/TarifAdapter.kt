package org.d3if0104.assasement1raka

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if0104.assasement1raka.databinding.ItemTarifBinding
import org.d3if0104.assasement1raka.model.DumKendaraan
import org.d3if0104.assasement1raka.network.KendaraanApi

class TarifAdapter(private val tarifList: List<DumKendaraan>) :
    RecyclerView.Adapter<TarifAdapter.TarifViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarifViewHolder {
        val binding = ItemTarifBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TarifViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TarifViewHolder, position: Int) {
        val tarif = tarifList[position]
        holder.bind(tarif)
    }

    override fun getItemCount(): Int {
        return tarifList.size
    }

    inner class TarifViewHolder(private val binding: ItemTarifBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tarif: DumKendaraan) {
            binding.judulTextView.text = "TARIF PARKIR"
            binding.jenisTextView.text = "Jenis Kendaraan: ${tarif.jeniskendaraan}"
            binding.tarifTextView.text = "Tarif per Jam: ${tarif.tarif}"

            val imageUrl = KendaraanApi.getKendaraanUrl(tarif.gambar)
            Glide.with(binding.root.context)
                .load(imageUrl)
                .error(R.drawable.baseline_broken_image_24)
                .into(binding.imageView)
        }
    }
}