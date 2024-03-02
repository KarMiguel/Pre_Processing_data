/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_ufmg_patentes.Classes;

import com.mycompany.projeto_ufmg_patentes.BancoDados.ConexaoBD;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguel
 */
public class Autor {

    private int id;
    private String nome;
    private String celular;
    private String email;
    private boolean emailEnviado;

    public boolean isEmail_enviado() {
        return emailEnviado;
    }

    public void setEmail_enviado(boolean email_enviado) {
        this.emailEnviado = email_enviado;
    }
    
    ConexaoBD conexao = new ConexaoBD();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    public void buscarTodosDadosAutorPorId(int id) {
        String sql = "select * from autores where id = " + id;

        conexao.conectar();
        conexao.executarSql(sql);

        try {
            if (pesqSeTemDadosCad(sql)) {
                conexao.rs.first();

                this.id = conexao.rs.getInt("id");
                this.nome = conexao.rs.getString("nome");
                this.celular = conexao.rs.getString("celular");
                this.email = conexao.rs.getString("email");
                this.emailEnviado = conexao.rs.getBoolean("email_enviado");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar dados do autor! \nErro: " + ex);
        }
    }

 

private boolean pesqSeTemDadosCad() throws SQLException {
    return conexao.rs.next();
}

    
  public void updateAutor(String celular, String email, String nome) {
    String sql = "UPDATE autores SET email = ?, celular = ? WHERE nome = ?";
    conexao.conectar();
    PreparedStatement pstmt = null;

    try {
        pstmt = conexao.con.prepareStatement(sql);
        pstmt.setString(1, email);
        pstmt.setString(2, celular);
        pstmt.setString(3, nome);
        pstmt.executeUpdate();

    } catch (SQLException ex) {
        System.out.println("Erro ao buscar dados do autor! \nErro: " + ex);
    } finally {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar PreparedStatement: " + ex);
            }
        }
        conexao.desconectar();
    }
}

    
    public void marcarEmailEnviado(String nome) {
    String sql = "UPDATE autores SET email_enviado = true WHERE nome = ?";
    conexao.conectar();
    PreparedStatement pstmt = null;
    try {
        pstmt = conexao.con.prepareStatement(sql);
        pstmt.setString(1, nome);
        pstmt.executeUpdate();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erro ao marcar email como enviado: \n" + ex);
    } finally {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar PreparedStatement: \n" + ex);
            }
        }
        conexao.desconectar();
    }
}

    
    
    public  void buscarDadosEspecAutores(String inventores){
        String sql =  "select * from autores where nome = '"+inventores+"'";
        
        conexao.conectar();
        conexao.executarSql(sql);
      
        try {
            if (pesqSeTemDadosCad(sql)) {
                conexao.rs.first();
             
                this.id = conexao.rs.getInt("id");
                this.nome = conexao.rs.getString("nome");
                this.celular = conexao.rs.getString("celular");
                this.email = conexao.rs.getString("email");
                this.emailEnviado = conexao.rs.getBoolean("email_enviado"); 
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar dados especificos do autor selecionado! \nErro: " + ex);
        }
    }
    
  
    public boolean pesqSeTemDadosCad(String sql) throws SQLException {
        this.conexao.conectar();
        
        boolean resultPesquisa;
        this.conexao.executarSql(sql);
        
        resultPesquisa = this.conexao.rs.first();
        
        this.conexao.desconectar();
        
        return resultPesquisa;
    }
    
}
