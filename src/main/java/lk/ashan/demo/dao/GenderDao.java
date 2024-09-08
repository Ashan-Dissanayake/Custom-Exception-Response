package lk.ashan.demo.dao;

import lk.ashan.demo.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderDao extends JpaRepository<Gender,Integer> {
}
