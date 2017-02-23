package ProgettoInterfacceGrafiche;

import java.io.*;
import java.util.*;

public class Negozio implements Comparator<Prodotto>{
    
    private static List<Prodotto> prodotti;//lista che contiene i prodotti
    private String folderName = "prodotti";//si riferisce alla cartella contenente i prodotti
    private String sortType;//ordinamento 
    
    public Negozio() {
        prodotti = new ArrayList();
        fillList();
    }
    
    public static List<Prodotto> getProdotti() {
        return Negozio.prodotti;
    }
    
    //metodo che per ogni file all'interno della cartella contenente i prodotti crea 
    //un Prodotto che immetterà poi nella lista.
    private void fillList() {
        File file = new File(folderName);
        if(!file.exists()) {
            file.mkdir();
        }else {
            for(File f : file.listFiles()) {
                try(BufferedReader in = new BufferedReader(new FileReader(f))) {
                    prodotti.add(new Prodotto(in.readLine(), in.readLine(), in.readLine(), Float.valueOf(in.readLine())));
                    in.close();
                }catch(Exception e) {}
            }
        }
    }
    
    //metodo utilizzato per la costruzione dei risultati di ricerca.
    public static List<Prodotto> searchProducts(String nome, String categoria) {
        List<Prodotto> risultato = new ArrayList();
        
        for(Prodotto p : prodotti) {
            if(p.getCategoria().toLowerCase().contains(categoria) && p.getNome().toLowerCase().contains(nome)) {
                risultato.add(p);
            }
        }
        
        return risultato;
    }
    
    //metodo che restituisce un prodotto conoscendo soltanto il suo nome.
    public static Prodotto getProdotto(String nome) {
        for(Prodotto p : prodotti) {
            if(p.getNome().equals(nome)) {
                return p;
            }
        }
        return null;
    }
    
    
    //metodo che crea un nuovo prodotto e ne crea il relativo file contenente i suoi dati, oltre ad essere aggiunto nella lista corrente.
        public boolean newProdotto(Prodotto p) {
        File output = new File(folderName + File.separator + p.getNome() + ".txt");
        
        if(!exist(p)) {
            try(BufferedWriter out = new BufferedWriter(new FileWriter(output))) {
                out.write(p.getNome() + System.getProperty("line.separator")
                        + p.getCategoria() + System.getProperty("line.separator")
                        + p.getDescrizione() + System.getProperty("line.separator")
                        + p.getPrezzo());
                out.close();
            }catch(Exception e) {}
            prodotti.add(p);
            return true;
        }
        
        return false;
    }
    
    //metodo che controlla se un determinato prodotto esiste già    
    public static boolean exist(Prodotto p) {
        for(Prodotto prodotto : prodotti) {
            if(prodotto.getNome().equals(p.getNome())) {
                return true;
            }
        }
        return false;
    }
    
    //metodo che ordina la lista secondo il criterio specificato secondo il parametro passatogli.
    public void sort(String type) {
        switch(type) {
            case "Alfabetico":
                Collections.reverse(prodotti);
                break;
            case "Prezzo: crescente":
                sortType = "crescente";
                Collections.sort(prodotti, this);
                break;
            case "Prezzo: decrescente":
                sortType = "decrescente";
                Collections.sort(prodotti, this);
                break;
        }
    }
    
    //metodo che, a partire dalle categorie di tutti i prodotti, prese una sola volta, costruisce e restituisce una lista con quest'ultimi valori
    public static List<String> getCategories() {
        List<String> categorie = new ArrayList();
        categorie.add("");
        for(Prodotto p : Negozio.getProdotti()) {
            if(!categorie.contains(p.getCategoria())) {
                categorie.add(p.getCategoria());
            }
        }
        Collections.sort(categorie);
        return categorie;
    }

    //Ha la funzione di confrontare due prodotti secondo il loro prezzo, crescente e decrescente
    @Override
    public int compare(Prodotto p1, Prodotto p2) {
        if(sortType.equals("crescente"))
            return (int)(p1.getPrezzo() - p2.getPrezzo());
        else
            return (int)(p2.getPrezzo() - p1.getPrezzo());
    }
    
}
