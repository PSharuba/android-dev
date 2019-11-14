package mmf.sharubapv.servicesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView resultField;
    private TextView resultCount;
    private EditText numberCount;
    private Button sendButton;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        resultField = findViewById(R.id.resultField);
        resultCount = findViewById(R.id.resultCount);
        resultField.setMovementMethod(new ScrollingMovementMethod());
        numberCount = findViewById(R.id.numberCount);
        sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = numberCount.getText().toString();
                if (!text.trim().isEmpty()) {
                    int count = Integer.valueOf(text);
                    Intent intent = new Intent(MainActivity.this, SimpleNumberService.class);
                    intent.putExtra("NumberCount", count);
                    sendButton.setClickable(false);
                    startService(intent);
                }
            }
        });
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int count = intent.getIntExtra("resultCount", 0);
                resultCount.setText(R.string.primes);
                resultCount.append(""+count);
                String result = intent.getStringExtra("resultString");
                resultField.setText(result);
                sendButton.setClickable(true);
            }
        };
        IntentFilter intentFilter = new IntentFilter(
                "mmf.sharubapv.servicesapp.RESPONSE");
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(broadcastReceiver, intentFilter);
    }
}
