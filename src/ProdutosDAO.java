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
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto) throws Exception {
        
        
        conn = new conectaDAO().connectDB();
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
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

