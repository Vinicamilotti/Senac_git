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
    
    public ArrayList<ProdutosDTO> listarProdutos(Boolean apenasVendas) throws Exception {
        String buscarProdutosSQL = "SELECT * FROM produtos";
        if(apenasVendas){
            buscarProdutosSQL += " WHERE status = 'Vendido'";
        }
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

    public ProdutosDTO getProduto(int idProduto) throws Exception {
        String sqlBuscar = "SELECT * FROM produtos WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sqlBuscar);
        statement.setInt(1, idProduto);
        ResultSet result = statement.executeQuery();
        int rowCount = result.last() ? result.getRow() : 0;
        if (rowCount == 0) {
            throw new Exception("Nenhum produto encontrado");
        }
        ProdutosDTO produto  = new ProdutosDTO();
        produto.setId(result.getInt("id"));
        produto.setNome(result.getString("nome"));
        produto.setValor(result.getInt("valor"));
        produto.setStatus(result.getString("status"));
        return produto;
        
    }
    
    public void venderProduto(int idProduto) throws Exception
    {
        ProdutosDTO getProduto = getProduto(idProduto);
        if(getProduto.getStatus().equalsIgnoreCase("Vendido")){
            throw new Exception("Produto " + getProduto.getNome() + " já foi vendido");
        }
        
        String sqlUpdate = "UPDATE produtos SET status = ? WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sqlUpdate);
        statement.setString(1, "Vendido");
        statement.setInt(2, idProduto);
        statement.execute();
    }
    
    
        
}

