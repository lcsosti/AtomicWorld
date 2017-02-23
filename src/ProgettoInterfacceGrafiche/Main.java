package ProgettoInterfacceGrafiche;

public class Main{
    
    public static void main(String[] args) {
        AppStyle.setLookAndFeel(AppStyle.getCurrentLookAndFeel());
        Negozio n = new Negozio();
        Utenza u = new Utenza();
        GUI_Overview gui = new GUI_Overview();
    }
    
}
