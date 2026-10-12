
package com.example.week6;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class CustomDialog extends Dialog {

    private Context context;

    public CustomDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getWindow())
                .setBackgroundDrawable(
                        new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams window =
                new WindowManager.LayoutParams();

        window.flags =
                WindowManager.LayoutParams.FLAG_DIM_BEHIND;

        window.dimAmount = 0.5f;

        getWindow().setAttributes(window);

        setContentView(R.layout.custom_dialog);

        EditText editText =
                findViewById(R.id.put_text);

        Button saveButton =
                findViewById(R.id.btnSave);

        saveButton.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(
                                context,
                                editText.getText().toString(),
                                Toast.LENGTH_SHORT).show();

                        dismiss();
                    }
                });

        Button cancelButton =
                findViewById(R.id.btnCancle);

        cancelButton.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });
    }
}
