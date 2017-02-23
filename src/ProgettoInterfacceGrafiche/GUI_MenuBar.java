package ProgettoInterfacceGrafiche;
//rappresenta la barra dei menù dell'applicazione, estende la classe JMenuBar ed implementa ActionListener
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class GUI_MenuBar extends JMenuBar implements ActionListener{

    private GUI_Overview frame;//serve per riferirsi esplicitamente all'oggetto che rappresenta la finestra principale
    
    private JMenu jmAccesso = new JMenu("Accedi");// elemento della barra grazie al quale vi si trovano tutte le vie per accedere come utente o registrarvi all'applicazione
    private JMenuItem jmiAccedi = new JMenuItem("Accedi");//elemento interno a jmAccesso, serve per accedere come utente
    private JMenuItem jmiRegistrati = new JMenuItem("Registrati");//elemento interno a jmAccesso, serve per registrarsi all'applicazione
    
    private static JMenu jmAccount = new JMenu("Account");//elemento della barra grazie al quale vi si trovano tutte le vie per la gestione del proprio account
    private JMenuItem jmiAccount = new JMenuItem("Il mio Account");//elemento interno a jmAccount, serve per la visualizzazione delle informazioni relative il proprio account
    private JMenuItem jmiCart = new JMenuItem("Carrello");//visualizza il carrello dell'utente
    private JMenuItem jmiStyle = new JMenuItem("Stile");//elemento interno a jmAccount, serve per cambiare il look and feel dell'applicazione
    
    private JMenu jmEsci = new JMenu("Esci");//elemento della barra grazie al quale si può uscire dall'applicazione.
    private JMenuItem jmiEsci = new JMenuItem("Esci");//elemento interno a jmEsci, serve per uscire effettivamente dall'applicazione
    
    //costruttore della classe che aggiunge tutti gli attributi sopra riportati alla barra, oltre ad aggiungergli un azione ed un ascoltatore
    public GUI_MenuBar() {
        jmAccesso.add(jmiAccedi);
        jmiAccedi.setActionCommand("LogIn");
        jmiAccedi.addActionListener(this);
        jmAccesso.add(jmiRegistrati);
        jmiRegistrati.setActionCommand("Sign up");
        jmiRegistrati.addActionListener(this);
        add(jmAccesso);
        jmAccount.add(jmiAccount);
        jmiAccount.setActionCommand("Account");
        jmiAccount.addActionListener(this);
        jmAccount.add(jmiCart);
        ImageIcon icon = new ImageIcon("icone" + File.separator + "Cart.png");
        Image newimg = icon.getImage().getScaledInstance((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2 / 100, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2 / 100,  java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        jmiCart.setIcon(icon);
        jmiCart.setActionCommand("Cart");
        jmiCart.addActionListener(this);
        jmAccount.add(jmiStyle);
        jmiStyle.setActionCommand("Style");
        jmiStyle.addActionListener(this);
        jmAccount.setVisible(false);
        add(jmAccount);
        jmEsci.add(jmiEsci);
        jmiEsci.setActionCommand("Esci");
        jmiEsci.addActionListener(this);
        add(Box.createHorizontalGlue());
        add(jmEsci);
    }
   
    //metodo setter per impostare il riferimento alla finestra principale.
    public void setFrame(GUI_Overview frame) {
        this.frame = frame;
    }
    
    //metodo che rende visibile tutti quei componenti con i quali l'utente verificato potrà interagire
    public void isLogged() {
        jmAccount.setVisible(true);
        jmiAccedi.setText("Cambia utente");
        jmiRegistrati.setVisible(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "Esci":
                switch(JOptionPane.showConfirmDialog(null, "Vuoi uscire veramente?", "Esci", JOptionPane.YES_NO_OPTION)) {
                    case 0:
                        Utenza.updateUtente();
                        System.exit(0);
                        break;
                }
                break;
            case "LogIn":
                Utenza.updateUtente();
                new LogIn(frame);
                break;
            case "Sign up":
                new SignUp();
                break;
            case "Account":
                frame.BuildInfoPage();
                break;
            case "Cart":
                frame.BuildCartPage();
                break;
            case "Style":
                new AppStyle();
                break;
        }
    }
    
}
