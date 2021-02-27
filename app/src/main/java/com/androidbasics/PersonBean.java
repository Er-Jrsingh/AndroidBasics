package com.androidbasics;

import android.os.Parcel;
import android.os.Parcelable;

public class PersonBean implements Parcelable {
    private String  f_name;
    private String  l_name;
    private int  age;

    public PersonBean(String f_name, String l_name, int age) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.age = age;
    }

    public String getF_name() {
        return f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.f_name);
        dest.writeString(this.l_name);
        dest.writeInt(this.age);
    }

    public void readFromParcel(Parcel source) {
        this.f_name = source.readString();
        this.l_name = source.readString();
        this.age = source.readInt();
    }

    protected PersonBean(Parcel in) {
        this.f_name = in.readString();
        this.l_name = in.readString();
        this.age = in.readInt();
    }

    public static final Parcelable.Creator<PersonBean> CREATOR = new Parcelable.Creator<PersonBean>() {
        @Override
        public PersonBean createFromParcel(Parcel source) {
            return new PersonBean(source);
        }

        @Override
        public PersonBean[] newArray(int size) {
            return new PersonBean[size];
        }
    };
}
