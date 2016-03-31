package cz.ackee.codecamp04.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cz.ackee.codecamp04.R;
import cz.ackee.codecamp04.domain.Task;
import cz.ackee.codecamp04.domain.TaskGenerator;
import cz.ackee.codecamp04.ui.adapter.TaskRecyclerAdapter;

/**
 * Fragment with recycler view filled with generated tasks
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {31.3.16}
 **/
public class RecyclerGridFragment extends Fragment {
    public static final String TAG = RecyclerGridFragment.class.getName();
    private static final String TASKS_KEY = "tasks";
    private ArrayList<Task> mTasks;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler_with_tasks, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recycler = (RecyclerView) view.findViewById(R.id.recycler_tasks);

        // Musime nasetovat layout manager, jinak se nam recycler nezobrazi
        recycler.setLayoutManager(new GridLayoutManager(getActivity(), 4, LinearLayoutManager.HORIZONTAL, false));

        if (savedInstanceState == null) {
            mTasks = TaskGenerator.generateTasks(500);
        } else {
            mTasks = savedInstanceState.getParcelableArrayList(TASKS_KEY);
        }
        recycler.setAdapter(new TaskRecyclerAdapter(mTasks));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(TASKS_KEY, mTasks);
    }
}
