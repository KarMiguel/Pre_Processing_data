/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projeto_ufmg_patentes.Executavel;

import com.mycompany.projeto_ufmg_patentes.Classes.Patente;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import com.mycompany.projeto_ufmg_patentes.Interfaces.*;
import javax.swing.*;
/**
 *
 * @author Miguel
 */
public class Projeto_UFMG_Patentes {

    
    
   private static ArrayList<ArrayList<String>> listaPatentes = new ArrayList<ArrayList<String>>();
    
    public static void main(String[] args) throws IOException {
       
       /* lerArquivo();
        for (int i = 0; i < 10); i++) {
            System.out.println(listaPatentes.get(i).get(0));
        }
        
        alimentarTabelaBD();
        */
        new TelaPatentes().setVisible(true);
                 
    }
     /*
    public static void alimentarTabelaBD(){
        
        for (int i = 1; i < listaPatentes.size(); i++) {
            
            String categoria = listaPatentes.get(i).get(0);
            String data_deposito = listaPatentes.get(i).get(1);
            String num_pedido = listaPatentes.get(i).get(2);
            String titulo = listaPatentes.get(i).get(3);
            int ordem_autores = Integer.parseInt(listaPatentes.get(i).get(4));
            String inventores = listaPatentes.get(i).get(5);
     
            Patente patente = new Patente();
            patente.cadastrarPatente(categoria, data_deposito, num_pedido, titulo, ordem_autores, inventores);
            patente.inserirPatente();
                    
            System.out.println("Cadastro linha de nº "+(i+1));        
            
        }
    }
    
    
     
    public static void lerArquivo() throws FileNotFoundException, IOException{
        
        File [] arquivos;  
        File diretorio = new File("D:\\Área de Trabalho\\SISTEMAS DE INFORMAÇÕES -IF\\Java\\Projeto_UFMG_Patentes\\dados_BD");  
	arquivos = diretorio.listFiles(); 
  
	for(int i = 0; i < arquivos.length; i++){
            System.out.println("Lendo arquivo: "+arquivos[i].getName());

            BufferedReader dados_arquivo  = new BufferedReader(new InputStreamReader(new FileInputStream(arquivos[i]), "UTF-8"));
            
                String line; 
    	  	         
  	  	while ((line = dados_arquivo.readLine()) != null) {
   	  	       String[] items = line.split(";");
   	  	       ArrayList<String> transaction = new ArrayList<String>();
            
                      
   	  	       for (int j = 0; j < items.length; j++) { 
   	  	           String item = (items[j]);

   	                 transaction.add(item);         
   	             } 
   	  	    listaPatentes.add(transaction);  
                
                }
                   
                dados_arquivo.close();
        }
                   
               
    }
*/
}
