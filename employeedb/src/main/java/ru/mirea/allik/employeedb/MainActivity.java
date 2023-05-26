package ru.mirea.allik.employeedb;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import ru.mirea.allik.employeedb.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppDatabase db = App.getInstance().getDatabase();
        EmployeeDao employeeDao = db.employeeDao();

        binding.updBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.idEditText.getText().length() != 0) {
                    if (binding.nameEdit.getText().length() != 0 &
                            binding.superEdit.getText().length() != 0 & binding.nemesisEdit.getText().length() != 0) {
                        try {
                            Employee employee = new Employee();
                            employee.id = Long.parseLong(String.valueOf(binding.idEditText.getText()));
                            employee.name = String.valueOf(binding.nameEdit.getText());
                            employee.superpower = String.valueOf(binding.superEdit.getText());
                            employee.nemesis = String.valueOf(binding.nemesisEdit.getText());
                            employeeDao.update(employee);
                        }
                        catch (SQLiteConstraintException e) { //shouldnt work
                            Toast.makeText(getApplicationContext(), "id уже используется", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "All fields must be filled", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    Toast.makeText(getApplicationContext(), "Set id for update", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.idEditText.getText().length() != 0) {
                    Employee employee;
                    employee = employeeDao.getById(Long.parseLong(String.valueOf(binding.idEditText.getText())));
                    binding.nameEdit.setText(employee.getName());
                    binding.superEdit.setText(employee.getSuperpower());
                    binding.nemesisEdit.setText(employee.getNemesis());
                }
            }
        });
        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.idEditText.getText().length() != 0 & binding.nameEdit.getText().length() != 0 &
                        binding.superEdit.getText().length() != 0 & binding.nemesisEdit.getText().length() != 0) {
                    try {
                        Employee employee = new Employee();
                        employee.id = Long.parseLong(String.valueOf(binding.idEditText.getText()));
                        employee.name = String.valueOf(binding.nameEdit.getText());
                        employee.superpower = String.valueOf(binding.superEdit.getText());
                        employee.nemesis = String.valueOf(binding.nemesisEdit.getText());
                        employeeDao.insert(employee);
                    }
                    catch (SQLiteConstraintException e) {
                        Toast.makeText(getApplicationContext(), "id already in use", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "All fields must be filled", Toast.LENGTH_SHORT).show();
                }
            }
        });



//        AppDatabase db = App.getInstance().getDatabase();
//        EmployeeDao employeeDao = db.employeeDao();
//        Employee employee = new Employee();
//        employee.id = 1;
//        employee.name = "John Smith";
//        employee.salary = 10000;
//        // запись сотрудников в базу
//        employeeDao.insert(employee);
//        // Загрузка всех работников
//        List<Employee> employees = employeeDao.getAll();
//        // Получение определенного работника с id = 1
//        employee = employeeDao.getById(1);
//        // Обновление полей объекта
//        employee.salary = 20000;
//        employeeDao.update(employee);
//        Log.d(TAG, employee.name + " " + employee.salary);
    }
}