package emeline.mysekai;

import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.*;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends FragmentActivity
{
    private TextView tvfirst_name, tvlast_namee, tvfull_name, tvEmail;
    private CallbackManager callbackManager;
    LoginButton login_button;
    static String id, email,name,first_name,last_name;

    static Session session;

    DBHandler db;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_main);

        login_button        = (LoginButton) findViewById(R.id.login_button);

        login_button.setReadPermissions(Arrays.asList("public_profile","email"));
        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>()
        {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                login_button.setVisibility(View.GONE);

                GraphRequest graphRequest   =   GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback()
                {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response)
                    {
                        Log.d("JSON", ""+response.getJSONObject().toString());
                        try
                        {
                            id = object.getString("id");
                            email       =   object.getString("email");
                            name        =   object.getString("name");
                            first_name  =   object.optString("first_name");
                            last_name   =   object.optString("last_name");

                            User user = new User(id, last_name, first_name, email, "0");
                            System.out.println("user.first_name : " + user.first_name + " MainActivity.first_name : " + first_name);

                            Context context = getApplicationContext();

                            UserRepo repo = new UserRepo(context);
                            boolean x = repo.test(user.id);

                            if(x = false) repo.insert(user);

                            repo.getUserList();

                            CountryRepo repo_c = new CountryRepo(context);
                            repo_c.getCountryList();

                            User user2 = repo.getUserById(user.id);
                            user = user2;
                            Session.logIn(user);

                            LoginManager.getInstance().logOut();
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                            System.out.println("catch" + e);
                        }
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,first_name,last_name,email");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
                System.out.println("succès");

                //Open the HomeActivity
                HomeActivity home = new HomeActivity();
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel()
            {
                System.out.println("cancel");
            }

            @Override
            public void onError(FacebookException exception)
            {
                System.out.println(exception.toString());
                System.out.println("erreur");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}

