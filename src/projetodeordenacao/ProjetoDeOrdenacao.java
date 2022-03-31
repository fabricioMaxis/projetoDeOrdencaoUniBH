/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projetodeordenacao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * @Fabricio 119123048 e Sunner 11919299
 */
public class ProjetoDeOrdenacao {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws  Exception {
        
       try{
            List<Item> listaItens = new ArrayList<Item>();
            
            LerCSV(listaItens);
            
            OrdenarPorCategoria(listaItens);

            System.out.println("**LISTA ORIGINAL**");
            for (int i = 0; i < listaItens.size(); i++) {
                System.out.println("Jogo:"+ listaItens.get(i).getJogo() 
                        + ", Categoria:" + listaItens.get(i).getCategoria() 
                        + ", Nota: " + listaItens.get(i).getNota());
            }

            System.out.println("**LISTA ORDENADA POR NOTA**");
            OrdernarPorNota(listaItens);

            for (int i = 0; i < listaItens.size(); i++) {
                System.out.println("Jogo:"+ listaItens.get(i).getJogo() 
                        + ", Categoria:" + listaItens.get(i).getCategoria() 
                        + ", Nota: " + listaItens.get(i).getNota());
            }
            EscreverNovoCSV(listaItens);
       }
       catch(Exception e){
           throw e;
       }

    }
    
    public static void LerCSV(List<Item> listaItens) throws Exception{
        try{
            String path = "C:/Users/falus/Desktop/atividadeRafa/JogosDesordenados.csv";
            BufferedReader br = new BufferedReader(new FileReader(path));
            String linha = "";
            while((linha = br.readLine()) != null){
                String[] jogo = linha.split(",");
                listaItens.add(new Item(jogo[0],jogo[1], Double.parseDouble(jogo[2])));
            }
        }
        catch(FileNotFoundException e){
            throw e;
        }
    }

    public static void EscreverNovoCSV(List<Item> listaItens) throws IOException {

        FileWriter writer = new FileWriter("C:/Users/falus/Desktop/atividadeRafa/JogosOrdenados.csv");

        String collect =  listaItens.stream()
        .map(item -> item.getJogo() + ", " + item.getCategoria() + ", " + item.getNota() + "\n")
        .collect(Collectors.joining(""));

        System.out.println(collect);

        writer.write(collect);
        writer.close();
    }

    //Realiza a ordenação por categoria
    public static void OrdenarPorCategoria(List<Item> listaItens){
        for(int i =0; i < listaItens.size();i ++){
            for(int j = i+1; j < listaItens.size(); j++){
                String categoriaA = listaItens.get(j).getCategoria();
                String categoriaB = listaItens.get(i).getCategoria();
                if(categoriaA.compareTo(categoriaB) < 1){
                    Item temp = listaItens.get(i);
                    listaItens.set(i, listaItens.get(j));
                    listaItens.set(j, temp);
                }
            }
        }
    }

    //Realiza a ordenação por nota
    public static void OrdernarPorNota(List<Item> listaItens) throws Exception{
        
        for(int i = 0; i < listaItens.size();i++){
            for(int j = i+1; j < listaItens.size(); j++){
                Item itemA = listaItens.get(i);
                Item itemB = listaItens.get(j);

                if(itemA.getNota() < itemB.getNota() 
                    && itemB.getCategoria().equals(itemA.getCategoria())){
                    Item temp = listaItens.get(i);
                    listaItens.set(i, listaItens.get(j));
                    listaItens.set(j, temp);
                }
            }
        }
    }
}
