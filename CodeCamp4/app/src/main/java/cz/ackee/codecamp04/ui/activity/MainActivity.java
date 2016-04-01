package cz.ackee.codecamp04.ui.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import cz.ackee.codecamp04.R;
import cz.ackee.codecamp04.ui.fragment.DetailFragment;
import cz.ackee.codecamp04.ui.fragment.HomeworkFragment;
import cz.ackee.codecamp04.ui.fragment.HomeworkV2Fragment;
import cz.ackee.codecamp04.ui.fragment.MenuActionsFragment;
import cz.ackee.codecamp04.ui.fragment.ViewPagerFragment;

/**
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {31.3.16}
 */
public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupToolbar();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideIme();
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        if (savedInstanceState == null) {
            createFragmentForActionId(R.id.nav_homework);
        }
    }

    private void hideIme() {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void setupToolbar() {
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowTitleEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void setupDrawerContent(NavigationView navigationView) {

        // Listener na vybirani menu itemu
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);

                        createFragmentForActionId(menuItem.getItemId());
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    private void createFragmentForActionId(int menuItem) {
        Fragment f = null;
        switch (menuItem) {
            case R.id.nav_homework:
                f = new HomeworkFragment();
                break;
            case R.id.nav_view_pager:
                f = new ViewPagerFragment();
                break;
            case R.id.nav_menu_actions:
                f = new MenuActionsFragment();
                break;
            case R.id.nav_detail:
                f = new DetailFragment();
                break;
            case R.id.nav_homework_v2:
                f = new HomeworkV2Fragment();
                break;
        }

        if (f != null) {

            removeOldFragments();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, f, f.getClass().getName())
                    .commit();
        }
    }

    private void removeOldFragments() {
        for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); i++) {
            getSupportFragmentManager().popBackStack();
        }
    }
}
