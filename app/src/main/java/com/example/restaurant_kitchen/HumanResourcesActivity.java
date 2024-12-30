package com.example.restaurant_kitchen;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class HumanResourcesActivity extends AppCompatActivity {

    private EditText employeeNameInput, shiftInput;
    private Button addEmployeeButton, viewEmployeesButton, generateShiftReportButton;
    private TextView employeesDisplay;

    private EmployeesDatabase employeesDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_human_resources);

        // Connect UI Components
        employeeNameInput = findViewById(R.id.employeeNameInput);
        shiftInput = findViewById(R.id.shiftInput);
        addEmployeeButton = findViewById(R.id.addEmployeeButton);
        viewEmployeesButton = findViewById(R.id.viewEmployeesButton);
        generateShiftReportButton = findViewById(R.id.generateShiftReportButton);
        employeesDisplay = findViewById(R.id.employeesDisplay);

        employeesDatabase = new EmployeesDatabase(this);

        // Add Employee
        addEmployeeButton.setOnClickListener(v -> {
            String name = employeeNameInput.getText().toString();
            String shift = shiftInput.getText().toString();

            employeesDatabase.addEmployee(name, shift);
            Toast.makeText(this, "Nhân viên đã được thêm!", Toast.LENGTH_SHORT).show();
        });

        // View Employee List
        viewEmployeesButton.setOnClickListener(v -> {
            List<String> employees = employeesDatabase.getAllEmployees();
            employeesDisplay.setText("");
            for (String employee : employees) {
                employeesDisplay.append(employee + "\n");
            }
        });

        // Generate Shift Report
        generateShiftReportButton.setOnClickListener(v -> {
            String shift = shiftInput.getText().toString();
            List<String> shiftReport = employeesDatabase.getEmployeesByShift(shift);
            employeesDisplay.setText("Báo cáo ca làm việc (" + shift + "):\n");
            for (String record : shiftReport) {
                employeesDisplay.append(record + "\n");
            }
        });
    }
}
