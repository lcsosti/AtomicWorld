package ProgettoInterfacceGrafiche;
//registrazione utente
import java.util.Arrays;
import javax.swing.*;

public class SignUp {
    
    private JTextField name = new JTextField(15);
    private JTextField surname = new JTextField(15);
    private JTextField email = new JTextField(15);
    private JPasswordField password = new JPasswordField(15);
    private JPasswordField repeatpassword = new JPasswordField(15);
    private JPanel panel = new JPanel();
    
    /*
    metodo costruttore che aggiunge al pannello tutti gli elementi sopra riportati. Il pannello sarà poi aggiunto ad una finestra di dialogo adatta alla registrazione dell'utente. 
    Per far si che ciò accada correttamente si deve inserire un nome e un cognome di almeno cinque caratteri, una password ed una password ripetuta di almeno cinque caratteri ed uguali tra 
    di loro. 
    Se ciò fallisce verrà comunicato opportunamente tramite finestre di dialogo il perché di tutto ciò
    */
    public SignUp() {
        panel.add(new JLabel("Name:"));
        panel.add(name);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Surname:"));
        panel.add(surname);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("E-mail:"));
        panel.add(email);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Password:"));
        panel.add(password);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Repeat Password:"));
        panel.add(repeatpassword);

        if (JOptionPane.showConfirmDialog(null, panel, "Atomic World", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            if(!Utenza.exist(email.getText())) {
                if(name.getText().length() > 0 && surname.getText().length() > 0 && password.getPassword().length >= 5) {
                    if(Arrays.equals(password.getPassword(), repeatpassword.getPassword())) {
                        Utenza.newUtente(new Utente(name.getText(), surname.getText(), email.getText(), String.valueOf(password.getPassword()), 0));
                        JOptionPane.showMessageDialog(null, "Utente creato con successo!", "Benvenuto!", JOptionPane.PLAIN_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(null, "Le password e la password ripetuta devono essere identiche!", "Errore", JOptionPane.ERROR_MESSAGE);
                        new SignUp();
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Il campo nome, cognome e password devono contenere almeno 5 caratteri!", "Errore", JOptionPane.ERROR_MESSAGE);
                    new SignUp();
                }
            }else {
                JOptionPane.showMessageDialog(null, "Utente già esistente!", "Errore", JOptionPane.ERROR_MESSAGE);
                new SignUp();
            }
        }
    }
    
}
