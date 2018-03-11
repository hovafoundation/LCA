package com.livecoinalert.lca.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.dreampany.framework.data.model.Base;
import com.google.common.base.Objects;

import org.jetbrains.annotations.NotNull;

/**
 * Created by nuc on 2/24/2017.
 */

@Entity(indices = {@Index(value = "id", unique = true)})
public class Demo extends Base {

    @PrimaryKey
    @NonNull
    private String id;

    @Ignore
    public Demo() {
    }

    public Demo(@NotNull String id) {
        this.id = id;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void copyWord(Demo from) {

    }



    @Ignore
    private Demo(Parcel in) {
        super(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    public static final Parcelable.Creator<Demo> CREATOR = new Parcelable.Creator<Demo>() {
        @Override
        public Demo createFromParcel(Parcel in) {
            return new Demo(in);
        }

        @Override
        public Demo[] newArray(int size) {
            return new Demo[size];
        }
    };

    @Override
    public boolean equals(Object inObject) {
        if (Demo.class.isInstance(inObject)) {
            Demo item = (Demo) inObject;
            return this.id.equals(item.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return id;
    }

}
