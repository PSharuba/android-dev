package mmf.sharubapv.fragments;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView answerField;
    EditText questionField;
    Button submitButton;
    public static final String EXIT = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        answerField = (TextView) findViewById(R.id.text_view);
        questionField = (EditText) findViewById(R.id.text_edit);
        submitButton = (Button) findViewById(R.id.button);

        submitButton.setText(R.string.submit_label);
        questionField.setHint(R.string.question_hint);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
                intent.putExtra("question", String.valueOf(questionField.getText()).trim());
                startActivityForResult(intent, 1);
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
        outState.putString("question", String.valueOf(questionField.getText()));
        outState.putString("answer", String.valueOf(answerField.getText()));
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        questionField.setText(savedInstanceState.getString("question"));
        answerField.setText(savedInstanceState.getString("answer"));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1)
            if (resultCode == RESULT_OK) {
                if (data.getStringExtra(EXIT) != null && data.getStringExtra(EXIT).equals("EXIT")) {
                    finishAndRemoveTask();
                }
                String answer = data.getStringExtra("answer");
                String question = data.getStringExtra("question");
                answerField.setText(getString(R.string.text_view_template_question));
                answerField.append(question);
                answerField.append(getString(R.string.text_view_template_answer));
                answerField.append(answer);
            } else {
                answerField.setText(getString(R.string.text_view_template_ingore));
            }
    }

}
