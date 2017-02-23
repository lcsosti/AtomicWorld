package ProgettoInterfacceGrafiche;
//classe nella quale viene visualizzato nello specifico il contenuto di un prodotto. 
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

class GUI_Product extends JPanel implements ActionListener{
    
    private JButton buy = new JButton("Aggiungi al carrello");// bottone che serve per l'aggiunta al carrello del prodotto correntemente visualizzato. Questo bottone rimarrà nascosto finché un utente non accederà
    private SpringLayout layout = new SpringLayout();
    
    //costruttore della classe che imposta il suo layout
    public GUI_Product() {
        setLayout(layout);
    }
    
    //metodo che aggiunge al pannello tutte quei componenti adatti alla visualizzazione delle informazioni del prodotto passatogli come parametro
    public void setPage(Prodotto p) {
        removeAll();
        JLabel img = new JLabel(p.getImmagine());
        layout.putConstraint(SpringLayout.NORTH, img, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 10 / 100, SpringLayout.NORTH, this);
        add(img);
        
        JLabel name = new JLabel(p.getNome());
        name.setFont(new Font("Serif", Font.BOLD, 35));
        layout.putConstraint(SpringLayout.NORTH, name, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 6 / 100, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, name, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 30 / 100, SpringLayout.WEST, this);
        add(name);
        
        JTextArea description = new JTextArea(p.getDescrizione());
        description.setEditable(false);
        description.setLineWrap(true);
        description.setPreferredSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 50 / 100, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 10 / 100));
        description.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
        layout.putConstraint(SpringLayout.NORTH, description, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 11 / 100, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, description, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 30 / 100, SpringLayout.WEST, this);
        add(description);
        
        JLabel cost = new JLabel("Prezzo: " + p.getPrezzo() + "€");
        cost.setFont(new Font("Serif", Font.ROMAN_BASELINE, 32));
        cost.setForeground(new Color(179, 0, 0));
        layout.putConstraint(SpringLayout.NORTH, cost, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 20 / 100, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, cost, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 30 / 100, SpringLayout.WEST, this);
        add(cost);
        
        buy.setName(p.getNome());
        buy.setCursor(new Cursor(12));
        ImageIcon icon = new ImageIcon("icone" + File.separator + "AddToCart.png");
        Image newimg = icon.getImage().getScaledInstance((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 3 / 100, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 3 / 100,  java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        buy.setIcon(icon);
        buy.setActionCommand("Buy");
        buy.addActionListener(this);
        layout.putConstraint(SpringLayout.NORTH, buy, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 24 / 100, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, buy, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 30 / 100, SpringLayout.WEST, this);
        add(buy);
        if(Utenza.getCurrentUtente() == null)
            buy.setVisible(false);
    }
    
    //metodo che rende visibile il bottone per l'aggiunta al carrello e che sottintende l'autenticazione verificata un utente
    public void setButtonVisible() {
        buy.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "Buy":
                String value;
                if((value = JOptionPane.showInputDialog(null, "Qunati ne vuoi aggiungere al carrello?", "Hai selezionato un elemento", JOptionPane.PLAIN_MESSAGE)) != null) {
                    try {
                        int v = Integer.valueOf(value);
                        if(Utenza.getCurrentUtente().getSaldo() >= Negozio.getProdotto(((JButton)e.getSource()).getName()).getPrezzo()) {
                            Utenza.getCurrentUtente().removeSaldo(Negozio.getProdotto(((JButton)e.getSource()).getName()).getPrezzo());
                            for(int i=0; i<v; i++) {
                                Utenza.getCurrentUtente().addToCart(Negozio.getProdotto(((JButton)e.getSource()).getName()));
                            }
                        }else {
                            JOptionPane.showMessageDialog(null, "Saldo insufficiente!", "Saldo mancante", JOptionPane.ERROR_MESSAGE);
                        }
                    }catch(NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Hai inserito un valore errato!", "Valore non riconosciuto", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
        }
    }
    
}
