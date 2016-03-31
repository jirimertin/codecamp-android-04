package cz.ackee.codecamp04.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import cz.ackee.codecamp04.R;
import cz.ackee.codecamp04.domain.Task;
import cz.ackee.codecamp04.domain.TaskGenerator;
import cz.ackee.codecamp04.ui.adapter.TaskListViewAdapter;

/**
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {25.3.16}
 **/
public class ListTasksFragment extends Fragment {
    public static final String TAG = ListTasksFragment.class.getName();
    private static final String TASKS_KEY = "tasks";

    private ArrayList<Task> mTasks;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_tasks, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = (ListView) view.findViewById(R.id.list_view_tasks);

        if (savedInstanceState == null) {
            mTasks = TaskGenerator.generateTasks(500);
        } else {
            mTasks = savedInstanceState.getParcelableArrayList(TASKS_KEY);
        }

        listView.setAdapter(new TaskListViewAdapter(getActivity(), mTasks));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(TASKS_KEY, mTasks);
    }
}
