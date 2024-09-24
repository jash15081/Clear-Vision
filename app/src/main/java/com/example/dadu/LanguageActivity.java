package com.example.dadu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private Button submitButton;
    private ImageButton cancelButton;
    private static final String PREFS_NAME = "LanguagePrefs";
    private static final String PREF_LANGUAGE = "SelectedLanguage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the saved language from SharedPreferences and apply it
        loadLocale();

        setContentView(R.layout.activity_language);

        // Set up the toolbar with a back button
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//           back button
//
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }

        radioGroup = findViewById(R.id.radioGroup);
        submitButton = findViewById(R.id.submit_button);
        cancelButton = findViewById(R.id.cancel_button);

        cancelButton.setOnClickListener(v -> finish());

        submitButton.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = findViewById(selectedId);

            if (selectedRadioButton != null) {
                String selectedLanguage = selectedRadioButton.getTag().toString(); // Using tag instead of text

                // Determine language code based on selected language tag
                switch (selectedLanguage) {
                    case "hi":
                        setLocale("hi");
                        break;
                    case "fr":
                        setLocale("fr");
                        break;
                    default:
                        setLocale("en"); // Default to English
                        break;
                }

                // Provide feedback to the user
                Toast.makeText(LanguageActivity.this, "Language changed to: " + selectedLanguage, Toast.LENGTH_SHORT).show();

                // Restart the main activity to apply the new language
                Intent intent = new Intent(LanguageActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); // Clear stack and start new task
                startActivity(intent);
                finish(); // Finish current activity
            }
        });
    }

    // Method to change the app's locale and save it to SharedPreferences
    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // Save language to shared preferences
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString(PREF_LANGUAGE, lang);
        editor.apply();
    }

    // Load the saved language from SharedPreferences
    private void loadLocale() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String language = prefs.getString(PREF_LANGUAGE, "en"); // Default to English
        setLocale(language);
    }

    // Handle back button click in the toolbar
    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
