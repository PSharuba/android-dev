package mmf.sharubapv.styledapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView answerField;
    EditText questionField;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        answerField = (TextView) findViewById(R.id.answer_field);
        questionField = (EditText) findViewById(R.id.question_field);
        submitButton = (Button) findViewById(R.id.submit_button);
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("question", String.valueOf(questionField.getText()));
        outState.putString("answer", String.valueOf(answerField.getText()));
    }
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        questionField.setText(savedInstanceState.getString("question"));
        answerField.setText(savedInstanceState.getString("answer"));
    }

    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
        intent.putExtra("question", String.valueOf(questionField.getText()).trim());
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1)
            if (resultCode == RESULT_OK){
                String answer = data.getStringExtra("answer");
                String question = data.getStringExtra("question");
                answerField.setText("Your question:\n"+question+"\nAnswer:\n"+answer);
            } else {
                answerField.setText("Your question was ignored!");
            }
    }

}
