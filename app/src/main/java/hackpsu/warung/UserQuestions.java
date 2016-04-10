package hackpsu.warung;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class UserQuestions extends AppCompatActivity {

    private static Button nextBtn2;
    private static CheckBox RUPregs;
    private static CheckBox under18;

    int caffeineThreshhold = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_questions);
        onClickButtonListener();
    }

    public void onCheckBoxClicked(View view){
        boolean checked = ((CheckBox) view).isChecked();
        RUPregs = (CheckBox) findViewById(R.id.pregBox);
        RUPregs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CheckBox) view).isChecked()){
                    caffeineThreshhold = 2;
                }
            }
        });
        under18 = (CheckBox) findViewById(R.id.ageBox);
        under18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CheckBox) view).isChecked()){
                    caffeineThreshhold = 2;
                }
            }
        });
    }

    public void onClickButtonListener(){
        nextBtn2 = (Button)findViewById(R.id.nextBtn2);
        nextBtn2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent(UserQuestions.this, NumberOfCups.class);
                        startActivity(intent);
                    }
                }
        );
    }
}
