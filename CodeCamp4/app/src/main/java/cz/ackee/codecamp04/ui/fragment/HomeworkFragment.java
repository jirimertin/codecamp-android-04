package cz.ackee.codecamp04.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import cz.ackee.codecamp04.R;
import cz.ackee.codecamp04.domain.Task;
import cz.ackee.codecamp04.ui.adapter.TaskListViewAdapter;
import cz.ackee.codecamp04.ui.fragment.dialog.DeleteDialogFragment;

/**
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {25.3.16}
 **/
public class HomeworkFragment extends Fragment implements DeleteDialogFragment.SimpleDialogActionListener {
    public static final String TAG = HomeworkFragment.class.getName();
    private static final String TASKS_KEY = "tasks";

    private ListView listView;
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
        return inflater.inflate(R.layout.fragment_homework, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.list_view_homework);
        listView.setAdapter(new TaskListViewAdapter(getActivity(), tasks));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DeleteDialogFragment.createInstance(HomeworkFragment.this, id).show(getFragmentManager(), TAG);
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
        TaskListViewAdapter adapter = (TaskListViewAdapter) listView.getAdapter();
        adapter.removeTask(taskId);
    }

    private void onNewTaskCreated(@NonNull Task task) {
        TaskListViewAdapter adapter = (TaskListViewAdapter) listView.getAdapter();
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
