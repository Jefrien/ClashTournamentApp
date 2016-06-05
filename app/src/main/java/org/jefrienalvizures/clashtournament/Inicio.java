package org.jefrienalvizures.clashtournament;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.jefrienalvizures.clashtournament.Dialogs.crearClanDialog;
import org.jefrienalvizures.clashtournament.Dialogs.unirseClanDialog;
import org.jefrienalvizures.clashtournament.bean.Clan;
import org.jefrienalvizures.clashtournament.clases.Clanes;
import org.jefrienalvizures.clashtournament.db.usuarioDB;
import org.jefrienalvizures.clashtournament.fragments.*;

import org.jefrienalvizures.clashtournament.bean.Usuario;
import org.jefrienalvizures.clashtournament.clases.Comunicador;
import org.jefrienalvizures.clashtournament.volley.WebService;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Inicio extends AppCompatActivity implements crearClanDialog.OnClanCreatedListener, InicioFragment.OnButtonClickListenerInicio, unirseClanDialog.OnJoinedClanListener {

    ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Usuario u = new usuarioDB().obtener(getBaseContext());

        setToolbar();


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("CLAN"));
        tabLayout.addTab(tabLayout.newTab().setText("PERFIL"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    /**
     * Establece la toolbar como action bar
     */
    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        //ab.setTitle(getString(R.string.app_name));
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);

        }

    }


    @Override
    public void onAccionListener(int accion) {
        switch (accion){
            case 1:
                new crearClanDialog().show(getSupportFragmentManager(),"crearClanDialog");
                break;
            case 2:
                new unirseClanDialog().show(getSupportFragmentManager(),"unirseClanDialog");
                break;
        }
    }

    @Override
    public void onClanCreatedListener(int accion) {
        switch(accion){
            case 1:
                Usuario u = Comunicador.getUsuario();
                new usuarioDB().modificar(this,u);
                startActivity(new Intent(Inicio.this,Inicio1.class));
                break;
        }

    }

    @Override
    public void onJoinedClanListener(int respuesta) {
        switch(respuesta) {
            case 1:
                Usuario u = Comunicador.getUsuario();
                u.setEstado(3);
                new usuarioDB().modificar(this,u);
                break;
        }
    }


    /**
     * Un {@link FragmentPagerAdapter} que gestiona las secciones, fragmentos y
     * títulos de las pestañas
     */
    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    InicioFragment tab1 = new InicioFragment();
                    return tab1;
                case 1:
                    perfilFragment tab2 = new perfilFragment();
                    return tab2;
               /* case 2:
                    TabFragment3 tab3 = new TabFragment3();
                    return tab3;*/
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }


}
