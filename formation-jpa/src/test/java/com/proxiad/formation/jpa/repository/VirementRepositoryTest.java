package com.proxiad.formation.jpa.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.proxiad.formation.jpa.springdatarepository.VirementRepository;

@DatabaseSetup("classpath:data/transaction.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:data/transaction.xml")
public class VirementRepositoryTest extends AbstractRepositoryTest {

	@Autowired
	private VirementRepository virementRepository;
	
	@Test
	public void testFindAll() {
		assertEquals(2, virementRepository.findAll().size());
	}
	
}
