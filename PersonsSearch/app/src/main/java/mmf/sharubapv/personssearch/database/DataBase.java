package mmf.sharubapv.personssearch.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import mmf.sharubapv.personssearch.util.DAO;

@Database(entities = {Person.class}, version = 1)
public abstract class DataBase extends RoomDatabase {
    public abstract DAO dao();
}
