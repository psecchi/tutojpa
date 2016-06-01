package com.proxiad.formation.jpa.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.proxiad.formation.jpa.model.Client;

@DatabaseSetup("classpath:data/client.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:data/client.xml")
public class ClientRepositoryTest extends AbstractRepositoryTest {

	@Autowired
	private ClientRepository clientRepository;

	@Test
	public void testFindAll() {
		List<Client> clients = clientRepository.findAll();
		assertEquals(1, clients.size());
		assertEquals("Zidane", clients.get(0).getNom());
	}

	@Test
	public void testFind() {
		Client client = clientRepository.find("1");
		assertEquals("Zidane", client.getNom());
	}

	@Test
	public void testSave() {
		int initialSize = clientRepository.findAll().size();
		Client client = new Client();
		client.setNumero("2");
		client.setNom("Dupont");
		client.setPrenom("Martin");

		clientRepository.create(client);

		assertEquals(initialSize + 1, clientRepository.findAll().size());
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void testSaveEchoueCarClientExisteDeja() {
		Client client = new Client();
		client.setNumero("1"); // le client 1 est deja en base => le persist va
								// planter : violation de la PK
		client.setNom("Dupont");
		client.setPrenom("Martin");

		clientRepository.create(client);
	}

	@Test
	public void testUpdate() {
		Client client = clientRepository.find("1");
		client.setNom("NomMAJ");

		clientRepository.update(client);

		assertEquals("NomMAJ", clientRepository.find("1").getNom());
		assertEquals("Zinedine", clientRepository.find("1").getPrenom());
	}

	@Test
	public void testDelete() {
		int initialSize = clientRepository.findAll().size();

		Client client = clientRepository.find("1");

		clientRepository.delete(client);

		assertEquals(initialSize - 1, clientRepository.findAll().size());
	}
}
