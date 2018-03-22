package org.academiadecodigo.haltistas.halflifeminus3;

import org.academiadecodigo.haltistas.halflifeminus3.MenuController;
import org.academiadecodigo.haltistas.halflifeminus3.MenuView;

public class MenuMain {

    public static void main(String[] args) {

        MenuView menuView= new MenuView();

        MenuController controller = new MenuController(menuView);

        menuView.setVisible(true);

    }

}
