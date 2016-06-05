package org.jefrienalvizures.clashtournament;

import android.content.Context;
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

import org.jefrienalvizures.clashtournament.Dialogs.crearClanDialog;
import org.jefrienalvizures.clashtournament.bean.Usuario;
import org.jefrienalvizures.clashtournament.db.usuarioDB;
import org.jefrienalvizures.clashtournament.fragments.Inicio1Fragment;
import org.jefrienalvizures.clashtournament.fragments.InicioFragment;
import org.jefrienalvizures.clashtournament.fragments.perfilFragment;

public class Inicio1 extends AppCompatActivity implements crearClanDialog.OnClanCreatedListener, InicioFragment.OnButtonClickListenerInicio{

    ViewPager viewPager;
    TabLayout tabLayout;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        this.context = this;


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
    public void onClanCreatedListener(int accion) {

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
                    Inicio1Fragment tab1 = new Inicio1Fragment(context);
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
