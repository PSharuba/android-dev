package mmf.sharubapv.styledapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AnswerActivity extends Activity {

    TextView questionText;
    EditText answerField;
    Button answerButton;
    String question;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        answerField = (EditText) findViewById(R.id.answer_field);
        questionText = (TextView) findViewById(R.id.question_text);
        answerButton = (Button) findViewById(R.id.answer_button);
        question=getIntent().getExtras().getString("question");
        questionText.setText(question);
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("question", String.valueOf(questionText.getText()));
        outState.putString("answer", String.valueOf(answerField.getText()));
    }
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        questionText.setText(savedInstanceState.getString("question"));
        answerField.setText(savedInstanceState.getString("answer"));
    }

    public void onClick(View view) {
        String answer= String.valueOf(answerField.getText()).trim();
        if(answer.isEmpty()|| answer.equals("Enter your answer!")){
            answerField.setText("Enter your answer!");
            return;
        }
        Intent answerIntent = new Intent();
        answerIntent.putExtra("question",question);
        answerIntent.putExtra("answer",answer);
        setResult(RESULT_OK,answerIntent);
        finish();
    }
}
