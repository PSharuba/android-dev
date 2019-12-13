package com.example.task5.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PersonDao {
    @Query("SELECT id, first_name, last_name, date_of_birth FROM person " +
            "WHERE first_name LIKE '%' || :firstName || '%' AND last_name LIKE '%' || :lastName || '%'")
    List<Person> getAllByFirstAndLastNameOrPartOfThem(String firstName, String lastName);

    @Query("SELECT id, first_name, last_name, date_of_birth FROM person WHERE last_name LIKE '%' || :lastName || '%' ")
    List<Person> getAllByLastNameOrPartOfThem(String lastName);

    @Query("SELECT id, first_name, last_name, date_of_birth FROM person WHERE first_name LIKE '%' || :firstName || '%' ")
    List<Person> getAllByFirstNameOrPartOfThem(String firstName);

    @Insert
    void insert(Person person);
}
