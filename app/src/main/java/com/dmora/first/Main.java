package com.dmora.first;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class Main extends AppCompatActivity {

    private SwipeRefreshLayout swipeLayout;
    WebView myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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