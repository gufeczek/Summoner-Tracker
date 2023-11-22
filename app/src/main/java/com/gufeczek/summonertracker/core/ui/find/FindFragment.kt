package com.gufeczek.summonertracker.core.ui.find

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gufeczek.summonertracker.core.ui.dialog.RegionSelectionDialog
import com.gufeczek.summonertracker.core.ui.find.compound.SummonerSearchView
import com.gufeczek.summonertracker.databinding.FragmentFindBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FindFragment : Fragment() {
    private lateinit var binding: FragmentFindBinding
    private val viewmodel: FindViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFindBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.summonerSearchView.setOnClickListener { setupDialog() }
        binding.summonerSearchView.setRegion(viewmodel.getSelectedRegion())
        binding.summonerSearchView.setOnEditorActionListener(object : SummonerSearchView.OnEditorActionListener {
            override fun onActionDone(summonerName: String) {
                viewmodel.onSearch(summonerName)
            }
        })
    }

    private fun setupDialog() {
        val dialog = RegionSelectionDialog()
        dialog.show(childFragmentManager, RegionSelectionDialog.TAG)
        dialog.setOnDialogDismissListener(binding.summonerSearchView)
        dialog.setOnRegionSelectedListener(object : RegionSelectionDialog.OnRegionSelectedListener {
            override fun onRegionSelected(region: String) {
                binding.summonerSearchView.setRegion(region)
                viewmodel.putSelectedRegion(region)
            }
        })
    }
}
