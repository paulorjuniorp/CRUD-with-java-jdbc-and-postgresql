package pos_java_jdbc.pos_java_jdbc;

import java.util.List;

import org.junit.Test;

import conexaojdbc.SingleConnect;
import dao.UserposDAO;
import model.BeanUserFone;
import model.TelefoneUsuario;
import model.Userposjava;

public class TesteBancoJdbc {
	
	@Test
	public void initBanco() {
			UserposDAO userposDAO = new UserposDAO();
			Userposjava userposjava = new Userposjava();
			userposjava.setNome("Claro");
			userposjava.setEmail("dr.clarotele@gmail.com");
			
			userposDAO.salvar(userposjava);
	}
	
	@Test
	public void initListar() {
		UserposDAO userDAO = new UserposDAO();
		List<Userposjava> listaUser = userDAO.listar();
		for (Userposjava userposjava : listaUser) {
			System.out.println(userposjava);
		}
	}
	
	@Test
	public void initBuscar() {
		UserposDAO userposDAO = new UserposDAO();
		
		Userposjava userposjava = userposDAO.buscar(3L);
		
		System.out.println(userposjava);
	}
	
	@Test
	public void initAtualizar() {
		UserposDAO dao = new UserposDAO();
		
		Userposjava objetoBanco = dao.buscar(4L);
		
		objetoBanco.setNome("Grande Juinu");
		
		dao.atualizar(objetoBanco);
	}
	
	@Test
	public void initDeletar() {
		UserposDAO dao = new UserposDAO();
		
		dao.deletar(5L);
	}
	
	@Test
	public void initInsertTelefone() {
		UserposDAO dao = new UserposDAO();
		TelefoneUsuario telefoneUsuario = new TelefoneUsuario();
		telefoneUsuario.setNumero("40028922");
		telefoneUsuario.setTipo("fixo bot");
		telefoneUsuario.setUsuarioId(3L);
		dao.salvarTelefone(telefoneUsuario);
	}
	
	@Test
	public void initListarFoneUser() {
		UserposDAO dao = new UserposDAO();
		
		List<BeanUserFone> foneUser = dao.listarUserFone(3L);
		
		for (BeanUserFone beanUserFone : foneUser) {
			System.out.println(beanUserFone);
		}
	}
}
