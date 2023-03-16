package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Carro;

public class CarroDAO extends DAO {
	
	public CarroDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}
	
	public boolean insert(Carro carro) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO carro (codigo, nome, cor, preco,descricao) "
				       + "VALUES ("+carro.getCodigo()+ ", '" + carro.getNome() + "', '"  
				       + carro.getCor() + "', '" + carro.getPreco() +"', '"+ carro.getDescricao()+"');";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	public Carro get(int codigo) {
		Carro carro = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM carro WHERE codigo =" + codigo;
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 carro = new Carro(rs.getInt("codigo"), rs.getString("nome"), rs.getString("cor"), rs.getString("descricao") ,rs.getFloat("preco"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return carro;
	}
	
	public int getRecentCarro() {
    Carro carro = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM carro ORDER BY codigo DESC LIMIT 1";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 carro = new Carro(rs.getInt("codigo"), rs.getString("nome"), rs.getString("cor"), rs.getString("descricao") ,rs.getFloat("preco"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return carro.getCodigo();
	}
	
	public List<Carro> get() {
		return get("");
	}

	public List<Carro> getOrderByCodigo() {
		return get("codigo");		
	}
	public List<Carro> getOrderByDescricao() {
		return get("descricao");		
	}
	
	public List<Carro> getOrderByNome() {
		return get("nome");		
	}
	
	public List<Carro> getOrderByCor() {
		return get("cor");		
	}
	public List<Carro> getOrderByPreco() {
		return get("preco");		
	}
	
	
	private List<Carro> get(String orderBy) {	
	
		List<Carro> carro = new ArrayList<Carro>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM carro" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Carro c = new Carro(rs.getInt("codigo"), rs.getString("nome"), rs.getString("cor"),rs.getString("descricao"), rs.getFloat("preco"));
	            carro.add(c);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return carro;
	}
	
	public boolean update(Carro carro) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE carro SET nome = '" + carro.getNome() + "', cor = '"  
				       + carro.getCor() + "', preco = '" + carro.getPreco()+"', descricao = '" + carro.getDescricao() + "'"
					   + " WHERE codigo = " + carro.getCodigo();
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean delete(int codigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM carro WHERE codigo = " + codigo;
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			System.out.println("ERRO");
		} 
		return status;
	}
	
}