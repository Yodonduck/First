package com.dmora.first;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

public class Main extends AppCompatActivity {

    private SwipeRefreshLayout swipeLayout;
    WebView myContext;
    ImageView avocado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        avocado = findViewById(R.id.avocado);

        swipeLayout = findViewById(R.id.myswipe);
        swipeLayout.setOnRefreshListener(mOnRefreshListener);

        myContext = findViewById(R.id.view);


        myContext.getSettings().setBuiltInZoomControls(true);
        myContext.loadUrl("https://thispersondoesnotexist.com");
        registerForContextMenu(myContext);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context, menu);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.cookie) {
            Toast toast = Toast.makeText(this,"Toma una galletita",Toast.LENGTH_LONG );
            toast.show();
        }
        if (id == R.id.settings) {
            openMainBAB();
        }
        if (id == R.id.log_out) {
            Main.super.onBackPressed();
        }
        if (id == R.id.close) {
            System.exit(0);
        }
        if (id == R.id.alert) {
            showAlertDialogButtonClicked(Main.this);
        }
        return super.onOptionsItemSelected(item);
    }

    private void openMainBAB() {
        Intent intent = new Intent(this, MainBAB.class);
        startActivity(intent);
    }

    private void showAlertDialogButtonClicked(Main main) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);

        builder.setTitle("ALERTA");
        builder.setMessage("No olvides el aguacate");
        builder.setIcon(R.drawable.avocado_del_diablo);

        builder.setPositiveButton("DAME AGUACATE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do something like...
                animatedAvocado();
                hideAvocado();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("No quiero aguacate", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast toast = Toast.makeText(Main.this,"Tú te lo pierdes",Toast.LENGTH_LONG );
                toast.show();
                dialog.dismiss();
            }
        });

        builder.setNeutralButton("Cerrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void hideAvocado() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                avocado.setVisibility(INVISIBLE);
            }
        }, 4000);
    }

    private void animatedAvocado() {
        Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.zoomin_rotate);
        avocado = findViewById(R.id.avocado);
        avocado.setVisibility(VISIBLE);
        avocado.startAnimation(myAnim);
    }

    protected SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            Toast toast = Toast.makeText(Main.this, "Hi there, I don't exist", Toast.LENGTH_LONG);
            toast.show();
            myContext.reload();
            swipeLayout.setRefreshing(false);
        }
    };

    @Override
    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.item1:
                final ConstraintLayout mLayout = findViewById(R.id.myMainConstraint);
                Snackbar snackbar = Snackbar
                        .make(mLayout, "fancy a Snack while you refresh?", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Snackbar snackbar1 = Snackbar.make(mLayout, "Action is restored!", Snackbar.LENGTH_LONG);
                                snackbar1.show();
                            }
                        });
                snackbar.show();
                return true;
                /*
                Toast toast = Toast.makeText(this, "Item copied", Toast.LENGTH_LONG);
                toast.show();
                return true;
                 */
            case R.id.item2:
                Toast toast2 = Toast.makeText(this, "Downloading...", Toast.LENGTH_LONG);
                toast2.show();
                return true;
            default:
                // return super.onContextItemSelected(item);
                return false;
        }
    }
}