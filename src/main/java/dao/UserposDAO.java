package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnect;
import model.BeanUserFone;
import model.TelefoneUsuario;
import model.Userposjava;

public class UserposDAO {
	
	private Connection connection;

	public UserposDAO() {
		this.connection = SingleConnect.getConnection();
	}
	
	 public void salvar(Userposjava userposjava) {
		 try {
			 String sql = "Insert into userposjava(nome, email) values (?,?)";
			 PreparedStatement insert = connection.prepareStatement(sql);
			 insert.setString(1, userposjava.getNome());
			 insert.setString(2, userposjava.getEmail());
			 insert.execute();
			 connection.commit();
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	 
	 public void salvarTelefone(TelefoneUsuario telefoneUsuario) {
		 try {
			 String sql = "INSERT INTO telefoneuser(numero, tipo, usuario_id) VALUES (?, ?, ?);";
			 PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setString(1, telefoneUsuario.getNumero());
			 statement.setString(2, telefoneUsuario.getTipo());
			 statement.setLong(3, telefoneUsuario.getUsuarioId());
			 statement.execute();
			 connection.commit();
		 }catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	 }
	 
	 public List<Userposjava> listar(){
		 List<Userposjava> lista = new ArrayList<Userposjava>();
		 try {
		 String sql = "Select * from userposjava";
		 PreparedStatement statement = connection.prepareStatement(sql);
		 ResultSet resultado = statement.executeQuery();
		 
		 while(resultado.next()) {
			 Userposjava userposjava = new Userposjava();
			 userposjava.setId(resultado.getLong("id"));
			 userposjava.setNome(resultado.getString("nome"));
			 userposjava.setEmail(resultado.getString("email"));
			 
			 lista.add(userposjava);
		 }
		 
		 }catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	 }
	 
	 public Userposjava buscar(Long id) {
		 Userposjava retorno = new Userposjava();
		 try {
		 String sql = "select * from userposjava where id = " + id;
		 PreparedStatement statement = connection.prepareStatement(sql);
		 ResultSet resultado = statement.executeQuery();
		 
		 while(resultado.next()) {
			 retorno.setId(resultado.getLong("id"));
			 retorno.setNome(resultado.getString("nome"));
			 retorno.setEmail(resultado.getString("email"));
		 }
		 
		 }catch (Exception e) {
			e.printStackTrace();
		}
		 return retorno;
	 }
	 
	 public List<BeanUserFone> listarUserFone(Long idUser){
		 List<BeanUserFone> beanUserFones = new ArrayList<BeanUserFone>();
		 try {
			 String sql = "Select nome, numero, email from telefoneuser as fone ";
			 sql += " inner join userposjava as userp on fone.usuario_id = userp.id ";
			 sql += " where userp.id = " + idUser;
			 PreparedStatement statement = connection.prepareStatement(sql);
			 ResultSet resultSet = statement.executeQuery();
			 
			 while(resultSet.next()) {
				 BeanUserFone telefoneUser = new BeanUserFone();
				 telefoneUser.setNome(resultSet.getString("nome"));
				 telefoneUser.setNumero(resultSet.getString("numero"));
				 telefoneUser.setEmail(resultSet.getString("email"));
				 
				 beanUserFones.add(telefoneUser);
			 }
		 }catch (Exception e) {
			 e.printStackTrace();
		 }
		 
		 
		 return beanUserFones;
	 }
	 
	 public void atualizar(Userposjava userposjava) {
			String sql = "update userposjava set nome = ? where id = " + userposjava.getId();
			try {
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, userposjava.getNome());
				statement.execute();
				connection.commit();
				
			}catch (Exception e) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
	 
	 public void deletar(Long id) {
		 String sql = "delete from userposjava where id = " + id;
		 try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	 }

}
