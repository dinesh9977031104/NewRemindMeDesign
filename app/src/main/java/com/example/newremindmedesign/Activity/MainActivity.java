package com.example.newremindmedesign.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.Menu;
import android.view.MenuItem;

import com.example.newremindmedesign.Adapter.ViewPagerAdapter;
import com.example.newremindmedesign.Fragment.HomeFragment;
import com.example.newremindmedesign.Fragment.OfficeFragment;
import com.example.newremindmedesign.Fragment.OtherFragment;
import com.example.newremindmedesign.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.list_view)
    RecyclerView listView;

    private ActionBar actionBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DrawerLayout drawer;

    public MainActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Remind Me");

        bottomNavigationDrawar();

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        Menu menu = navigationView.getMenu();
        MenuItem tools = menu.findItem(R.id.nav_general_setting);
        SpannableString s = new SpannableString(tools.getTitle());
        s.setSpan(new TextAppearanceSpan(this, R.color.colorAccent), 0, s.length(), 0);
        tools.setTitle(s);


        Menu menu2 = navigationView.getMenu();
        MenuItem tools2 = menu2.findItem(R.id.nav_communicate);
        SpannableString s2 = new SpannableString(tools2.getTitle());
        s2.setSpan(new TextAppearanceSpan(this, R.color.colorAccent), 0, s2.length(), 0);
        tools2.setTitle(s2);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new HomeFragment(), getResources().getString(R.string.fragment_home));
        adapter.AddFragment(new OfficeFragment(), getResources().getString(R.string.fragment_office));
        adapter.AddFragment(new OtherFragment(), getResources().getString(R.string.fragment_other));

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.nav_home:
                break;
            case R.id.nav_favorite:
                break;
            case R.id.nav_setting:
                break;
            case R.id.nav_share:
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private void bottomNavigationDrawar() {

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Intent intent = null;
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                intent = new Intent(MainActivity.this, ActivityAddReminder.class);
                                startActivity(intent);
                                break;
                            case R.id.action_item2:

                                break;
                            case R.id.action_item3:

                                break;
                        }
                        return true;
                    }
                });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
           /* case R.id.search:
                break;*/
        }
        return super.onOptionsItemSelected(item);
    }
}
