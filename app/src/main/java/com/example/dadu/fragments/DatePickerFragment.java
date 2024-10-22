package com.example.dadu.fragments;

import androidx.fragment.app.DialogFragment;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.app.DatePickerDialog;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener
{
        private EditText editTextDate;

        public DatePickerFragment(EditText editText)
        {
            this.editTextDate = editText;
        }

        @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
        {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(requireContext(), this, year, month , day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day)
        {
            editTextDate.setText(day+"-"+(month+1)+"-"+year);
        }
}
