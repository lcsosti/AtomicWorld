package ProgettoInterfacceGrafiche;

/*
modello principale del prodotto ovvero dove vengo definite le caratteristiche e le azioni
*/
import java.awt.*;
import java.io.File;
import javax.swing.ImageIcon;

public class Prodotto {
    
    private String nome;
    private String categoria;
    private String descrizione;
    private float prezzo;

    public Prodotto(String nome, String categoria, String descrizione, float prezzo) {
        this.nome = nome;
        this.categoria = categoria;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getCategoria() {
        return categoria;
    }

    public float getPrezzo() {
        return prezzo;
    }
    
    //ritorna immagine del prodotto a partire dal nome
    public ImageIcon getImmagine() {
        ImageIcon imageIcon = new ImageIcon("immagini" + File.separator + nome + ".png");
        Image newimg = imageIcon.getImage().getScaledInstance((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 25 / 100, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 20 / 100,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        return imageIcon;
    }
    
}
