package ru.mirea.allik.lesson6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import ru.mirea.allik.lesson6.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sharedPref = getSharedPreferences("settings-allik",	Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("GROUP", String.valueOf(binding.groupNumEditText.getText()));
                editor.putInt("NUMBER", Integer.parseInt(String.valueOf(binding.studNumEditText.getText())));
                editor.putString("MOVIE", String.valueOf(binding.favMovieEditText.getText()));
                editor.apply();
            }
        });

//        editor.putString("GROUP", String.valueOf(binding.groupNumEditText.getText()));
//        editor.putInt("NUMBER", Integer.parseInt(String.valueOf(binding.studNumEditText.getText())));
//        editor.putString("MOVIE", String.valueOf(binding.favMovieEditText.getText()));
//        editor.apply();

//        String group = sharedPref.getString("GROUP", "unknown");
//        Integer num = sharedPref.getInt("NUMBER", 0);
//        String movie = sharedPref.getString("MOVIE", "no movie :(");
//        binding.groupNumEditText.setText(group);
//        binding.studNumEditText.setText(String.valueOf(num));
//        binding.favMovieEditText.setText(movie);
        binding.groupNumEditText.setText(sharedPref.getString("GROUP", "unknown"));
        binding.studNumEditText.setText(String.valueOf(sharedPref.getInt("NUMBER", 1)));
        binding.favMovieEditText.setText(sharedPref.getString("MOVIE", "no movie :("));
    }
}