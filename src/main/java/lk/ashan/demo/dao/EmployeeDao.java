package lk.ashan.demo.dao;

import lk.ashan.demo.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface EmployeeDao extends JpaRepository<Employee,Integer> {

    Employee findByNic(String nic);

    @Query("select e from Employee e where e.id = :id")
    Employee findByMyId(@Param("id") Integer id);

}
