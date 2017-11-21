package com.proxiad.formation.jpa.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.proxiad.formation.jpa.model.Retrait;
import com.proxiad.formation.jpa.springdatarepository.RetraitRepository;

@DatabaseSetup("classpath:data/transaction.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:data/transaction.xml")
public class RetraitRepositoryTest extends AbstractRepositoryTest {

	@Autowired
	private RetraitRepository retraitRepository;
	
	@Test
	public void testFindAll() {
		assertEquals(1, retraitRepository.findAll().size());
	}
	
	@Test
	public void testFindOne() {
		assertTrue(retraitRepository.findOne("1") instanceof Retrait);
		assertNull(retraitRepository.findOne("2"));
	}
	
}
