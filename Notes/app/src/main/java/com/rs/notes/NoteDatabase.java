package com.rs.notes;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Note.class, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };


    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao notedao;

        private PopulateDbAsyncTask(NoteDatabase db) {
            notedao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            notedao.insert(new Note("Title 1", "Description1", 1));
            notedao.insert(new Note("Title 2", "Description2", 4));
            notedao.insert(new Note("Title 3", "Description3", 2));
            notedao.insert(new Note("Title 4", "Description4", 3));
            return null;
        }
    }

}
