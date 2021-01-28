package com.dam.prueba.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.dam.prueba.db.dao.NotaDao;
import com.dam.prueba.db.entity.NotaEntity;

@Database(entities = {NotaEntity.class}, version = 1)
public abstract class NotaRoomDatabase extends RoomDatabase {
    public abstract NotaDao notaDao();
    private static volatile NotaRoomDatabase INSTANCE;

    public static NotaRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NotaRoomDatabase.class){
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NotaRoomDatabase.class, "notas_database")
                            .build();
                }
            }
        }
        return INSTANCE;

    }



}
