package menu;

import jdk.dynalink.linker.LinkerServices;
import window.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class MainScene {

    private JPanel menu;
    private JPanel menuButtons;
    private Window window;
    private static final String LOGO = "Game";

    private static MainScene instance;

    private MainScene(Window window) {
        this.menu = new JPanel();
        this.menuButtons = new JPanel();

        this.window = window;
        this.menu.setLayout(new BorderLayout());
        this.menuButtons.setLayout(new GridBagLayout());
        this.initLogo();
        this.initButtons();
    }

    private void initButtons() {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        addButton("Start", constraints, e -> {
            System.out.println("TODO");
        });
        addButton("Exit", constraints, e -> {
            this.window.close();
        });
        this.menu.add(menuButtons, BorderLayout.CENTER);
    }

    private void addButton(String title, GridBagConstraints constraints, ActionListener l) {
        JButton button = new JButton(title);

        button.addActionListener(l);
        button.setFont(new Font("Impact", Font.TYPE1_FONT, 50));
        button.setMargin(new Insets(50, 300, 50, 300));
        menuButtons.add(button, constraints);
    }

    private void initLogo() {
        JLabel logo = new JLabel(LOGO);

        logo.setFont(new Font("Impact", Font.TYPE1_FONT, 300));
        logo.setHorizontalAlignment(JLabel.CENTER);
        logo.setVerticalAlignment(JLabel.CENTER);
        this.menu.add(logo, BorderLayout.NORTH);
    }

    public JPanel getPanel() {
        return this.menu;
    }

    public static MainScene getMainScene(Window window) {
        if (instance == null) {
            instance = new MainScene(window);
        }
        return instance;
    }

}
