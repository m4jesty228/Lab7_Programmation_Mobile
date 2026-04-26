package com.example.starsgallery.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ShareCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.starsgallery.R;
import com.example.starsgallery.adapter.StarAdapter;
import com.example.starsgallery.service.StarService;
/**
 * Classe représentant une Star avec ses informations principales.
 *
 * @author DOSSAH Landry
 * @version 1.0
 */
public class ListActivity extends AppCompatActivity {

    private static final String TAG = "ListActivity";
    private RecyclerView celebrityRecyclerView;
    private StarAdapter starAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        celebrityRecyclerView = findViewById(R.id.recycle_view);
        celebrityRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Liaison de l'adapter avec les données du service
        starAdapter = new StarAdapter(this, StarService.getInstance().findAll());
        celebrityRecyclerView.setAdapter(starAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setQueryHint("Rechercher une star...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, "Recherche en cours : " + newText);
                // Déclenche le filtrage dans l'adapter
                if (starAdapter != null) {
                    starAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.share) {
            // Contenu et type du message à partager
            String shareMessage = "Découvrez l'application Stars Gallery !";
            String contentType = "text/plain";

            // Ouverture du sélecteur d'applications de partage
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(contentType)
                    .setChooserTitle("Partager via...")
                    .setText(shareMessage)
                    .startChooser();
        }
        return super.onOptionsItemSelected(item);
    }
}