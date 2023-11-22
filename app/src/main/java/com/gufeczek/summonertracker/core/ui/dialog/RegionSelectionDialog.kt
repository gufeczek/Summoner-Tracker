package com.gufeczek.summonertracker.core.ui.dialog

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.gufeczek.summonertracker.databinding.DialogRegionSelectionBinding

class RegionSelectionDialog : DialogFragment() {
    private lateinit var binding: DialogRegionSelectionBinding
    private var onDialogDismissListener: OnDialogDismissListener? = null
    private var onRegionSelectedListener: OnRegionSelectedListener? = null

    interface OnDialogDismissListener {
        fun onDialogDismissed()
    }

    interface OnRegionSelectedListener {
        fun onRegionSelected(region: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogRegionSelectionBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onStart() {
        super.onStart()

        setupDialogSize()
        setupOnClickBehaviour()

        binding.layout.setOnClickListener {
            dialog?.dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDialogDismissListener?.onDialogDismissed()
    }

    fun setOnRegionSelectedListener(listener: OnRegionSelectedListener) {
        onRegionSelectedListener = listener
    }

    fun setOnDialogDismissListener(listener: OnDialogDismissListener) {
        onDialogDismissListener = listener
    }

    private fun setupDialogSize() {
        val width = (resources.displayMetrics.widthPixels)
        val height = (resources.displayMetrics.heightPixels * 0.8).toInt()
        dialog?.window?.setLayout(width, height)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun setupOnClickBehaviour() {
        val rows = arrayOf(
            binding.rowRegionSelectionFirst,
            binding.rowRegionSelectionSecond,
            binding.rowRegionSelectionThird,
            binding.rowRegionSelectionFourth,
            binding.rowRegionSelectionFifth
        )
        for (row in rows) {
            val regions = arrayOf(
                row.tvStart,
                row.tvMiddle,
                row.tvEnd
            )
            for (region in regions) {
                region.setOnClickListener {
                    onRegionSelectedListener?.onRegionSelected(region.text.toString())
                    dismiss()
                }
            }
        }
    }

    companion object {
        const val TAG = "RegionSelectionDialog"
    }
}
