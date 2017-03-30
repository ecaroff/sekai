package emeline.mysekai;

public class User{

    //Labels table name
    public static final String TABLE = "users";

    //Labels Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_LAST_NAME = "last_name";
    public static final String KEY_FIRST_NAME = "first_name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_COUNTRY = "country";

    public long id;
    public String last_name;
    public String first_name;
    public String email;
    public long home_country;

    public User()
    {
        this.id = 0;
        this.last_name=null;
        this.first_name=null;
        this.email=null;
    }

    public User(String id, String last_name, String first_name, String email)
    {
        this.id = Long.parseLong(id);
        this.last_name=last_name;
        this.first_name=first_name;
        this.email=email;
    }

    public User(String id, String last_name, String first_name, String email, String home_country)
    {
        this.id = Long.parseLong(id);
        this.last_name=last_name;
        this.first_name=first_name;
        this.email=email;
        this.home_country = Long.parseLong(home_country);
    }

    public long getId() { return id; }
    public String getLast_name() {
        return last_name;
    }
    public String getFirst_name() {
        return first_name;
    }
    public String getEmail() {
        return email;
    }
    public long getHome_country() { return home_country; }
}
