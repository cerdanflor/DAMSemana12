package com.dam.prueba;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.dam.prueba.db.NotaRoomDatabase;
import com.dam.prueba.db.dao.NotaDao;
import com.dam.prueba.db.entity.NotaEntity;

import java.util.List;

public class NotaRepository {
    private NotaDao notaDao;
    private LiveData<List<NotaEntity>> allNotas;
    private LiveData<List<NotaEntity>> allNotasFavoritas;

    // Constructor
    public NotaRepository(Application application) {
        NotaRoomDatabase db = NotaRoomDatabase.getDatabase(application);
        notaDao = db.notaDao();
        allNotas = notaDao.getAll();
        allNotasFavoritas = notaDao.getAllFavoritas();
    }
    public LiveData<List<NotaEntity>> getAll(){
        return allNotas;
    }
    public LiveData<List<NotaEntity>> getallFavs(){
        return allNotasFavoritas;
    }
    public void insert(NotaEntity nota){
        // Luego de crear la clase insertAsyntask
        // completamos este método.
        new insertAsyntask(notaDao).execute(nota);
    }

    // 1.
    // Asyntask, es la forma de decir que es una
    // clase que va a ejecutar acciones en segundo plano
    // El AsyncTask recibe 3 parámetros pero nosotros solo trabajaremos con el primero
    public static class insertAsyntask extends AsyncTask<NotaEntity, Void, Void>{
        private NotaDao notaDaoAsynTask;

        // 3. Creamos el constructor
        // Para instanciar nuestro notaDaoAsynTask con el dao que recibimos
        insertAsyntask(NotaDao dao){
            notaDaoAsynTask = dao;
        }

        // 2. Debemos generar el método doInBakground
        // Que va a ejecutar la inserción de elementos en la BD en segundo plano
        @Override
        protected Void doInBackground(NotaEntity... notaEntities) {
            // HAcemos el insert del primer parámetro
            // (Que en realidad es la única)que nos han pasado
            notaDaoAsynTask.insert((notaEntities[0]));
            return null;
        }
    }
    public void update(NotaEntity nota){
        new updateAsyntask(notaDao).execute(nota);
    }
    public static class updateAsyntask extends AsyncTask<NotaEntity, Void, Void>{
        private NotaDao notaDaoAsynTask;

        // 3. Creamos el constructor
        // Para instanciar nuestro notaDaoAsynTask con el dao que recibimos
        updateAsyntask(NotaDao dao){
            notaDaoAsynTask = dao;
        }

        // 2. Debemos generar el método doInBakground
        // Que va a ejecutar la inserción de elementos en la BD en segundo plano
        @Override
        protected Void doInBackground(NotaEntity... notaEntities) {
            // HAcemos el insert del primer parámetro
            // (Que en realidad es la única)que nos han pasado
            notaDaoAsynTask.update(notaEntities[0]);
            return null;
        }


    }
}
