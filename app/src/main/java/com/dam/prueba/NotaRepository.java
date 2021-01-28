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

    public NotaRepository(Application application) {
        NotaRoomDatabase db = NotaRoomDatabase.getDatabase(application);
        notaDao = db.notaDao();
        allNotas = notaDao.getAll();
        allNotasFavoritas = notaDao.getAllFavoritas();
    }

    // Equivalente a Métodos Accesores.

    // Para obtener
    public LiveData<List<NotaEntity>> getAll(){
        return allNotas;
    }
    public LiveData<List<NotaEntity>> getAllFavs(){
        return allNotasFavoritas;
    }

    // Para insertar las notas
    public void insert(NotaEntity nota){
        //
    }

    public static class insertAsyntask extends AsyncTask<NotaEntity, Void, Void>{
        private NotaDao notaDaoAsyntask;

        // Constructor de la class insertAsyntask
        insertAsyntask(NotaDao dao){
            notaDaoAsyntask = dao;
        }

        // Método doInBackground
        @Override
        protected Void doInBackground(NotaEntity... notaEntities) {
            notaDaoAsyntask.insert((notaEntities[0]));
            return null;
        }
    }
}
