package cz.ackee.codecamp04.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Task POJO
 * <p/>
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {25.3.16}
 **/
public class Task implements Parcelable {
    public static final String TAG = Task.class.getName();

    private final String task;


    public Task(String task) {
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.task);
    }

    protected Task(Parcel in) {
        this.task = in.readString();
    }

    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {
        public Task createFromParcel(Parcel source) {
            return new Task(source);
        }

        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
}
