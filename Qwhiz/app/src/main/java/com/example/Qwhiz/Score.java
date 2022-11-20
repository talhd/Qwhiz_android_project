package com.example.Qwhiz;

import android.os.Parcel;
import android.os.Parcelable;

public class Score implements Parcelable
{
    int score;
    String time;
    String category;

    protected Score(Parcel in) {
        score = in.readInt();
        time = in.readString();
        category = in.readString();
    }

    public static final Creator< Score > CREATOR = new Creator< Score >() {
        @Override
        public Score createFromParcel(Parcel in) {
            return new Score(in);
        }

        @Override
        public Score[] newArray(int size) {
            return new Score[size];
        }
    };

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Score(int score, String time, String category) {
        this.score = score;
        this.time = time;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Score{" +
                "score=" + score +
                ", time=" + time +
                ", category='" + category + '\'' +
                '}';
    }

    public int getScore() {
        return score*10;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public Score() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(score);
        dest.writeString(time);
        dest.writeString(category);
    }
}