package emeline.mysekai;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView textView;
    Button button_opinion;
    Button button_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();

        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(getString(R.string.welcome_message_part1) + " " + "utilisateur" + " " + getString(R.string.welcome_message_part2));

        button_opinion = (Button) findViewById(R.id.button_opinion);
        button_opinion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MapActivity opinion = new MapActivity();
                Intent intent = new Intent(HomeActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
    }

}
