package cz.ackee.codecamp04.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cz.ackee.codecamp04.R;
import cz.ackee.codecamp04.ui.activity.DetailActivity;

/**
 * Fragment with view pager and tab layout
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {31.3.16}
 **/
public class DetailFragment extends Fragment {
    public static final String TAG = DetailFragment.class.getName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnShowDetail = (Button) view.findViewById(R.id.btn_show_detail);
        btnShowDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                startActivity(intent);
            }
        });
    }
}
