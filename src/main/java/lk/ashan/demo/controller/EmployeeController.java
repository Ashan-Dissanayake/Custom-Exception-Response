package lk.ashan.demo.controller;

import lk.ashan.demo.dao.EmployeeDao;
import lk.ashan.demo.entity.Employee;
import lk.ashan.demo.exception.customexception.ResourceExistsException;
import lk.ashan.demo.exception.customexception.ResourceNotFoundException;
import lk.ashan.demo.exception.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {


    @Autowired private EmployeeDao employeedao;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Employee>> get() {

        List<Employee> employees = this.employeedao.findAll();

        if (!employees.isEmpty()) return new ResponseEntity<>(employees,HttpStatus.OK);
        else throw  new ResourceNotFoundException("Employees Not Available");
    }

    @PostMapping
    public ResponseEntity<Response> add(@RequestBody Employee employee) {

        if (employeedao.findByNic(employee.getNic()) != null)
            throw new ResourceExistsException("Employee is Already Exists with this NIC:"+employee.getNic());

        employeedao.save(employee);

        return new ResponseEntity<>(new Response( HttpStatus.CREATED.value(),"Employee Created successfully"),HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<Response> update(@RequestBody Employee employee){

        Employee emp2 = employeedao.findByNic(employee.getNic());

        if(emp2!=null && !Objects.equals(employee.getId(), emp2.getId()))
            throw new ResourceExistsException("Employee is Already Exists with this NIC:"+employee.getNic());

        employeedao.save(employee);

        return new ResponseEntity<>(new Response( HttpStatus.CREATED.value(),"Employee Updated successfully"),HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Integer id){

        Employee emp1 = employeedao.findByMyId(id);

        if(emp1==null)throw  new ResourceNotFoundException("Employee Doesn't Exists :"+id);

        employeedao.delete(emp1);

        return new ResponseEntity<>(new Response( HttpStatus.OK.value(),"Employee Deleted successfully"),HttpStatus.OK);
    }




//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public HashMap<String, String> add(@RequestBody Employee employee) {
//
//        HashMap<String, String> responce = new HashMap<>();
//        String errors = "";
//
//        if (employeedao.findByNic(employee.getNic()) != null)
//            errors = errors + "<br> Existing NIC";
//
//        if (errors.isEmpty())
//            employeedao.save(employee);
//        else errors = "Server Validation Errors : <br> " + errors;
//
//        responce.put("id", String.valueOf(employee.getId()));
//        responce.put("url", "/employees/" + employee.getId());
//        responce.put("errors", errors);
//
//        return responce;
//
//    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public HashMap<String, String> add(@RequestBody Employee employee) {
//
//        HashMap<String, String> responce = new HashMap<>();
//        String errors = "";
//
//        if (employeedao.findByNic(employee.getNic()) != null)
//            throw new ResourceExistsException("Employee is Already Exists with this NIC:"+employee.getNic());
//
//        employeedao.save(employee);
//
//        responce.put("id", String.valueOf(employee.getId()));
//        responce.put("url", "/employees/" + employee.getId());
//        responce.put("errors", errors);
//
//        return responce;
//
//    }

//    @DeleteMapping("/{id}")
//    public HashMap<String, String> delete(@PathVariable Integer id){
//
//        HashMap<String,String> response  = new HashMap<>();
//
//        String errors="";
//
//        Employee emp1 = employeedao.findByMyId(id);
//
//        if(emp1==null) errors = errors+"<br> Employee Does Not Existed";
//
//        if(errors.isEmpty()) employeedao.delete(emp1);
//        else errors = "Server Validation Errors : <br> "+errors;
//
//        response .put("id",String.valueOf(id));
//        response .put("url","/employees/"+id);
//        response .put("errors",errors);
//
//        return response ;
//    }



}
