package mmf.sharubapv.personssearch.database;

import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

import mmf.sharubapv.personssearch.util.DAO;

public class Repository {
    private DAO dao;

    public Repository(DataBase dataBase) {
        dao = dataBase.dao();

    }

    public void addPerson(Person person) {
        new insertAsyncTask(dao).execute(person);
    }

    public List<Person> findByName(String name) throws ExecutionException, InterruptedException {
        return new findByNameAsyncTask(dao).execute(name).get();
    }

    public List<Person> findBySurname(String surname) throws ExecutionException, InterruptedException {
        return new findBySurnameAsyncTask(dao).execute(surname).get();
    }

    public List<Person> findByFullName(String name, String surname) throws ExecutionException, InterruptedException {
        String[] params = new String[]{name, surname};
        return new findByFullNameAsyncTask(dao).execute(params).get();
    }


    private static class insertAsyncTask extends AsyncTask<Person, Void, Void> {
        private DAO asyncTaskDao;

        insertAsyncTask(DAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Person... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class findBySurnameAsyncTask extends AsyncTask<String, Void, List<Person>> {
        private DAO asyncTaskDao;

        findBySurnameAsyncTask(DAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected List<Person> doInBackground(final String... params) {
            return asyncTaskDao.getBySurname(params[0]);
        }
    }

    private static class findByNameAsyncTask extends AsyncTask<String, Void, List<Person>> {
        private DAO asyncTaskDao;

        findByNameAsyncTask(DAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected List<Person> doInBackground(final String... params) {
            return asyncTaskDao.getByName(params[0]);

        }
    }

    private static class findByFullNameAsyncTask extends AsyncTask<String, Void, List<Person>> {
        private DAO asyncTaskDao;

        findByFullNameAsyncTask(DAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected List<Person> doInBackground(final String... params) {
            return asyncTaskDao.getByFullName(params[0], params[1]);
        }
    }

}
