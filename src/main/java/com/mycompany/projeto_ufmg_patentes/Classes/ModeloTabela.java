/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_ufmg_patentes.Classes;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Miguel
 */
public class ModeloTabela extends AbstractTableModel{
    private ArrayList linhas = null;
    private String[] colunas = null;
    
    public ModeloTabela(ArrayList linhas, String[] colunas){
        setLinhas(linhas);
        setColunas(colunas);  
    }
    
    
    public ArrayList getLinhas() {
        return linhas;
    }    
    public void setLinhas(ArrayList linhas) {
        this.linhas = linhas;
    }
    
    
    public String[] getColunas() {
        return colunas;
    }
    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }
    
    
    @Override
    public int getColumnCount(){
        return colunas.length;
    }
    
    @Override
    public int getRowCount(){
        return linhas.size();
    }
    
    @Override
    public String getColumnName(int numCol){
        return colunas[numCol];
    }
    
    @Override
    public Object getValueAt(int numLin, int numCol){
        Object[] linha = (Object[])getLinhas().get(numLin);
        return linha[numCol];
    }
}

