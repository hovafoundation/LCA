package com.livecoinalert.lca.data.enums;

import android.os.Parcel;
import android.os.Parcelable;

import com.dreampany.framework.data.enums.Type;

public enum ItemState implements Type {
    RAW, BUILT, ERROR, OFFLINE, YES, NO, FULL, VIEWED, FLAG, PLAYED, SKIPPED, DISCOVERED;

    @Override
    public boolean equals(Type type) {
        if (ItemState.class.isInstance(type)) {
            ItemState item = (ItemState) type;
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

    public static final Parcelable.Creator<ItemState> CREATOR = new Parcelable.Creator<ItemState>() {

        public ItemState createFromParcel(Parcel in) {
            return ItemState.valueOf(in.readInt());
        }

        public ItemState[] newArray(int size) {
            return new ItemState[size];
        }

    };

    public static ItemState valueOf(int ordinal) {
        switch (ordinal) {
            case 0:
                return RAW;
            case 1:
                return BUILT;
            case 2:
                return ERROR;
            case 3:
                return OFFLINE;
            case 4:
                return YES;
            case 5:
                return NO;
            default:
                return null;
        }
    }
}