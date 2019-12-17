package mmf.sharubapv.personssearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import mmf.sharubapv.personssearch.database.DataBase;
import mmf.sharubapv.personssearch.database.Person;
import mmf.sharubapv.personssearch.database.Repository;
import mmf.sharubapv.personssearch.util.DAO;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private DataBase database;
    private DAO dao;
    private Repository repository;

    private TextView resultsList;
    private EditText nameField;
    private EditText surnameField;
    private EditText dobField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.database = Room.databaseBuilder(getApplicationContext(),
                DataBase.class, "database").build();
        this.dao = this.database.dao();
        this.repository = new Repository(database);

        resultsList = findViewById(R.id.resultsList);
        nameField = findViewById(R.id.name);
        surnameField = findViewById(R.id.surname);
        dobField = findViewById(R.id.dob);
        Button findButton = findViewById(R.id.find);
        Button insertButton = findViewById(R.id.insert);

        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameField.getText().toString().trim();
                String surname = surnameField.getText().toString().trim();
                if (!name.isEmpty() && !surname.isEmpty()) {
                    List<Person> personList = null;
                    try {
                        personList = repository.findByFullName(name, surname);
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    resultsList.setText("");
                    for (Person p : personList)
                        resultsList.append(p.toString() + "\n");
                    return;
                }
                if (!name.isEmpty()) {
                    List<Person> personList = null;
                    try {
                        personList = repository.findByName(name);
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    resultsList.setText("");
                    for (Person p : personList)
                        resultsList.append(p.toString() + "\n");
                    return;
                }
                if (!surname.isEmpty()) {
                    List<Person> personList = null;
                    try {
                        personList = repository.findBySurname(surname);
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    resultsList.setText("");
                    for (Person p : personList)
                        resultsList.append(p.toString() + "\n");
                }
            }
        });
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person newPerson = new Person();
                String name = nameField.getText().toString().trim();
                String surname = surnameField.getText().toString().trim();
                String dob = dobField.getText().toString().trim();
                newPerson.setName(name);
                newPerson.setSurname(surname);
                newPerson.setDob(dob);
                if (!name.isEmpty() && !surname.isEmpty() && !dob.isEmpty()) {
                    repository.addPerson(newPerson);
                }
            }
        });
    }
}
