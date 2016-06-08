package com.proxiad.formation.jpa.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.proxiad.formation.jpa.model.Commande;

@DatabaseSetup("classpath:data/commande.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:data/commande.xml")
public class CommandeRepositoryTest extends AbstractRepositoryTest {

	@Autowired
	private CommandeRepository commandeRepository;

	@Test
	public void testFind() {
		Commande commande = commandeRepository.find("1");
		assertNotNull(commande);
		assertEquals(3, commande.getArticles().size());
		assertEquals(3, commande.getLignes().size());
	}
}
