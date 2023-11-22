package com.gufeczek.summonertracker.core.ui.find.compound

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import com.gufeczek.summonertracker.R
import com.gufeczek.summonertracker.core.ui.dialog.RegionSelectionDialog
import com.gufeczek.summonertracker.databinding.ViewSummonerSearchBinding

class SummonerSearchView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs), View.OnClickListener, RegionSelectionDialog.OnDialogDismissListener {
    private var binding: ViewSummonerSearchBinding
    private val regions = resources.getStringArray(R.array.regions_array)
    private var _onClickListener: OnClickListener? = null
    private var _onEditorActionListener: OnEditorActionListener? = null

    interface OnEditorActionListener {
        fun onActionDone(summonerName: String)
    }

    init {
        binding = ViewSummonerSearchBinding.inflate(LayoutInflater.from(context), this, true)
        binding.tvRegionName.text = regions[0]
        binding.btnSelectRegion.setOnClickListener(this)
        binding.etSummonerInput.setOnEditorActionListener { tvSummonerInput, actionId, _ ->
            if (tvSummonerInput.text.isNotEmpty() && actionId == EditorInfo.IME_ACTION_DONE) {
                Log.d(TAG, "setOnEditorActionListener")
                _onEditorActionListener?.onActionDone(tvSummonerInput.text.toString())
                true
            } else {
                false
            }
        }
    }

    fun setOnEditorActionListener(listener: OnEditorActionListener?) {
        _onEditorActionListener = listener
    }

    override fun onClick(view: View) {
        toggleButtonOn()
        _onClickListener?.onClick(view)
    }

    override fun setOnClickListener(listener: OnClickListener?) {
        _onClickListener = listener
    }

    override fun onDialogDismissed() = toggleButtonOff()

    fun setRegion(region: String) {
        binding.tvRegionName.text = region
    }

    private fun toggleButtonOn() = binding.btnSelectRegion.setImageResource(R.drawable.shape_region_selection_enabled)
    private fun toggleButtonOff() = binding.btnSelectRegion.setImageResource(R.drawable.shape_region_selection_disabled)

    companion object {
        const val TAG = "SummonerSearchView"
    }
}
