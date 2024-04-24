package org.example.taskforpractive.repository;

import org.example.taskforpractive.model.RequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<RequestModel, Long> {
}
