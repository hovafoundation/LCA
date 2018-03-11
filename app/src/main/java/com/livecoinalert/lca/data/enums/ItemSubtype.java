package com.livecoinalert.lca.data.enums;

import android.os.Parcel;
import android.os.Parcelable;

import com.dreampany.framework.data.enums.Type;

public enum ItemSubtype implements Type {
    ORIGINAL, TODAY, RANDOM, DISCOVER, SIMPLE, SEARCH, CATEGORY, FIND, LISTEN, MANUAL, CAMERA, GALLERY, FLAG;

    @Override
    public boolean equals(Type type) {
        if (ItemSubtype.class.isInstance(type)) {
            ItemSubtype item = (ItemSubtype) type;
            return compareTo(item) == 0;
        }
        return false;
    }

    @Override
    public int ordinalValue() {
        return ordinal();
    }

    @Override
    public String value() {
        return name();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<ItemSubtype> CREATOR = new Parcelable.Creator<ItemSubtype>() {

        public ItemSubtype createFromParcel(Parcel in) {
            return ItemSubtype.valueOf(in.readInt());
        }

        public ItemSubtype[] newArray(int size) {
            return new ItemSubtype[size];
        }

    };

    public static ItemSubtype valueOf(int ordinal) {
        switch (ordinal) {
            case 1:
                return TODAY;
            case 2:
                return RANDOM;
            default:
                return null;
        }
    }
}