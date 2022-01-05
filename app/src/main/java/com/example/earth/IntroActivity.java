package com.example.earth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.earth.fragments.ChartFragment;
import com.example.earth.fragments.HomeFragment;
import com.example.earth.fragments.WalletFragment;
import com.google.android.material.navigation.NavigationView;

public class IntroActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    MeowBottomNavigation btn_navigation;
    NavigationView navigationView;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


       /* getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        setContentView(R.layout.activity_intro);

        final DrawerLayout drawerLayout = findViewById(R.id.drawerlayout);

        findViewById(R.id.btn_navigation_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btn_navigation = findViewById(R.id.btn_navigation);

        navigationView = findViewById(R.id.navigation_menu);

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        btn_navigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_balance_wallet));
        btn_navigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_home));
        btn_navigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_bar_chart));


        btn_navigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                Fragment fragment = null;

                 switch (item.getId()) {

                     case 1:
                         fragment = new WalletFragment();
                         break;
                     case 2:
                         fragment = new HomeFragment();
                         break;
                     case 3:
                         fragment = new ChartFragment();
                         break;

                 }
                loadFragment(fragment);
            }

        });

        btn_navigation.show(2,true);

        btn_navigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                // Toast.makeText(getApplicationContext(),"vous avez clickez" + item.getId(),Toast.LENGTH_LONG).show();
            }
        });

        btn_navigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

                // Toast.makeText(getApplicationContext(),"reset" + item.getId(),Toast.LENGTH_LONG).show();

            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.appbarmenu,menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("recherche");
        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(IntroActivity.this,query,Toast.LENGTH_SHORT).show();
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);

        return super.onCreateOptionsMenu(menu);
    }




    public void loadFragment(Fragment fragment){

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}