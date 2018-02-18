package pl.dogsinmylife.database;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = DB.NAME, version = DB.VERSION)
public class DB {
    public static final String NAME = "DogsInMyLifeDB";

    public static final int VERSION = 1;
}
