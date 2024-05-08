package org.example.kafkatest.repositories;

import org.example.kafkatest.entities.ReviewPostgres;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepositoryPostGres extends JpaRepository<ReviewPostgres, Integer> {
}
