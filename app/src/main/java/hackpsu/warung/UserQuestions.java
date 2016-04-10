package hackpsu.warung;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserQuestions extends AppCompatActivity {

    private static Button nextBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_questions);
        onClickButtonListener();
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
