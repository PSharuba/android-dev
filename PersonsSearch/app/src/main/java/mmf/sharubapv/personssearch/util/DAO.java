package mmf.sharubapv.personssearch.util;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import mmf.sharubapv.personssearch.database.Person;

@Dao
public interface DAO {
    @Query("SELECT * FROM person")
    List<Person> getAll();

    @Query("SELECT * FROM person WHERE name LIKE '%'||:name||'%' AND surname LIKE'%'||:surname||'%'")
    List<Person> getByFullName(String name, String surname);

    @Query("SELECT * FROM person WHERE name LIKE'%'||:name||'%'")
    List<Person> getByName(String name);

    @Query("SELECT * FROM person WHERE surname LIKE'%'||:surname||'%'")
    List<Person> getBySurname(String surname);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Person person);

    @Delete
    void delete(Person person);
}
