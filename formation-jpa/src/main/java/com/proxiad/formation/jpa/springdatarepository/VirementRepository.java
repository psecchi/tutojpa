package com.proxiad.formation.jpa.springdatarepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proxiad.formation.jpa.model.Virement;

public interface VirementRepository extends JpaRepository<Virement, String> {

}
