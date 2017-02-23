package ProgettoInterfacceGrafiche;
//classe impiegata per far accedere un utente, richiamabile dalla barra dei menu
import javax.swing.*;

public class LogIn {
    
    private GUI_Overview frame;
    
    private JTextField email = new JTextField(15);//campo di autenticazione, email
    private JPasswordField password = new JPasswordField(15);//campo di autenticazione, password 
    private JPanel panel = new JPanel();
    
    /*
    metodo costruttore che imposta il valore dell'attributo frame, aggiunge tutti  i componenti relativi l'autenticazione al pannello ed una finestra di dialogo per l'effettiva autenticazione. 
    Se i dati inseriti sono corretti allora il logIn avrà successo, altrimenti verrà comunicato il fallimento grazie ad un'altra finestra di dialogo
    */
    public LogIn(GUI_Overview frame) {
        this.frame = frame;
        panel.add(new JLabel("Email:"));
        panel.add(email);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Password:"));
        panel.add(password);

        if (JOptionPane.showConfirmDialog(null, panel, "Atomic World", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            if(Utenza.getUserData(email.getText(), String.valueOf(password.getPassword()))) {
                Utenza.setCurrentUtente(Utenza.getUtente(email.getText()));
                frame.Logged();
            }else {
                JOptionPane.showMessageDialog(null, "Nome utente o password errati!", "Errore", JOptionPane.ERROR_MESSAGE);
                new LogIn(frame);
            }
        }
    }
    
}
