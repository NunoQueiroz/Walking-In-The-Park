package org.academiadecodigo.haltistas.halflifeminus3;

import org.academiadecodigo.haltistas.halflifeminus3.Client.Client;
import org.academiadecodigo.simplegraphics.graphics.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameMenu implements ButtonListener {

    private TextField textField = new TextField(15, "ip");
    private Menu menu = new Menu(200, 200);
    private Client client;
    private Game game;

    public GameMenu() {

    }

    public void init() {

        this.menu.addLabel(new Label("Insert IP Number: "));
        this.menu.addTextField(textField);
        Button button = new Button("START");
        this.menu.addButton(button);
        this.menu.addButtonListener(this);
        this.menu.show();

    }

    @Override
    public void buttonPressed(ButtonEvent buttonEvent) {

        Pattern p = Pattern.compile("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
        Matcher m = p.matcher(textField.getText());

        if (!m.matches()) {

            this.menu.displayErrorMessage("Invalid format");
            return;

        }


        game = new Game(textField.getText());
        this.menu.hide();
        game.start();


        System.out.println(textField.getText());

    }

}
