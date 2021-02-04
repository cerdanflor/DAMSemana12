package com.dam.prueba;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dam.prueba.db.entity.NotaEntity;

import java.util.List;

public class NuevaNotaDialogViewModel extends AndroidViewModel {
    private LiveData<List<NotaEntity>> allNotas;
    // Para hacer la inserción de la nota vamos a crear una NotaRepository
    // Porque el repositorio es quien nos facilita.
    private NotaRepository notaRepository;

    //VAmos a crear un constructor
    public NuevaNotaDialogViewModel(Application application) {
        super(application);
        notaRepository = new NotaRepository(application);
        allNotas = notaRepository.getAll();
    }

    //Ahora creamos un método que devuelva la lista
    // Para el fragmento que necesita recibir la nueva lista de datos
    public LiveData<List<NotaEntity>> getAllNotas(){
        return allNotas;
    }

    // El fragmento que inserte una nueva nota, deberá comunicarlo a este viewmodel
    public void insertarNota(NotaEntity nuevaNotaEntity){
        notaRepository.insert(nuevaNotaEntity);
    }

    public void updateNota(NotaEntity notaActualizarEntity){
        notaRepository.update(notaActualizarEntity);
    }
}