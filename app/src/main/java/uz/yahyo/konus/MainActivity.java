package uz.yahyo.konus;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.calculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });

        getSupportActionBar().setTitle(R.string.app_name2);
    }

    void calculate() {

        TextView natija = findViewById(R.id.natija);
        ImageView image = findViewById(R.id.imageView);
        String x1;
        String y1;
        String a1;
        try {
            Double r1 = Double.valueOf(((TextView) findViewById(R.id.radius1)).getText().toString());
            Double r2 = Double.valueOf(((TextView) findViewById(R.id.radius2)).getText().toString());
            Double h = Double.valueOf(((TextView) findViewById(R.id.height)).getText().toString());

            if (r2 - r1 <= 0 || h <= 0 || r1 == 0 || r2 == 0) {
                throw new RuntimeException();
            }

            Double x = Math.sqrt((h * h + (r2 - r1) * (r2 - r1))) / (1 - (r1 / r2));
            Double y = r1 * x / r2;
            Double a = 360 * r2 / x;

            x1 = "" + (((int) (x * 100)) / 100.0);
            y1 = "" + (((int) (y * 100)) / 100.0);
            a1 = "" + (((int) (a * 100)) / 100.0);
        } catch (Exception e) {
            Toast.makeText(this, "Тўғри киритинг!", Toast.LENGTH_LONG).show();
            natija.setVisibility(View.GONE);
            image.setVisibility(View.GONE);
            Log.e("MainActivity", e.getMessage(), e);
            return;
        }

        natija.setText("Натижа: x=" + x1 + "; y=" + y1 + "; α=" + a1 +"°");

        natija.setVisibility(View.VISIBLE);
        image.setVisibility(View.VISIBLE);

        hideSoftInput(findViewById(R.id.calculate));
    }

    public void hideSoftInput(final View view) {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm == null) return;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }
    }


}
