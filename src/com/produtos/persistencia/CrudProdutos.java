package com.produtos.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.prjcadprodutos.Produtos;

/**
 * @author jandira.pires
 */

public class CrudProdutos {

	private Connection con = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;


	public String cadastrar(Produtos produto) {
		
		String msg = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbprodutos?serverTimezone=UTC","root","");
			
			String consulta = "INSERT INTO tbprodutos(nome, descricao, fabricante, quantidade, preco)values(?,?,?,?,?)";
			
			pst = con.prepareStatement(consulta);
			
			pst.setString(1, produto.getNome());
			pst.setString(2, produto.getDescricao());
			pst.setString(3, produto.getFabricante());
			pst.setInt(4, produto.getQuantidade());
			pst.setDouble(5, produto.getPreco());
			
			int r = pst.executeUpdate();
			
			if(r > 0)
    			msg = "Cadastro realizado com sucesso!";
    		else
    			msg = "N�o foi poss�vel cadastrar!";
    		     		
    	}
    	catch(SQLException ex) {
    		msg = "Erro ao tentar cadastrar: "+ex.getMessage();
    	}
    	catch(Exception e) {
    		msg = "Erro inesperado: "+e.getMessage();
    	}
    	finally {
    		try{con.close();}catch(Exception e) {e.printStackTrace();}
    	}
    	
    	return msg;
								
		}
	
	
	
	public String atualizar(Produtos produto) {
		String msg = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbprodutos?serverTimezone=UTC","root","");
			
			String consulta = "UPDATE tbprodutos SET nome=?, descricao=?, fabricante=?, quantidade=?, preco=? WHERE id=?";
			
			pst = con.prepareStatement(consulta);
			
			pst.setString(1, produto.getNome());
			pst.setString(2, produto.getDescricao());
			pst.setString(3, produto.getFabricante());
			pst.setInt(4, produto.getQuantidade());
			pst.setDouble(5, produto.getPreco());
			
			int r = pst.executeUpdate();
			
			if(r > 0)
    			msg = "Cadastro realizado com sucesso!";
    		else
    			msg = "N�o foi poss�vel cadastrar!";
    		     		
	}
    	catch(SQLException ex) {
    		msg = "Erro ao tentar cadastrar: "+ex.getMessage();
    	}
    	catch(Exception e) {
    		msg = "Erro inesperado: "+e.getMessage();
    	}
    	finally {
    		try{con.close();}catch(Exception e) {e.printStackTrace();}
    	}
    	
    	return msg;
}
	
	public String deletar(Produtos produto) {
		String msg = "";
		try {
		
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbprodutos?serverTimezone=UTC","root","");
			
			String consulta = "DELETE FROM tbprodutos WHERE id=?";
					
	pst = con.prepareStatement(consulta);
    		
    		pst.setInt(1, produto.getId());
    		
    		
    		int r = pst.executeUpdate();
    		
    		
			if(r > 0)
    			msg = "Deletado com sucesso!";
    		else
    			msg = "N�o foi poss�vel deletar!";
    		     		
    	}
    	catch(SQLException ex) {
    		msg = "Erro ao tentar deletar: "+ex.getMessage();
    	}
    	catch(Exception e) {
    		msg = "Erro inesperado: "+e.getMessage();
    	}
    	finally {
    		try{con.close();}catch(Exception e) {e.printStackTrace();}
    	}
    	
    	return msg;
		
	
    	}
	
	 public List<Produtos> PesquisarPorNome(String nome){
	    	
	    	List<Produtos> lista = new ArrayList<Produtos>();
	    	
	    	try {
	    		//carregar o drive de comunica��o com o banco de dados
	    		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	    		
	    		//Chamar o gerenciador de driver
	    		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbprodutos?serverTimezone=UTC","root","");
	    		
	    		//Vamos criar a consulta para selecionar os clientes por nome
	    		String consulta = "Select * from tbprodutos where nome=?";
	    		
	    		pst = con.prepareStatement(consulta);
	    		
	    		pst.setString(1,nome);
	    		
	    		//Vamos executar a consulta e guardar o resultado na vari�vel rs
	    		rs = pst.executeQuery();
	    		
	    		/*
	    		 * Vamos pegar um cliente por vez que est� no rs e adicion�-lo
	    		 * a lista de clientes para, ent�o retorn�-la   		
	    		 */
	    		while(rs.next()) {
	    			lista.add(new Produtos(
	    					rs.getInt(0),
	    					rs.getString(1),
	    					rs.getString(2),
	    					rs.getString(3),
	    					rs.getInt(4),
	    					rs.getDouble(5)
	    					));
	    		}//Fim do while
	    		
	    	}//Fim do try
	    	
	    	catch(SQLException ex) {
	    		ex.printStackTrace();
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    	finally {
	    		try {con.close();} catch(Exception e) {e.printStackTrace();}
	    	}

	    	return lista;
	 }
	
}

