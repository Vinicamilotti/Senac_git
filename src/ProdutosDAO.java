/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public ProdutosDAO() {
        conectar();
    }
    
    private void conectar() {
        conn = new conectaDAO().connectDB();
    }
    
    public void cadastrarProduto (ProdutosDTO produto) throws Exception {
        
        

        String insertStatment = "INSERT INTO produtos(nome, valor, status) VALUES (?, ?, ?)";
        try {
            
        
        PreparedStatement statment = conn.prepareStatement(insertStatment);
        statment.setString(1, produto.getNome());
        statment.setInt(2, produto.getValor());
        statment.setString(3, produto.getStatus());
        statment.execute();
        }catch(Exception ex) {
            throw ex;
        }
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos() throws Exception {
        String buscarProdutosSQL = "SELECT * FROM produtos";
        Statement statement = conn.createStatement();
        try {
            resultset = statement.executeQuery(buscarProdutosSQL);
            listagem.clear();
            while(resultset.next()) {
                ProdutosDTO produto  = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }
        }
        catch(Exception ex){
            throw ex;
        }
        return listagem;
    }
    
    
    
        
}

