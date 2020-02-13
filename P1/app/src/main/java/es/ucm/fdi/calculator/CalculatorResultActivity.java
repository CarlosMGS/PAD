package es.ucm.fdi.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;



public class CalculatorResultActivity extends AppCompatActivity {

    private static final String CalculatorResultActivityTag = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(CalculatorResultActivityTag, "Calculating...");
        setContentView(R.layout.activity_calculator_result);

        Intent intent = getIntent();
        int result = intent.getIntExtra(MainActivity.EXTRA_ADD_RESULT, 0);

        TextView result_text = findViewById(R.id.result_text);
        result_text.setText("Result is " + result);

    }
}
