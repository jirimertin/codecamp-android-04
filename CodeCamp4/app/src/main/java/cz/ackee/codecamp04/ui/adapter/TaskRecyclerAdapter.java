package cz.ackee.codecamp04.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cz.ackee.codecamp04.R;
import cz.ackee.codecamp04.domain.Task;

/**
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {25.3.16}
 **/
public class TaskRecyclerAdapter extends RecyclerView.Adapter<TaskRecyclerAdapter.ViewHolder> {
    public static final String TAG = TaskRecyclerAdapter.class.getName();

    private final List<Task> mTaskList;

    public TaskRecyclerAdapter(@NonNull List<Task> taskList) {
        mTaskList = taskList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Task task = mTaskList.get(position);
        holder.textTask.setText(task.getTask());
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textTask;

        public ViewHolder(View itemView) {
            super(itemView);
            textTask = (TextView) itemView.findViewById(R.id.text_task);
        }
    }
}
