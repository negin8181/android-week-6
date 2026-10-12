package com.example.week6;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progress, progress1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDialog1(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                this,
                android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);

        builder.setMessage("앱을 종료하시겠습니까 ?");
        builder.setIcon(R.drawable.star1).setTitle("일반 Dialog");

        builder.setPositiveButton("취소",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(
                                getBaseContext(),
                                "취소를 눌렀습니다",
                                Toast.LENGTH_LONG).show();
                    }
                }).setNeutralButton("확인",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(
                                getBaseContext(),
                                "확인하였습니다",
                                Toast.LENGTH_SHORT).show();
                    }
                }).setCancelable(false).show();
    }

    public void showDialog2(View view) {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener dateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(
                            DatePicker view,
                            int year,
                            int monthOfYear,
                            int dayOfMonth) {

                        String temp = year + "년 ";
                        temp += (monthOfYear + 1) + "월 ";
                        temp += dayOfMonth + "일";

                        Toast.makeText(
                                getBaseContext(),
                                temp,
                                Toast.LENGTH_SHORT).show();
                    }
                };

        DatePickerDialog dialog = new DatePickerDialog(
                this,
                android.R.style.Theme_DeviceDefault_Light_Dialog,
                dateSetListener,
                year,
                month,
                day);

        dialog.show();
    }

    public void showDialog3(View view) {
        TimePickerDialog.OnTimeSetListener timeSetListener =
                new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(
                            TimePicker view,
                            int hourOfDay,
                            int minute) {

                        Toast.makeText(
                                getBaseContext(),
                                hourOfDay + ":" + minute,
                                Toast.LENGTH_SHORT).show();
                    }
                };

        TimePickerDialog dialog = new TimePickerDialog(
                this,
                android.R.style.Theme_DeviceDefault_Light_Dialog,
                timeSetListener,
                0,
                0,
                false);

        dialog.show();
    }

    public void showDialog4(View view) {
        Calendar calender = Calendar.getInstance();
        int year = calender.get(Calendar.YEAR);

        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_birthday);

        Button ok = dialog.findViewById(R.id.ok);
        Button cancel = dialog.findViewById(R.id.cancel);
        NumberPicker picker =
                dialog.findViewById(R.id.birthdayPicker);

        picker.setMinValue(year - 80);
        picker.setMaxValue(year - 15);

        picker.setDescendantFocusability(
                NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        picker.setWrapSelectorWheel(false);
        picker.setValue(year - 20);

        picker.setOnValueChangedListener(
                new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(
                            NumberPicker picker,
                            int oldVal,
                            int newVal) {
                    }
                });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        MainActivity.this,
                        String.valueOf(picker.getValue()),
                        Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void showDialog5(View view) {
        String options = "0321458769ACBFED";

        CharacterPickerDialog dialog =
                new CharacterPickerDialog(
                        this,
                        new View(this),
                        null,
                        options,
                        false) {
                    public void onClick(View v) {
                        Toast.makeText(
                                getBaseContext(),
                                "selected character :" +
                                        ((Button) v).getText().toString(),
                                Toast.LENGTH_SHORT).show();
                        dismiss();
                    }
                };

        dialog.show();
    }

    public void showDialog6(View view) {
        progress = new ProgressDialog(
                this,
                AlertDialog.THEME_HOLO_DARK);

        progress.setTitle("공지");

        progress.setMessage(
                "잠시만 기다려 주세요!!\n 프로그램 로딩중입니다.\n중지하시겠습니까 ? ");

        progress.setButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(
                            DialogInterface dialogInterface,
                            int i) {
                        progress.dismiss();

                        Toast.makeText(
                                getBaseContext(),
                                "중지하였습니다",
                                Toast.LENGTH_SHORT).show();
                    }
                });

        progress.getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        progress.show();
    }

    public void showDialog7(View view) {
        progress1 = new ProgressDialog(
                this,
                android.R.style.Theme_DeviceDefault_Light_Dialog);

        progress1.setProgressStyle(
                ProgressDialog.STYLE_HORIZONTAL);

        progress1.setMessage("로딩중..");
        progress1.setMax(200);
        progress1.getWindow().setDimAmount(0);
        progress1.show();

        for (int i = 1; i <= 10; i++) {
            progress1.setProgress(i * 10);
        }
    }

    public void showDialog8(View view) {
        CustomDialog dialog = new CustomDialog(this);
        dialog.setCancelable(false);
        dialog.show();
    }

    public void dismissDialog1(View view) {
        if (progress != null) progress.dismiss();
    }

    public void dismissDialog2(View view) {
        if (progress1 != null) progress1.dismiss();
    }
}
