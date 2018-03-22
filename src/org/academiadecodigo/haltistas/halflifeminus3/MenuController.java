package org.academiadecodigo.haltistas.halflifeminus3;

import org.academiadecodigo.haltistas.halflifeminus3.MenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController {

    private MenuView menuView;

    public MenuController(MenuView menuView){

        this.menuView=menuView;

        this.menuView.addButtonListener(new ButtonListener());
    }

    public class ButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent e){

            String localhost;
            int port;

            try{

                localhost = menuView.getLocalhost();
                port= menuView.getPort();

                //menuView.setButton(localhost,port);


            }
            catch(NumberFormatException ex){
                menuView.displayErrorMessage("Please enter a valid IP format");

            }
        }

    }

}
