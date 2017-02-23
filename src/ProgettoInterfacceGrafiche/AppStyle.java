package ProgettoInterfacceGrafiche;
//classe utilizzata per cambiare dinamicamente il look and feel dell'applicazione.
import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class AppStyle extends JFrame implements ActionListener{

    private JPanel jp = new JPanel();//il pannello nel quale vengono aggiunti tutti i suoi componenti
    private JLabel title = new JLabel("Imposta l'aspetto dell'applicazione");//etichetta utilizzata per impostare il titolo della finestra
    private ButtonGroup bg = new ButtonGroup();//oggetto utilizzato per racchiudere tutti i radioButton e permettere la selezione esclusiva di uno di essi
    
    //costruttore della classe inserisce il titolo nel pannello, aggiunge tutti i radioButton nel pannello e nel gruppo di bottoni
    //e tutte le specifiche per la finestra, come il layout, la visibilità e la sua chiusura
    public AppStyle() {
        title.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
        jp.add(title);
        for(EnumStyle style : EnumStyle.values()) {
            JRadioButton jrb = new JRadioButton(style.toString());
            jrb.addActionListener(this);
            bg.add(jrb);
            jp.add(jrb);
        }
        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
        add(jp);
        pack();
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    //metodo che cambia il look and feel corrente dell'applicazione, se lo trova
    public static void setLookAndFeel(EnumStyle style) {
        try {
            UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.Synthetica" + style + "LookAndFeel");
        }
        catch (Exception e) {
            System.out.println("Look and feel non trovato!");
        }
    }
    
    //metodo che ritorna il look and feel correntemente salvato nel file relativo (style.txt)
    public static EnumStyle getCurrentLookAndFeel() {
        EnumStyle es = null;
        try (BufferedReader in = new BufferedReader(new FileReader("style" + File.separator + "style.txt"))){
            es = EnumStyle.valueOf(in.readLine());
            in.close();
        }catch(Exception e) {
            System.out.println("File non trovato!");
        }
        return es;
    }

    //metodo sovraccaricato per la gestione degli eventi.
    // cambia il look and feel dell'applicazione andando a salvare il look and feel selezionato nel proprio file.
    @Override
    public void actionPerformed(ActionEvent e) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter("style" + File.separator + "style.txt"))){
            out.write(((JRadioButton)e.getSource()).getText());
            out.close();
        }catch(Exception ex) {
            System.out.println("File non trovato!");
        }
    }
}

