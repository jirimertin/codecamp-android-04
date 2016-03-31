package cz.ackee.codecamp04.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cz.ackee.codecamp04.R;
import cz.ackee.codecamp04.domain.Task;

/**
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {25.3.16}
 **/
public class TaskListViewAdapter extends BaseAdapter {
    public static final String TAG = TaskListViewAdapter.class.getName();

    private Context ctx;
    private final List<Task> tasks;

    public TaskListViewAdapter(@NonNull Context ctx, @NonNull List<Task> tasks) {
        this.ctx = ctx;
        this.tasks = tasks;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Task getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Recyclace viewholederu
        ViewHolder viewHolder;
//        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_task, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }

        viewHolder.textTask.setText(getItem(position).getTask());

        return convertView;
    }

    public void addTask(@NonNull Task task) {
        tasks.add(task);
        notifyDataSetChanged();
    }

    public void removeTask(long taskId) {
        tasks.remove((int) taskId);

        // Musime zavolat, aby se data obnovila
        notifyDataSetChanged();
    }

    class ViewHolder {
        public TextView textTask;

        public ViewHolder(View view) {
            textTask = (TextView) view.findViewById(R.id.text_task);
        }
    }
}
