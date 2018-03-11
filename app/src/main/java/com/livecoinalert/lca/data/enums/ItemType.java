package com.livecoinalert.lca.data.enums;

import android.os.Parcel;
import android.os.Parcelable;

import com.dreampany.framework.data.enums.Type;

public enum ItemType implements Type {
    DEMO, ABOUT;

    @Override
    public boolean equals(Type type) {
        if (ItemType.class.isInstance(type)) {
            ItemType item = (ItemType) type;
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

    public static final Parcelable.Creator<ItemType> CREATOR = new Parcelable.Creator<ItemType>() {

        public ItemType createFromParcel(Parcel in) {
            return ItemType.valueOf(in.readInt());
        }

        public ItemType[] newArray(int size) {
            return new ItemType[size];
        }

    };

    public static ItemType valueOf(int ordinal) {
        switch (ordinal) {
            case 0:
                return DEMO;
            case 1:
                return ABOUT;
            default:
                return null;
        }
    }
}