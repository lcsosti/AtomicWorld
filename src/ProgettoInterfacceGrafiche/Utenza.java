package ProgettoInterfacceGrafiche;
/*
classe che gestisce tutti gli utenti registrati e quello correntemente autenticato. Essa ha molti metodi statici in quanto la si è voluta rendere una classe accessibile da chiunque
*/
import java.io.*;
import java.util.*;

public class Utenza {
    
    private static List<Utente> utenti;//lista contenete tutti gli utenti registrati all'applicazione
    private static final String folderName = "utenti";//riferirsi alla cartella contenente gli utenti
    private static Utente currentUtente;//rappresenta utente loggato
    
    public Utenza() {
        utenti = new ArrayList();
        fillList();
    }
    
    //metodo che per ogni file all'interno della cartella contenente gli utenti crea un utente che immetterà poi nella lista.
    private void fillList() {
        File file = new File(folderName);
        if(!file.exists()) {
            file.mkdir();
        }else {
            for(File f : file.listFiles()) {
                try(BufferedReader in = new BufferedReader(new FileReader(f))) {
                    Utente newUser = new Utente(in.readLine(), in.readLine(), in.readLine(), in.readLine(), Float.valueOf(in.readLine()));
                    String nome;
                    while((nome = in.readLine()) != null) {
                        newUser.addToCart(Negozio.getProdotto(nome));
                    }
                    utenti.add(newUser);
                    in.close();
                }catch(Exception e) {}
            }
        }
    }
    
    //imposta l'utente che ha effettuato l'accesso
    public static void setCurrentUtente(Utente utente) {
        currentUtente = utente;
    }
    
    public static Utente getCurrentUtente() {
        return currentUtente;
    }
    
    //metodo che crea un nuovo utente e ne crea il relativo file contenente i suoi dati, oltre ad essere aggiunto nella lista corrente.
    public static boolean newUtente(Utente utente) {
        File output = new File(folderName + File.separator + "user" + utenti.size() + ".txt");
        
        if(!exist(utente.getEmail())) {
            try(BufferedWriter out = new BufferedWriter(new FileWriter(output))) {
                out.write(utente.getNome() + System.getProperty("line.separator")
                        + utente.getCognome() + System.getProperty("line.separator")
                        + utente.getEmail() + System.getProperty("line.separator")
                        + utente.getPassword() + System.getProperty("line.separator")
                        + utente.getSaldo());
                out.close();
            }catch(Exception e) {
                return false;
            }
            utenti.add(utente);
            return true;
        }
        
        return false;
    }
//metodo che aggiorna il contenuto del file di un determinato utente. Più nello specifico è utilizzato per andare a riscrivere tutti i prodotti del carrello aggiunti in fase d'esecuzione
    public static void updateUtente() {
        if(currentUtente != null) {
            File output = new File(folderName + File.separator + "user" + utenti.indexOf(currentUtente) + ".txt");

            try(BufferedWriter out = new BufferedWriter(new FileWriter(output))) {
                out.write(currentUtente.getNome() + System.getProperty("line.separator")
                        + currentUtente.getCognome() + System.getProperty("line.separator")
                        + currentUtente.getEmail() + System.getProperty("line.separator")
                        + currentUtente.getPassword() + System.getProperty("line.separator")
                        + currentUtente.getSaldo() + System.getProperty("line.separator"));
                for(Prodotto p : currentUtente.getCart()) {
                    out.write(p.getNome() + System.getProperty("line.separator"));
                }
                out.close();
            }catch(Exception e) {}
            utenti.add(currentUtente);
        }
    }
    
    //metodo che ritorna l'utente con l'email corrispondente a quella passatagli come parametro.
    public static Utente getUtente(String email) {
        for(Utente utente : utenti) {
            if(utente.getEmail().equals(email)) {
                return utente;
            }
        }
        return null;
    }
    
    //metodo che controlla se un determinato utente esiste già, confrontando la sua email con quelle presenti nella lista
    public static boolean exist(String email) {
        for(Utente utente : utenti) {
            if(utente.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
    /*metodo utilizzato per l'autenticazione. Se l'email e la password inserite, e quindi passati come parametri al metodo, 
    corrispondono con almeno l'email e la password di un utente interno alla lista allora il logIn avrà successo
    */
    public static boolean getUserData(String email, String password) {
        for(Utente utente : utenti) {
            if(utente.getEmail().equals(email) && utente.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    
}
