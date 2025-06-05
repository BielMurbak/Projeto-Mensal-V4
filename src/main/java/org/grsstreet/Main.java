package org.grsstreet;

import org.grsstreet.view.TelaLogin;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Garante que a interface gráfica será iniciada na thread certa
        SwingUtilities.invokeLater(() -> {
            new TelaLogin().setVisible(true);
        });
    }
}