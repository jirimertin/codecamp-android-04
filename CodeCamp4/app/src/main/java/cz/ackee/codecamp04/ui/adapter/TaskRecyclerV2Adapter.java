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
public class TaskRecyclerV2Adapter extends RecyclerView.Adapter<TaskRecyclerV2Adapter.ViewHolder> {
    public static final String TAG = TaskRecyclerV2Adapter.class.getName();

    private final List<Task> mTaskList;
    private OnTaskItemClickListener mListener;

    public TaskRecyclerV2Adapter(@NonNull List<Task> taskList) {
        mTaskList = taskList;
    }

    public void addTask(@NonNull Task task) {
        mTaskList.add(task);
        notifyItemInserted(mTaskList.size() - 1);
    }

    public void removeTask(long taskId) {
        mTaskList.remove((int) taskId);
        notifyItemRemoved((int) taskId);
    }

    public void setOnTaskItemClickListener(OnTaskItemClickListener listener) {
        mListener = listener;
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

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textTask;

        public ViewHolder(View itemView) {
            super(itemView);
            textTask = (TextView) itemView.findViewById(R.id.text_task);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(getAdapterPosition());
            }
        }
    }

    public interface OnTaskItemClickListener {

        void onItemClick(int taskPosition);
    }
}
