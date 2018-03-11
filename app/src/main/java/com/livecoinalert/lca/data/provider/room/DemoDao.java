package com.livecoinalert.lca.data.provider.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.livecoinalert.lca.data.model.Demo;

import java.util.List;

/**
 * Created by air on 10/17/17.
 */

@Dao
public interface DemoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Demo item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Demo... items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Demo> items);

    @Query("select count(*) from demo")
    int count();

    @Update
    void update(Demo item);

    @Delete
    void delete(Demo item);
}
