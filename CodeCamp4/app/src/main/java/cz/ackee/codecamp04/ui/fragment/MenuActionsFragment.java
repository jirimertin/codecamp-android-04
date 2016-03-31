package cz.ackee.codecamp04.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import cz.ackee.codecamp04.R;

/**
 * Fragment with view pager and tab layout
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {31.3.16}
 **/
public class MenuActionsFragment extends Fragment {
    public static final String TAG = MenuActionsFragment.class.getName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_menu_actions, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_menu_example, menu);
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_share:
                Snackbar.make(getView(), R.string.menu_actions_share_clicked, Snackbar.LENGTH_LONG).show();
                break;
            case R.id.action_check:
                Snackbar.make(getView(), R.string.menu_actions_check_clicked, Snackbar.LENGTH_LONG).show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
