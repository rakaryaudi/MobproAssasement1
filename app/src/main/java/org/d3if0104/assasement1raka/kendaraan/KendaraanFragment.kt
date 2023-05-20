package org.d3if0104.assasement1raka.kendaraan

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import org.d3if0104.assasement1raka.R
import org.d3if0104.assasement1raka.UnderMaintenance
import org.d3if0104.assasement1raka.databinding.FragmentKendaraanBinding

class KendaraanFragment : Fragment() {
    private lateinit var binding: FragmentKendaraanBinding
    private val viewModel: KendaraanViewModel by lazy {
        ViewModelProvider(requireActivity())[KendaraanViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentKendaraanBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tambahIcon.setOnClickListener { it.findNavController().navigate(
            R.id.action_kendaraanFragment_to_tambahFragment
        ) }
        binding.pemasukanIcon.setOnClickListener { keluar() }
        binding.keluarIcon.setOnClickListener { keluar() }
        binding.dataIcon.setOnClickListener { it.findNavController().navigate(
            R.id.action_kendaraanFragment_to_historiFragment
        ) }
    }


    private fun keluar() {
        val intent = Intent(requireContext(), UnderMaintenance::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_about) {
            findNavController().navigate(
                R.id.action_kendaraanFragment_to_aboutFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}