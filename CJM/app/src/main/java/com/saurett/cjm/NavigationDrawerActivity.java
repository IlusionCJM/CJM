package com.saurett.cjm;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.saurett.cjm.data.model.AretesInternos;
import com.saurett.cjm.data.model.Cabezas;
import com.saurett.cjm.fragments.interfaces.NavigationDrawerInterface;
import com.saurett.cjm.helpers.DecodeExtraHelper;
import com.saurett.cjm.helpers.DecodeItemHelper;
import com.saurett.cjm.utils.Constants;
import com.saurett.cjm.utils.DateTimeUtils;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationDrawerInterface, NavigationView.OnNavigationItemSelectedListener, DialogInterface.OnClickListener {

    private static DecodeItemHelper _decodeItem;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_logout:
                closeNavigation();
                break;
            case R.id.action_soporte:
                openSoporte();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void closeNavigation() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(NavigationDrawerActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Abre la aplicación de gmail para enviar correo al equipo de soporte.
     */
    private void openSoporte() {

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        this.closeAllFragment();

        switch (id) {
            case R.id.menu_item_inicio:
                getSupportActionBar().setTitle(getString(R.string.default_item_menu_title_inicio));
                this.openFragment(Constants.ITEM_FRAGMENT.get(id));
                break;
            case R.id.menu_item_usuarios:
                getSupportActionBar().setTitle(getString(R.string.default_item_menu_title_usuarios));
                this.openFragment(Constants.ITEM_FRAGMENT.get(id));
                break;
            case R.id.menu_item_aretes_internos:
                getSupportActionBar().setTitle(getString(R.string.default_item_menu_title_aretes_internos));
                this.openFragment(Constants.ITEM_FRAGMENT.get(id));
                break;
            case R.id.menu_item_cabezas:
                getSupportActionBar().setTitle(getString(R.string.default_item_menu_title_cabezas));
                this.openFragment(Constants.ITEM_FRAGMENT.get(id));
                break;
            case R.id.menu_item_checklist:
                getSupportActionBar().setTitle(getString(R.string.default_item_menu_title_checklist));
                this.openFragment(Constants.ITEM_FRAGMENT.get(id));
                break;
            case R.id.menu_item_herrado:
                getSupportActionBar().setTitle(getString(R.string.default_item_menu_title_herrado));
                this.openFragment(Constants.ITEM_FRAGMENT.get(id));
                break;
            case R.id.menu_item_vacunado:
                getSupportActionBar().setTitle(getString(R.string.default_item_menu_title_vacunado));
                this.openFragment(Constants.ITEM_FRAGMENT.get(id));
                break;
            case R.id.menu_item_desparacitado:
                getSupportActionBar().setTitle(getString(R.string.default_item_menu_title_desparacitado));
                this.openFragment(Constants.ITEM_FRAGMENT.get(id));
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Abre el fragmento mediante el tag seleccionado
     **/
    private void openFragment(String tag) {
        try {
            FragmentTransaction mainFragment = getSupportFragmentManager().beginTransaction();
            mainFragment.replace(R.id.fragment_main_container, Constants.TAG_FRAGMENT.get(tag), tag);
            mainFragment.addToBackStack(tag);
            mainFragment.commit();
        } catch (Exception e) {
            Toast.makeText(this, "En construcción + " + tag, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Cierra todos los fragmentos actuales
     **/
    private void closeAllFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_main_container);
        if (null != fragment)
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
    }

    @Override
    public void showQuestion() {
        AlertDialog.Builder ad = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);

        ad.setTitle("Eliminar");
        ad.setMessage("¿Esta seguro que desea elminar?");
        ad.setCancelable(false);
        ad.setNegativeButton("Cancelar", this);
        ad.setPositiveButton("Aceptar", this);
        ad.show().getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(getColor(R.color.bootstrap_brand_danger));
    }

    @Override
    public void setDecodeItem(DecodeItemHelper decodeItem) {
        _decodeItem = decodeItem;
    }

    @Override
    public void openExternalActivity(int action, Class<?> externalActivity) {
        DecodeExtraHelper extraParams = new DecodeExtraHelper();

        extraParams.setTituloActividad(getString(Constants.TITLE_ACTIVITY.get(_decodeItem.getIdView())));
        extraParams.setTituloFormulario(getString(Constants.TITLE_FORM_ACTION.get(action)));
        extraParams.setAccionFragmento(action);
        extraParams.setFragmentTag(Constants.ITEM_FRAGMENT.get(_decodeItem.getIdView()));
        extraParams.setDecodeItem(_decodeItem);

        Intent intent = new Intent(this, externalActivity);
        intent.putExtra(Constants.KEY_MAIN_DECODE, extraParams);
        //intent.putExtra(Constants.KEY_SESSION_USER, _SESSION_USER);
        startActivity(intent);

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        int operation = 0;

        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                switch (_decodeItem.getIdView()) {
                    case R.id.item_btn_eliminar_arete_interno:
                        operation = Constants.WS_KEY_ELIMINAR_ARETES_INTERNOS;
                        break;
                    case R.id.item_btn_eliminar_cabeza:
                        operation = Constants.WS_KEY_ELIMINAR_CABEZAS;
                        break;
                }

                firebaseOperations(operation);
                break;
        }
    }

    /**
     * Elimina los objetos deacuerdo a la operacion
     **/
    private void firebaseOperations(int operation) {
        pDialog = new ProgressDialog(NavigationDrawerActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        switch (operation) {
            case Constants.WS_KEY_ELIMINAR_ARETES_INTERNOS:
                this.firebaseEliminarAreteInterno();
                break;
            case Constants.WS_KEY_ELIMINAR_CABEZAS:
                this.firebaseEliminarCabeza();
                break;
        }
    }

    private void firebaseEliminarCabeza() {
        final Cabezas cabeza = (Cabezas) _decodeItem.getItemModel();

        /**obtiene la instancia del elemento**/
        DatabaseReference dbCabeza =
                FirebaseDatabase.getInstance().getReference()
                        .child(Constants.FB_KEY_MAIN_CABEZAS)
                        .child(cabeza.getFirebaseId())
                        .child(Constants.FB_KEY_MAIN_ITEM_CABEZA);

        cabeza.setEstatus(Constants.FB_KEY_ITEM_ESTATUS_ELIMINADO);
        cabeza.setFechaDeEdicion(DateTimeUtils.getTimeStamp());

        dbCabeza.setValue(cabeza, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                pDialog.dismiss();
                if (databaseError == null) {
                    Toast.makeText(getApplicationContext(),
                            "Eliminado correctamente...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void firebaseEliminarAreteInterno() {
        final AretesInternos areteInterno = (AretesInternos) _decodeItem.getItemModel();

        /**obtiene la instancia del elemento**/
        DatabaseReference dbAreteInterno =
                FirebaseDatabase.getInstance().getReference()
                        .child(Constants.FB_KEY_MAIN_ARETES_INTERNOS)
                        .child(areteInterno.getFirebaseId())
                        .child(Constants.FB_KEY_MAIN_ITEM_ARETE_INTERNO);

        areteInterno.setEstatus(Constants.FB_KEY_ITEM_ESTATUS_ELIMINADO);
        areteInterno.setFechaDeEdicion(DateTimeUtils.getTimeStamp());

        dbAreteInterno.setValue(areteInterno, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                pDialog.dismiss();
                if (databaseError == null) {
                    Toast.makeText(getApplicationContext(),
                            "Eliminado correctamente...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
