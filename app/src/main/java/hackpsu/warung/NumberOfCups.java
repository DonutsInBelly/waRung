package hackpsu.warung;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;

public class NumberOfCups extends AppCompatActivity {

    private static Button doneBtn;
    NumberPicker numberpicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_of_cups);
        onClickButtonListener();
        numberpicker = (NumberPicker) findViewById(R.id.numberPicker);
        numberpicker.setMinValue(0);
        numberpicker.setMaxValue(10);
        numberpicker.setWrapSelectorWheel(false);
    }

    public void onClickButtonListener(){
        doneBtn = (Button)findViewById(R.id.doneBtn);
        doneBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent(NumberOfCups.this, TimeCount.class);
                        startActivity(intent);
                    }
                }
        );
    }
}
