package app;

import view.MainMenuView;

public class App {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new MainMenuView().setVisible(true);
        });
    }
}
