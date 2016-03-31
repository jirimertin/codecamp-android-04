package cz.ackee.codecamp04.ui.fragment.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import cz.ackee.codecamp04.R;

/**
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {18.3.16}
 **/
public class DeleteDialogFragment extends DialogFragment {
    public static final String TAG = DeleteDialogFragment.class.getName();
    public static final int REQUEST_CODE = 123;
    private static final String TASK_ID_KEY = "task_id";


    public static DeleteDialogFragment createInstance(@NonNull Fragment target, long id) {
        DeleteDialogFragment toReturn = new DeleteDialogFragment();
        toReturn.setTargetFragment(target, REQUEST_CODE);

        Bundle bundle = new Bundle();
        bundle.putLong(TASK_ID_KEY, id);
        toReturn.setArguments(bundle);

        return toReturn;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SimpleDialogActionListener listener = (SimpleDialogActionListener) getTargetFragment();
                if (listener != null) {
                    long taskId = getArguments().getLong(TASK_ID_KEY);
                    listener.onTaskDone(taskId);
                } else {
                    throw new UnsupportedOperationException("Target fragment does not implement SimpleDialogActionListener");
                }
            }
        });
        builder.setNegativeButton(R.string.cancel, null);
        builder.setTitle(R.string.delete_dialog_title);
        return builder.create();
    }

    public interface SimpleDialogActionListener {
        void onTaskDone(long taskId);
    }
}
