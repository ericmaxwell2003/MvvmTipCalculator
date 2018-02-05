package com.acme.tipcalculator.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.EditText
import com.acme.tipcalculator.R


class SaveDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val saveDialog = context?.let { ctx ->

            AlertDialog.Builder(ctx)
                    .setView(buildDialogView(ctx))
                    .setNegativeButton(R.string.action_cancel, null)
                    .setPositiveButton(R.string.action_save, null)
                    .create()

        }

        return saveDialog!!
    }

    private fun buildDialogView(ctx: Context) : View {
        val location = EditText(ctx)
        location.hint = "Enter Location"
        return location
    }

}
