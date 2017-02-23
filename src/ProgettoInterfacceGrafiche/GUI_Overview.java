package ProgettoInterfacceGrafiche;

import java.awt.event.*;
import java.util.List;
import javax.swing.*;

public class GUI_Overview extends JFrame implements WindowListener{
    
    private JPanel container;//pannello che contiene gli altri 
    private GUI_MenuBar bar;//principale barra dei menù dell'applicazione
    private GUI_SearchPanel header;//intestazione del programma, subito sotto la barra dei menù, è il pannello di ricerca
    private GUI_ProductsList list;//pannello utilizzato per la visualizzazione dei risultati di ricerca
    private GUI_Product page;//pannello nel quale vengono visualizzate le informazioni relative ad un prodotto selezionato
    private GUI_Info info;//pannello nel quale vengono visualizzate le informazioni relative all'utente che ha effettuato l'accesso
    private GUI_Cart cart;//pannello utile alla visualizzazione del carrello dell'utente che ha effettuato l'accesso
    
    public GUI_Overview() {
        container = new JPanel();
        bar = new GUI_MenuBar();
        header = new GUI_SearchPanel();
        list = new GUI_ProductsList();
        page = new GUI_Product();
        cart = new GUI_Cart();
        info = new GUI_Info();
        bar.setFrame(this);
        header.setFrame(this);
        list.setFrame(this);
        container.add(header);
        container.add(list.jsp);
        container.add(page);
        container.add(cart.jsp);
        container.add(info);
        page.setVisible(false);
        cart.jsp.setVisible(false);
        info.setVisible(false);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        add(container);
        setJMenuBar(bar);
        setVisible(true);
        pack();
        setTitle("Atomic World");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(this);
    }
    
    //metodo che costruisce la pagina relativa la visualizzazione dei risultati di ricerca, secondo una lista passatagli come parametro.
    public void BuildProductsList(List<Prodotto> prodotti) {
        list.jsp.setVisible(true);
        page.setVisible(false);
        cart.jsp.setVisible(false);
        info.setVisible(false);
        list.setPage(prodotti);
    }
    
    //metodo che costruisce la pagina del prodotto passatogli come parametro
    public void BuildProductPage(Prodotto p) {
        list.jsp.setVisible(false);
        page.setVisible(true);
        cart.jsp.setVisible(false);
        info.setVisible(false);
        page.setPage(p);
    }
    
    //metodo che costruisce la pagina relativa al carrello dell'utente che ha effettuato l'accesso.
    public void BuildCartPage() {
        cart.jsp.setVisible(true);
        list.jsp.setVisible(false);
        page.setVisible(false);
        info.setVisible(false);
        cart.addElements();
    }
    //metodo che costruisce la pagina relativa alle informazione dell'utente che ha effettuato l'accesso
    public void BuildInfoPage() {
        info.setVisible(true);
        cart.jsp.setVisible(false);
        list.jsp.setVisible(false);
        page.setVisible(false);
        info.addElements();
    }
    
    //metodo che rende visibili tutti quei componenti utilizzabili sono da un utente autenticato
    public void Logged() {
        page.setButtonVisible();
        bar.isLogged();
    }
    
    @Override
    public void windowClosing(WindowEvent e) {
        switch(JOptionPane.showConfirmDialog(null, "Vuoi uscire veramente?", "Esci", JOptionPane.YES_NO_OPTION)) {
            case 0:
                Utenza.updateUtente();
                System.exit(0);
                break;
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
    
}
