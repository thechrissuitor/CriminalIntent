package csuitor.uvm.edu;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DatePickerFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Inflate the view. Later, .setView(v) sets the view on the dialog
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);

        // build an AlertDialog with a title and one OK button
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }

}
