package mmf.sharubapv.servicesapp;

import android.app.IntentService;
import android.content.Intent;

public class SimpleNumberService extends IntentService {

    public SimpleNumberService() {
        super("SimpleNumberService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int numberCount = intent.getIntExtra("NumberCount", 10);
        int primeCount = 0;
        StringBuilder primeNumbers = new StringBuilder();
        for (int i = 2; i <= numberCount; i++) {
            boolean isPrime = true;
            for (int j = 2; j <= i / 2; j++) {
                int tmp = i % j;
                if (tmp == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primeCount++;
                primeNumbers.append(i).append(" ");
            }
        }


        Intent answerIntent = new Intent();

        answerIntent.setAction("mmf.sharubapv.servicesapp.RESPONSE");
        answerIntent.addCategory(Intent.CATEGORY_DEFAULT);
        answerIntent.putExtra("resultCount", primeCount);
        answerIntent.putExtra("resultString", primeNumbers.toString());
        sendBroadcast(answerIntent);
    }
}
