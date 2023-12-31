package controller;

import model.Product;
import presentation.ClientFrame;
import presentation.OrderFrame;
import presentation.ProductFrame;
import presentation.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    View view = new View();
    public  Controller()
    {
        view.ClientListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ClientFrame cFr = new ClientFrame();
            }
        });

        view.ProductListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductFrame pFr  = new ProductFrame();
            }
        });

        view.OrderListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    OrderFrame oFr = new OrderFrame();
            }
        });
    }
}
