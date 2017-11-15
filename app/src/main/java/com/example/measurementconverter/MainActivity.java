package com.example.measurementconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner1, spinner2;
    private EditText editText;
    private Button button;
    private TextView textView;
    private TextView warningText;
    private String[] measurements_array;

    private int spinner1_position, spinner2_position;
    private double quantity_in = 0, quantity_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        measurements_array = new String[]{"gram", "pound", "ounce", "stone", "kilogram"};

        spinner1 = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        textView = (TextView) findViewById(R.id.textView);
        warningText = (TextView) findViewById(R.id.warningText);
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, measurements_array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity_out = convert();
                textView.setText("" + quantity_out);
            }
        });
    }

    private double convert() {
        String input = editText.getText().toString();
        if(input != null || !input.isEmpty()) {
            if(input.matches("[0-9.]+")) {
                try {
                    quantity_in = Double.parseDouble(input);
                    spinner1_position = spinner1.getSelectedItemPosition();
                    spinner2_position = spinner2.getSelectedItemPosition();

                    /*
                    case 0 - gram
                    case 1 - pound
                    case 2 - ounce
                    case 3 - stone
                    case 4 - kg
                     */

                    switch (spinner1_position) {
                        case 0:
                            switch(spinner2_position) {
                                case 0:
                                    return quantity_in;
                                case 1:
                                    return (quantity_in / 453.592);
                                case 2:
                                    return (quantity_in / 28.3495);
                                case 3:
                                    return (quantity_in / 6350.29);
                                case 4:
                                    return (quantity_in / 1000);
                            }
                            break;
                        case 1:
                            switch(spinner2_position) {
                                case 0:
                                    return quantity_in * 453.592;
                                case 1:
                                    return (quantity_in);
                                case 2:
                                    return (quantity_in * 16);
                                case 3:
                                    return (quantity_in / 14);
                                case 4:
                                    return (quantity_in / 2.20462);
                            }
                            break;
                        case 2 :
                            switch(spinner2_position) {
                                case 0:
                                    return quantity_in * 28.3495;
                                case 1:
                                    return (quantity_in / 16);
                                case 2:
                                    return (quantity_in);
                                case 3:
                                    return (quantity_in / 224);
                                case 4:
                                    return (quantity_in / 35.274);
                            }
                            break;
                        case 3:
                            switch(spinner2_position) {
                                case 0:
                                    return quantity_in * 6350.29;
                                case 1:
                                    return (quantity_in / 14);
                                case 2:
                                    return (quantity_in * 224);
                                case 3:
                                    return (quantity_in);
                                case 4:
                                    return (quantity_in * 6.35029);
                            }
                            break;
                        case 4:
                            switch(spinner2_position) {
                                case 0:
                                    return quantity_in * 1000;
                                case 1:
                                    return (quantity_in * 2.20462);
                                case 2:
                                    return (quantity_in * 35.274);
                                case 3:
                                    return (quantity_in / 6.35029);
                                case 4:
                                    return (quantity_in);
                            }
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    warningText.setText("Something is wrong with your input");
                }
            }
        }
        return 0;
    }
}
