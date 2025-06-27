package lk.ashan.demo.dao;

import lk.ashan.demo.model.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderDao extends JpaRepository<Gender,Integer> {
}
