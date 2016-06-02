package org.jefrienalvizures.clashtournament;

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

import org.jefrienalvizures.clashtournament.Dialogs.crearClanDialog;
import org.jefrienalvizures.clashtournament.bean.Clan;
import org.jefrienalvizures.clashtournament.clases.Clanes;
import org.jefrienalvizures.clashtournament.fragments.*;

import org.jefrienalvizures.clashtournament.bean.Usuario;
import org.jefrienalvizures.clashtournament.clases.Comunicador;

public class Inicio extends AppCompatActivity implements crearClanDialog.OnClanCreatedListener, InicioFragment.OnButtonClickListenerInicio {

    ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Usuario u = Comunicador.getUsuario();

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
        }
    }

    @Override
    public void onClanCreatedListener(String nombreClan) {
        if(nombreClan!=null){
            Log.e("NOMBRE CLAN",nombreClan);
            //Clan clan = new Clanes().getClanByName(nombreClan,this);
            //Log.e("NOMBRE CLAN OBTENIDO",clan.getNombreClan().toString());
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
