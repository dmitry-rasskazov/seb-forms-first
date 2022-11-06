package rasskazov.laba.laba6.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rasskazov.laba.laba6.Entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
