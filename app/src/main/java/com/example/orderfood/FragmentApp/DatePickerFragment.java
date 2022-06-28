package com.example.orderfood.FragmentApp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.orderfood.R;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Calendar calendar = Calendar.getInstance();
        int iNam = calendar.get(Calendar.YEAR);
        int iThang = calendar.get(Calendar.MONTH);
        int iNgay = calendar.get(Calendar.DAY_OF_MONTH);



        return new DatePickerDialog(getActivity(), this, iNgay, iThang,iNam);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        // lấy thông tin từ view thay vì phải dung biến static
        EditText edNgaySinh = getActivity().findViewById(R.id.edNgaySinhDK);
        String sNgaySinh = dayOfMonth+"/"+ (month+1) +"/"+ year;
        edNgaySinh.setText(sNgaySinh);
    }


}
