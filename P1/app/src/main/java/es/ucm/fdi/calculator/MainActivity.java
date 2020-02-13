package es.ucm.fdi.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    Calculator calculator;
    EditText editTextX;
    EditText editTextY;
    static final String EXTRA_ADD_RESULT = "es.ucm.fdi.calculator.EXTRA_ADD_RESULT";
    private static final String MainActivityTag = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(MainActivityTag, "Creating the view...");
        setContentView(R.layout.activity_main);

        calculator = new Calculator();
        editTextX = (EditText) findViewById(R.id.editTextX);
        editTextY = (EditText) findViewById(R.id.editTextY);







    }

    public void addXandY(View View) {

        Log.d(MainActivityTag, "Adding...");

        int x = Integer.parseInt(editTextX.getText().toString());
        int y = Integer.parseInt(editTextY.getText().toString());
        int result = calculator.add(x, y);
        Intent showResultIntent = new Intent(this, CalculatorResultActivity.class);
        showResultIntent.putExtra(EXTRA_ADD_RESULT, result);
        startActivity(showResultIntent);
    }



}
