package mmf.sharubapv.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {
    TextView questionText;
    EditText answerField;
    Button answerButton;
    String question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        answerField = (EditText) findViewById(R.id.text_edit);
        questionText = (TextView) findViewById(R.id.text_view);
        answerButton = (Button) findViewById(R.id.button);

        question = getIntent().getExtras().getString("question");
        questionText.setText(question);
        answerButton.setText(R.string.answer_label);

        answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = String.valueOf(answerField.getText()).trim();
                if (answer.isEmpty() || answer.equals("Enter your answer!")) {
                    answerField.setText(R.string.no_answer);
                    return;
                }
                Intent answerIntent = new Intent();
                answerIntent.putExtra("question", question);
                answerIntent.putExtra("answer", answer);
                setResult(RESULT_OK, answerIntent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_exit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.exit_button) {
            ExitDialog exitDialog = new ExitDialog();
            exitDialog.show(getSupportFragmentManager(), "exitDialog");
            return true;
        }
        return super.onOptionsItemSelected(item);
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

    @Override
    public void finishAndRemoveTask() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.EXIT, "EXIT");
        setResult(RESULT_OK, intent);
        super.finishAndRemoveTask();
    }
}
