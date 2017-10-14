package com.saurett.cjm;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.saurett.cjm.data.model.AretesInternos;
import com.saurett.cjm.data.model.Cabezas;
import com.saurett.cjm.fragments.interfaces.MainRegisterInterface;
import com.saurett.cjm.helpers.DecodeExtraHelper;
import com.saurett.cjm.utils.Constants;
import com.saurett.cjm.utils.DateTimeUtils;

/**
 * Created by jvier on 04/09/2017.
 */

public class MainRegisterActivity extends AppCompatActivity implements MainRegisterInterface {

    private DecodeExtraHelper _MAIN_DECODE;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main_register);
        setSupportActionBar(toolbar);

        _MAIN_DECODE = (DecodeExtraHelper) getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        this.onPreRender();
    }

    @Override
    protected void onStart() {
        super.onStart();

        getSupportActionBar().setTitle(_MAIN_DECODE.getTituloActividad());
        getSupportActionBar().setSubtitle(_MAIN_DECODE.getTituloFormulario());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void onPreRender() {
        openFragment(_MAIN_DECODE.getFragmentTag());
    }

    /**
     * Abre el fragmento mediante el tag seleccionado
     **/
    private void openFragment(String tag) {
        FragmentTransaction mainFragment = getSupportFragmentManager().beginTransaction();
        mainFragment.replace(R.id.fragment_main_register_container, Constants.TAG_FRAGMENT.get(tag), tag);
        mainFragment.addToBackStack(tag);
        mainFragment.commit();
    }

    @Override
    public void registrarAretesInternos(AretesInternos aretesInternos) {
        pDialog = new ProgressDialog(MainRegisterActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        firebaseRegistroAretesInternos(aretesInternos);
    }

    @Override
    public void editarAretesInternos(AretesInternos aretesInternos) {
        pDialog = new ProgressDialog(MainRegisterActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        this.firebaseUpdateArteesInternos(aretesInternos);
    }

    @Override
    public void registrarCabezas(Cabezas cabezas) {
        pDialog = new ProgressDialog(MainRegisterActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        this.firebaseRegistroCabezas(cabezas);
    }

    @Override
    public void editarCabezas(Cabezas cabezas) {
        pDialog = new ProgressDialog(MainRegisterActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        this.firebaseUpdateCabezas(cabezas);
    }

    private void firebaseUpdateArteesInternos(AretesInternos aretesInternos) {
        DatabaseReference dbAreteInterno =
                FirebaseDatabase.getInstance().getReference()
                        .child(Constants.FB_KEY_MAIN_ARETES_INTERNOS)
                        .child(aretesInternos.getFirebaseId());

        aretesInternos.setFechaDeEdicion(DateTimeUtils.getTimeStamp());

        dbAreteInterno.setValue(aretesInternos, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                pDialog.dismiss();

                if (databaseError == null) {
                    finish();
                    Toast.makeText(getApplicationContext(),
                            "Actualizado correctamente...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void firebaseRegistroAretesInternos(AretesInternos aretesInternos) {
        /**obtiene la instancia como transportista**/
        final DatabaseReference dbAretesInternos =
                FirebaseDatabase.getInstance().getReference()
                        .child(Constants.FB_KEY_MAIN_ARETES_INTERNOS);

        String key = dbAretesInternos.push().getKey();

        aretesInternos.setFirebaseId(key);
        aretesInternos.setFechaDeCreacion(DateTimeUtils.getTimeStamp());
        aretesInternos.setFechaDeEdicion(DateTimeUtils.getTimeStamp());

        dbAretesInternos.child(aretesInternos.getFirebaseId())
                .setValue(aretesInternos, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                        pDialog.dismiss();
                        if (databaseError == null) {
                            finish();
                            Toast.makeText(getApplicationContext(),
                                    "Registrado correctamente...", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void firebaseUpdateCabezas(Cabezas cabezas) {
        DatabaseReference dbAreteInterno =
                FirebaseDatabase.getInstance().getReference()
                        .child(Constants.FB_KEY_MAIN_CABEZAS)
                        .child(cabezas.getFirebaseId());

        cabezas.setFechaDeEdicion(DateTimeUtils.getTimeStamp());

        dbAreteInterno.setValue(cabezas, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                pDialog.dismiss();

                if (databaseError == null) {
                    finish();
                    Toast.makeText(getApplicationContext(),
                            "Actualizado correctamente...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void firebaseRegistroCabezas(Cabezas cabezas) {

        /**obtiene la instancia como transportista**/
        final DatabaseReference dbAretesInternos =
                FirebaseDatabase.getInstance().getReference()
                        .child(Constants.FB_KEY_MAIN_CABEZAS);

        String key = dbAretesInternos.push().getKey();

        cabezas.setFirebaseId(key);
        cabezas.setFechaDeCreacion(DateTimeUtils.getTimeStamp());
        cabezas.setFechaDeEdicion(DateTimeUtils.getTimeStamp());

        dbAretesInternos.child(cabezas.getFirebaseId())
                .setValue(cabezas, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                        pDialog.dismiss();
                        if (databaseError == null) {
                            finish();
                            Toast.makeText(getApplicationContext(),
                                    "Registrado correctamente...", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
