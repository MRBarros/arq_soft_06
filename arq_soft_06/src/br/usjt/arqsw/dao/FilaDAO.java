package br.usjt.arqsw.dao;

/** @authora Marina Barros
 * 	816155372
 **/

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import br.usjt.arqsw.entity.Fila;
@Repository
public class FilaDAO {
	@PersistenceContext
	EntityManager manager;
	
	
	public int inserirFila(Fila fila) throws IOException {
		manager.persist(fila);
		return fila.getId();
	}
	public String excluirFila(Fila fila) throws IOException {
		String jpql = "DELETE FROM Fila c WHERE c.id = :p ";
		Query query = manager.createQuery(jpql);
		query.setParameter("p", fila.getId());
		query.executeUpdate();
		return "Removido";
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Fila> listarFilas() throws IOException {
		return manager.createQuery("select f from Fila f").getResultList();
	}

	public Fila carregar(int id) throws IOException {
		return manager.find(Fila.class, id);
	}
}
