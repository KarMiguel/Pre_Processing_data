/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_ufmg_patentes.Classes;

import com.mycompany.projeto_ufmg_patentes.BancoDados.ConexaoBD;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Miguel
 */
public class Patente {
    
    public static ArrayList<Patente> dadosPatente = new ArrayList<>();
    
    private String categoria;
    private String dataDeposito;
    private String numPedido ;
    private String titulo;
    private int ordemAutores;
    private String inventores;
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getData_deposito() {
        return dataDeposito;
    }

    public void setData_deposito(String data_deposito) {
        this.dataDeposito = data_deposito;
    }

    public String getNum_pedido() {
        return numPedido;
    }

    public void setNum_pedido(String num_pedido) {
        this.numPedido = num_pedido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getOrdem_autores() {
        return ordemAutores;
    }

    public void setOrdem_autores(int ordem_autores) {
        this.ordemAutores = ordem_autores;
    }

    public String getInventores() {
        return inventores;
    }

    public void setInventores(String inventores) {
        this.inventores = inventores;
    }
    
    

    
    public void cadastrarPatente(String categoria, String data_deposito, String num_pedido, String titulo, int ordem_autores, String inventores){
        this.categoria = categoria;
        this.dataDeposito = data_deposito;
        this.numPedido = num_pedido;
        this.titulo = titulo;
        this.ordemAutores = ordem_autores;
        this.inventores = inventores;  
    }
    
    ConexaoBD conexao = new ConexaoBD();
    
    public void inserirPatente() {
        this.conexao.conectar();
        try {                                                                   
            PreparedStatement pst = this.conexao.con.prepareStatement("insert into patentes (categoria, data_deposito, num_pedido, titulo, ordem_autores, inventores) values(?, ?, ?, ?, ?, ?)");
            //tipo de valor setado em (campo, valor)
            pst.setString(1, getCategoria());
            pst.setString(2, getData_deposito());
            pst.setString(3, getNum_pedido());
            pst.setString(4, getTitulo());
            pst.setInt(5, getOrdem_autores());
            pst.setString(6, getInventores());
            pst.execute();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir patente no banco de dados\nErro: " + ex);
        }

        this.conexao.desconectar();
    }
    
    public boolean pesqSeTemDadosCad(String sql) throws SQLException {
        this.conexao.conectar();
        
        boolean resultPesquisa;
        this.conexao.executarSql(sql);
        
        resultPesquisa = this.conexao.rs.first();
        
        this.conexao.desconectar();
        
        return resultPesquisa;
    }

      
    
    public void preencherTabelaPatentes(JTable  tabela, String categoria) {
                
        String sql =  "select DISTINCT num_pedido, titulo, data_deposito from patentes where categoria = '"+categoria+"'";
        
        
        String[] colunas = new String[]{"Número Pedido", "Título", "Data"};
        conexao.conectar();
        conexao.executarSql(sql);
      
      
        ArrayList dados = new ArrayList();


        try {
            if (pesqSeTemDadosCad(sql)) {
                conexao.rs.first();
                do {
                    
                    dados.add(new Object[]{conexao.rs.getString("num_pedido"),
                        conexao.rs.getString("titulo"),conexao.rs.getString("data_deposito")});
                 

                } while (conexao.rs.next());
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher a tabela de patentes! \nErro: " + ex);
        }

        ModeloTabela modelo = new ModeloTabela(dados, colunas);
        tabela.setModel(modelo);
       
        conexao.desconectar();
        
    }
    
    public void preencherTabelaAutores(JTable  tabela, String categoria, String num_pedido) {
                
        String sql =  "select inventores from patentes where categoria = '"+categoria+"' and num_pedido = '"+num_pedido+"'";
      
        String[] coluna = new String[]{"Autores"};
        conexao.conectar();
        conexao.executarSql(sql);
      
        ArrayList dados = new ArrayList();

        try {
            if (pesqSeTemDadosCad(sql)) {
                conexao.rs.first();
                do {
                    dados.add(new Object[]{conexao.rs.getString("inventores")});
                } while (conexao.rs.next());
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher a tabela de autores! \nErro: " + ex);
        }

        ModeloTabela modelo = new ModeloTabela(dados, coluna);
        tabela.setModel(modelo);
       
        conexao.desconectar();
        
    }
    
    public void preencherTabelaAno(JTable  tabela, String categoria) {
                      
        String sql =  "select categoria,data_deposito,num_pedido, titulo,inventores from patentes where SUBSTRING(data_deposito, 7, 4) = '"+categoria+"';";

        String[] colunas = new String[]{"Categoria","Data","Número Pedido", "Título", "Autor"};
        conexao.conectar();
        conexao.executarSql(sql);
      
      
        ArrayList dados = new ArrayList();


        try {
            if (pesqSeTemDadosCad(sql)) {
                conexao.rs.first();
                do {                
                    dados.add(new Object[]{conexao.rs.getString("categoria"),
                        conexao.rs.getString("data_deposito"),
                        conexao.rs.getString("num_pedido"),
                        conexao.rs.getString("titulo"),
                        conexao.rs.getString("inventores")
                    
                    });
                 

                } while (conexao.rs.next());
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher a tabela de patentes! \nErro: " + ex);
        }

        ModeloTabela modelo = new ModeloTabela(dados, colunas);
        tabela.setModel(modelo);
       
        conexao.desconectar();
        
    }
     public void preencherTabelaEmailEnviado(JTable  tabela, String categoria) {
                      
       
         String sql =   "SELECT DISTINCT a.nome, a.email_enviado, p.categoria, p.data_deposito, p.num_pedido, p.titulo, p.inventores, p.status\n" +
                        "FROM autores a\n" +
                        "INNER JOIN patentes p ON a.nome = p.inventores\n" +
                        "WHERE a.email_enviado = true\n" +
                        "AND p.categoria = '"+categoria+"';";
        

        
        String[] colunas = new String[]{"Categoria","Data","Número Pedido", "Título", "Autor","Status Email"};
        conexao.conectar();
        conexao.executarSql(sql);
       
        ArrayList dados = new ArrayList();

        try {
            if (pesqSeTemDadosCad(sql)) {
                conexao.rs.first();
                do {                
                    dados.add(new Object[]{conexao.rs.getString("categoria"),
                        conexao.rs.getString("data_deposito"),
                        conexao.rs.getString("num_pedido"),
                        conexao.rs.getString("titulo"),
                        conexao.rs.getString("inventores"),
                        conexao.rs.getString("email_enviado")

                    
                    });
                 

                } while (conexao.rs.next());
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher a tabela de patentes! \nErro: " + ex);
        }

        ModeloTabela modelo = new ModeloTabela(dados, colunas);
        tabela.setModel(modelo);
       
        conexao.desconectar();
        
    }
    
    
}
