package com.mycompany.projeto_ufmg_patentes.BancoDados;

import java.sql.*;
import javax.swing.JOptionPane;

public class ConexaoBD {
    public Statement stm;
    public ResultSet rs;
    public Connection con;
    private String driver = "org.postgresql.Driver";
    private String caminho = "jdbc:postgresql://localhost:5432/patentes_ufmg_bd";
    private String usuario = "postgres";
    private String senha = "adminpass";
    
    
    public void conectar(){      
        try{
            System.setProperty("jdbc.Drivers", driver);
            con = DriverManager.getConnection(caminho, usuario, senha);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao tentar conectar com o banco de dados: \n"+ex.getMessage());
        }
    }

    public void desconectar(){
        try {
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão com banco de dados:\n"+ex);
        }
    }
 
    //metodo responsavel por executar qualquer comando sql passado como argumento
    public void executarSql(String sql){
        try {                   //Diferir maiúsculas e minúsculas, percorer todo o banco de dados
            stm = con.createStatement(rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro executa sql: \n"+ex);
            JOptionPane.showMessageDialog(null, "Erro aqui: "+sql);
        }
    }
 
    //Método para pesquisar dados no banco retornando true ou false
    public boolean pesquisarComRetornoBoolean(String string) throws SQLException {
        this.conectar();
        
        boolean resultPesquisa;
        this.executarSql(string);
        
        resultPesquisa = this.rs.first();
        
        this.desconectar();
        
        return resultPesquisa;
    }
   
}