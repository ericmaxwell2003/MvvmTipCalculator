package com.acme.tipcalculator.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.widget.EditText
import com.acme.tipcalculator.R


class SaveDialogFragment : DialogFragment() {

    interface Callback {
        fun onSaveTip(name: String)
    }

    var saveTipCallback: SaveDialogFragment.Callback? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        saveTipCallback = context as? SaveDialogFragment.Callback
    }

    override fun onDetach() {
        super.onDetach()
        saveTipCallback = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val saveDialog = context?.let { ctx ->

            val editText = buildDialogView(ctx)

            AlertDialog.Builder(ctx)
                    .setView(editText)
                    .setNegativeButton(R.string.action_cancel, null)
                    .setPositiveButton(R.string.action_save,  { _,_ -> onSave(editText) })
                    .create()
        }

        return saveDialog!!
    }

    private fun onSave(editText: EditText) {
        saveTipCallback?.onSaveTip(editText.text.toString())
    }

    private fun buildDialogView(ctx: Context) : EditText {
        val location = EditText(ctx)
        location.hint = "Enter Location"
        return location
    }

}
