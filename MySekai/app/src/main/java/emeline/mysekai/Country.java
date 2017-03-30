package emeline.mysekai;

/**
 * Created by emeli on 28/03/2017.
 */

public class Country {

    //Labels table name
    public static final String TABLE = "countries";

    //Labels Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_LANGUAGE = "language";

    public long id;
    public String name;
    public String language;

    public Country() {}

    public Country(long id, String name, String language)
    {
        this.id = id;
        this.name = name;
        this.language = language;
    }

    public long getId() { return id; }
    public String getName() {
        return name;
    }
    public String getLanguage() {
        return language;
    }
}
