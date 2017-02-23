package ProgettoInterfacceGrafiche;
//classe nella quale vengono visualizzate tutte le informazioni relative ad un utente, può essere considerata una classe di riepilogo.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI_Info extends JPanel implements ActionListener{
    
    //costruttore della classe che imposta il suo layout. Inoltre vengono aggiunti tutti i suoi elementi
    public GUI_Info() {
        setLayout(new GridLayout(0,1));
        setPreferredSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 100 / 100, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 89 / 100));
        addElements();
    }
    
    //metodo che aggiunge tutti i componenti per la corretta visualizzazione dei dati di un utente.
    public void addElements() {
        if(Utenza.getCurrentUtente() != null) {
            removeAll();
            JLabel title = new JLabel("Informazioni utente:");
            title.setFont(new Font("Serif", Font.BOLD, 30));
            add(title);
            
            JLabel name = new JLabel("Nome: " + Utenza.getCurrentUtente().getNome());
            name.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
            add(name);
            
            JLabel surname = new JLabel("Cognome:" + Utenza.getCurrentUtente().getCognome());
            surname.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
            add(surname);
            
            JLabel email = new JLabel("Email: " + Utenza.getCurrentUtente().getEmail());
            email.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
            add(email);
            
            JLabel saldo = new JLabel("Saldo: " + Utenza.getCurrentUtente().getSaldo() + "€");
            saldo.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
            add(saldo);
            
            JButton jbSaldo = new JButton("Aggiungi denaro");
            jbSaldo.setCursor(new Cursor(12));
            jbSaldo.setActionCommand("Saldo");
            jbSaldo.addActionListener(this);
            add(jbSaldo);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "Saldo":
                String value;
                if((value = JOptionPane.showInputDialog(null, "Aggiungi denaro", "Saldo utente", JOptionPane.PLAIN_MESSAGE)) != null) {
                    try {
                        Float v = Float.valueOf(value);
                        Utenza.getCurrentUtente().addSaldo(v);
                        addElements();
                        repaint();
                        revalidate();
                    }catch(NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Hai inserito un valore errato!", "Valore non riconosciuto", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
        }
    }
    
}
