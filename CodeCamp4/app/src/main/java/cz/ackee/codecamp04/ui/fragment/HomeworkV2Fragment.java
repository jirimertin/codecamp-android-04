package cz.ackee.codecamp04.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import cz.ackee.codecamp04.R;
import cz.ackee.codecamp04.domain.Task;
import cz.ackee.codecamp04.ui.adapter.TaskRecyclerV2Adapter;
import cz.ackee.codecamp04.ui.fragment.dialog.DeleteDialogFragment;

/**
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {25.3.16}
 **/
public class HomeworkV2Fragment extends Fragment implements DeleteDialogFragment.SimpleDialogActionListener {
    public static final String TAG = HomeworkV2Fragment.class.getName();
    private static final String TASKS_KEY = "tasks";

    private RecyclerView recyclerView;
    private EditText editTask;
    private ArrayList<Task> tasks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Obnova dat
        if (savedInstanceState != null) {
            tasks = savedInstanceState.getParcelableArrayList(TASKS_KEY);
        } else {
            tasks = new ArrayList<>();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_homework_v2, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_homework);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        TaskRecyclerV2Adapter adapter = new TaskRecyclerV2Adapter(tasks);
        recyclerView.setAdapter(adapter);


        adapter.setOnTaskItemClickListener(new TaskRecyclerV2Adapter.OnTaskItemClickListener() {
            @Override
            public void onItemClick(int taskPosition) {
                DeleteDialogFragment.createInstance(HomeworkV2Fragment.this, taskPosition).show(getFragmentManager(), TAG);
            }
        });

        editTask = (EditText) view.findViewById(R.id.edit_todo);
        Button btnCreateTask = (Button) view.findViewById(R.id.btn_create_task);
        btnCreateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (areDataValid()) {
                    String text = editTask.getText().toString();
                    editTask.getText().clear();

                    Task task = new Task(text);
                    onNewTaskCreated(task);
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(TASKS_KEY, tasks);
    }

    @Override
    public void onTaskDone(long taskId) {
        TaskRecyclerV2Adapter adapter = (TaskRecyclerV2Adapter) recyclerView.getAdapter();
        adapter.removeTask(taskId);
    }

    private void onNewTaskCreated(@NonNull Task task) {
        TaskRecyclerV2Adapter adapter = (TaskRecyclerV2Adapter) recyclerView.getAdapter();
        adapter.addTask(task);
    }

    /**
     * Validace dat
     *
     * @return true if valid
     */
    private boolean areDataValid() {
        boolean valid = !TextUtils.isEmpty(editTask.getText().toString());
        if (!valid) {
            editTask.setError(getString(R.string.error_empty_task));
        }
        return valid;
    }
}
