package ProgettoInterfacceGrafiche;

import java.util.*;

public class Utente {
    
    private String nome;
    private String cognome;
    private String password;
    private String email;
    private float saldo;
    private List<Prodotto> carrello;

    public Utente(String nome, String cognome, String email, String password, float saldo) {
        this.nome = nome;
        this.cognome = cognome;
        this.password = password;
        this.email = email;
        this.saldo = saldo;
        carrello = new ArrayList();
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }
    
    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return email;
    }

    public float getSaldo() {
        return saldo;
    }
    
    public void addSaldo(float denaro) {
        saldo += denaro;
    }
    
    public void removeSaldo(float denaro) {
        saldo -= denaro;
    }
    
    public List<Prodotto> getCart() {
        return this.carrello;
    }
    
    public void addToCart(Prodotto p) {
        carrello.add(p);
    }
    
    public void removeFromCart(Prodotto p) {
        carrello.remove(p);
    }
    
}
