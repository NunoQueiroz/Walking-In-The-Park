package org.academiadecodigo.haltistas.halflifeminus3;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuView extends JFrame {

    private JLabel localhostLabel= new JLabel("Insert IP Number:");
    private JTextField localhost = new JTextField(15);
    private JLabel portLabel = new JLabel("Insert Port Number:");
    private JTextField port = new JTextField(15);
    private JButton button = new JButton("START");


    public MenuView() {

        JPanel menuPanel = new JPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(200, 200);

        menuPanel.add(localhostLabel);
        menuPanel.add(localhost);
        menuPanel.add(portLabel);
        menuPanel.add(port);
        menuPanel.add(button);

        this.add(menuPanel);

    }

    public String getLocalhost() {
        return localhost.getText();
    }

    public int getPort() {
        return Integer.parseInt(port.getText());
    }


    public void setButton(int data) {

    }

    public void addButtonListener(ActionListener addButtonListener) {
        button.addActionListener(addButtonListener);
    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }
}


