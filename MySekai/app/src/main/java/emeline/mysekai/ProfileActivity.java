package emeline.mysekai;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Context context = getApplicationContext();

        final TextView tv_fullName = (TextView) findViewById(R.id.tv_fullName);
        tv_fullName.setText("Full name : " + Session.getFullName());
        final TextView tv_email = (TextView) findViewById(R.id.tv_email);
        tv_email.setText("Mail adress : " + Session.getMail());
        final TextView tv_country = (TextView) findViewById(R.id.tv_country);
        tv_country.setText("Home country : " + Session.getCountry());

        final EditText et_firstName = (EditText) findViewById(R.id.et_firstName);
        et_firstName.setText(Session.getFirstName());
        final EditText et_lastName = (EditText) findViewById(R.id.et_lastName);
        et_lastName.setText(Session.getLastName());
        final EditText et_mail = (EditText) findViewById(R.id.et_Mail);
        et_mail.setText(Session.getMail());

        final Spinner spinner_country = (Spinner) findViewById(R.id.spinner);
        CountryRepo cp = new CountryRepo(context);
        ArrayList<String> countryList = cp.getCountryList2();

        ArrayAdapter<String> adp1=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, countryList);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_country.setAdapter(adp1);

        final Button button_OK = (Button) findViewById(R.id.button_OK);
        button_OK.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CountryRepo cp = new CountryRepo(getApplicationContext());
                User user = new User(Session.getId(), et_lastName.getText().toString(), et_firstName.getText().toString(), et_mail.getText().toString(), cp.getIdbyName(spinner_country.getSelectedItem().toString()));
                System.out.println("Submit changes : new user : first_name = " + user.first_name + " last_name " + user.last_name);
                UserRepo up = new UserRepo(getApplicationContext());
                up.update(user);
                Session.logIn(user);
                ProfileActivity profile = new ProfileActivity();
                Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
