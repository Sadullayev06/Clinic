package uz.isystem.Clinika.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.isystem.Clinika.model.Visit;

public interface VisitRepository extends JpaRepository<Visit,Integer> {
}
