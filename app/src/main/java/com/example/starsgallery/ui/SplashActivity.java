package com.example.starsgallery.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.starsgallery.R;
/**
 * Classe représentant une Star avec ses informations principales.
 *
 * @author DOSSAH Landry
 * @version 1.0
 */
public class SplashActivity extends AppCompatActivity {

    // ImageView du logo affiché au lancement de l'app
    private ImageView starLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        starLogo = findViewById(R.id.logo);

        // Fait tourner le logo d'un tour complet
        starLogo.animate().rotation(360f).setDuration(2000);

        // Réduit progressivement la taille du logo
        starLogo.animate().scaleX(0.5f).scaleY(0.5f).setDuration(3000);

        // Descend le logo vers le bas de l'écran
        starLogo.animate().translationYBy(1000f).setDuration(2000);

        // Efface le logo en fondu
        starLogo.animate().alpha(0f).setDuration(6000);

        // Thread de transition vers l'écran principal après 5s
        Thread splashThread = new Thread(() -> {
            try {
                Thread.sleep(5000);
                startActivity(new Intent(SplashActivity.this, ListActivity.class));
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        splashThread.start();
    }
}