package ru.mirea.allik.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import kotlin.text.Charsets;
import ru.mirea.allik.notebook.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isExternalStorageWritable()) {
                    writeFileToExternalStorage();
                }
            }
        });
        binding.loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isExternalStorageReadable()) readFileFromExternalStorage();
            }
        });
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
    /* Проверяем внешнее хранилище на доступность чтения */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public void writeFileToExternalStorage()	{
        File path =	Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file =	new	File(path,	binding.nameEditText.getText() + ".txt");
        try	{
            FileOutputStream fileOutputStream =	new	FileOutputStream(file.getAbsoluteFile());
            OutputStreamWriter output =	new	OutputStreamWriter(fileOutputStream);
            //	Запись строки в файл
            output.write(String.valueOf(binding.quoteEditText.getText()));
            //	Закрытие потока записи
            output.close();

        }	catch	(IOException e)	{
            Log.w("ExternalStorage",	"Error	writing	"	+	file,	e);
        }
    }

    public void	readFileFromExternalStorage() {
        File path =	Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file =	new	File(path,binding.nameEditText.getText() + ".txt");
        try	{
            FileInputStream fileInputStream	= new	FileInputStream(file.getAbsoluteFile());
            InputStreamReader inputStreamReader	= new	InputStreamReader(fileInputStream,	StandardCharsets.UTF_8);
//            List<String> lines = new ArrayList<String>();
            BufferedReader reader =	new BufferedReader(inputStreamReader);
            String line	= reader.readLine();
//            while (line != null) {
//                lines.add(line);
//                line = reader.readLine();
//            }
            Log.w("ExternalStorage", String.format("Read from file %s successful",	line));
            binding.quoteEditText.setText(line);
        }	catch (Exception	e) {
            Log.w("ExternalStorage", String.format("Read from file %s failed",	e.getMessage()));
        }
    }
}

