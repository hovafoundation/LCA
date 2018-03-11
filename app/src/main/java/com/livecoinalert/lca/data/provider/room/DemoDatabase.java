package com.livecoinalert.lca.data.provider.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.livecoinalert.lca.data.model.Demo;
import com.dreampany.framework.data.provider.room.Converter;

/**
 * Created by air on 10/17/17.
 */

@Database(entities = {Demo.class}, version = 1)
@TypeConverters({Converter.class})
public abstract class DemoDatabase extends RoomDatabase {
    private static final String DATABASE = "word-db";
    private static volatile DemoDatabase instance;

    public abstract DemoDao wordDao();

    synchronized public static DemoDatabase onInstance(Context context) {
        if (instance == null) {
            instance = newInstance(context, false);
        }
        return instance;
    }

    public static DemoDatabase newInstance(Context context, boolean memoryOnly) {
        RoomDatabase.Builder<DemoDatabase> builder;

        if (memoryOnly) {
            builder = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), DemoDatabase.class);
        } else {
            builder = Room.databaseBuilder(context.getApplicationContext(), DemoDatabase.class, DATABASE);
        }

        return builder.build();
    }

}
