package emeline.mysekai;

import android.content.Context;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by emeli on 28/03/2017.
 */

public class Session {

    public static User user = new User();

    public Session()
    {}

    public static void logIn(User theuser)
    {
        user = theuser;
    }

    public static String getFirstName()
    {
        return user.first_name;
    }

    public static String getLastName() { return user.last_name; }

    public static String getFullName()
    {
        return user.first_name + " " + user.last_name;
    }

    public static long getId()
    {
        return user.id;
    }

    public static String getMail()
    {
        return user.email;
    }

    public static String getCountry()
    {
        if(user.home_country==0) return "Unfilled";
        else
        {
            Context context = getApplicationContext();
            CountryRepo repo = new CountryRepo(context);
            Country country = repo.getCountryById(user.id);
            return country.getName();
        }
    }
}
