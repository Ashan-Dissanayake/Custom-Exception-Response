package lk.ashan.demo.controller;

import lk.ashan.demo.dao.EmployeeDao;
import lk.ashan.demo.exception.ResourceExistsException;
import lk.ashan.demo.exception.ResourceNotFoundException;
import lk.ashan.demo.model.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired private EmployeeDao employeedao;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeedao.findAll();

        if (!employees.isEmpty()) return ResponseEntity.ok(employees);
        else throw new ResourceNotFoundException("Employees Not Available");

    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        if (employeedao.findByNic(employee.getNic()) != null)
            throw new ResourceExistsException("Employee already exists with this NIC: " + employee.getNic());

        Employee savedEmployee = employeedao.save(employee);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee existingEmployee = employeedao.findByNic(employee.getNic());

        if (existingEmployee != null && !Objects.equals(employee.getId(), existingEmployee.getId()))
            throw new ResourceExistsException("Employee already exists with this NIC: " + employee.getNic());

        Employee updatedEmployee = employeedao.save(employee);

        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        Employee employee = employeedao.findByMyId(id);

        if (employee == null) throw new ResourceNotFoundException("Employee doesn't exist: " + id);

        employeedao.delete(employee);

        return ResponseEntity.noContent().build();
    }
}
