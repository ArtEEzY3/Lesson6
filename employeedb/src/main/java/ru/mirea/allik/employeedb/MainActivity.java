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
        binding.findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.idEditText.getText().length() != 0) {
                    Employee employee;
                    employee = employeeDao.getById(Long.parseLong(String.valueOf(binding.idEditText.getText())));
                    binding.nameEdit.setText(employee.getName());
                    binding.salaryEdit.setText(employee.getSalary());
                } else {
                    Toast.makeText(getApplicationContext(), "Введите id", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.idEditText.getText().length() != 0 & binding.nameEdit.getText().length() != 0 & binding.salaryEdit.getText().length() != 0) {
                    try {
                        Employee employee = new Employee();
                        employee.id = Long.parseLong(String.valueOf(binding.idEditText.getText()));
                        employee.name = String.valueOf(binding.nameEdit.getText());
                        employee.salary = Integer.parseInt(String.valueOf(binding.salaryEdit.getText()));
                        employeeDao.insert(employee);
                    }
                    catch (SQLiteConstraintException e) {
                        Toast.makeText(getApplicationContext(), "id уже используется", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Вы не заполнили все поля", Toast.LENGTH_SHORT).show();
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