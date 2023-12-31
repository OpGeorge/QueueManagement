package presentation;

import bll.ClientLogic;
import bll.OrderLogic;
import model.Orders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrderFrame extends JFrame {

    JPanel buttonGrid = new JPanel(new GridLayout(2,3));
    JButton buttonSelId = new JButton("SelectByID");
    JButton buttonInsert = new JButton("Insert");
    JButton buttonUpdate = new JButton("Update");
    JButton buttonDelete = new JButton("Delete");
    JButton buttonSelA = new JButton("Select all");



    JLabel id = new JLabel("ID");
    JTextField textid = new JTextField();

    JLabel orderName = new JLabel("Order");
    JTextField textOrderName = new JTextField("order");
    JLabel productId = new JLabel("Product ID");
    JTextField textProductId = new JTextField("-1");
    JLabel clientId = new JLabel("Client ID");
    JTextField textClientId = new JTextField("-1");
    JLabel cantitate = new JLabel("Cantitate");
    JTextField textCantitate = new JTextField("-1");
    public OrderFrame() {
        setTitle("Order");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setSize(700,650);
        setResizable(false);
        setLocationRelativeTo(null);


        orderName.setBounds(50,50,150,50);
        textOrderName.setBounds(150,65,150,25);


        productId.setBounds(50,80,150,50);
        textProductId.setBounds(150,95,150,25);

        clientId.setBounds(50,110,150,50);
        textClientId.setBounds(150,125,150,25);


        cantitate.setBounds(50,140,150,50);
        textCantitate.setBounds(150,155,150,25);

        id.setBounds(155,260,150,50);
        textid.setBounds(170,275,70,25);

        buttonGrid.setBounds(170,300,300,100);

        buttonGrid.add(buttonSelId);
        buttonGrid.add(buttonInsert);
        buttonGrid.add(buttonUpdate);
        buttonGrid.add(buttonDelete);
        buttonGrid.add(buttonSelA);



        buttonSelId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Orders orders = new Orders(textOrderName.getText(),Integer.parseInt(textProductId.getText()),Integer.parseInt(textClientId.getText()),Integer.parseInt(textCantitate.getText()));
                OrderLogic o = new OrderLogic();
                ArrayList<Orders> arr = new ArrayList<Orders>();

                try{textid.getText();}
                catch(Exception ex)
                {
                    throw new IllegalArgumentException("Lipseste ID-ul");
                }

                arr.add(o.selectOrder(orders,Integer.parseInt(textid.getText())));
                OrderTable table = new OrderTable(arr);

            }
        });
        buttonInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Orders orders = new Orders(textOrderName.getText(),Integer.parseInt(textProductId.getText()),Integer.parseInt(textClientId.getText()),Integer.parseInt(textCantitate.getText()));
                OrderLogic o = new OrderLogic();
                o.insertOrder(orders,Integer.parseInt(textProductId.getText()));

            }
        });

        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Orders orders = new Orders(textOrderName.getText(),Integer.parseInt(textProductId.getText()),Integer.parseInt(textClientId.getText()),Integer.parseInt(textCantitate.getText()));
                OrderLogic o = new OrderLogic();
                o.updateOrder(orders,Integer.parseInt(textid.getText()));

            }
        });

        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Orders orders = new Orders(textOrderName.getText(),Integer.parseInt(textProductId.getText()),Integer.parseInt(textClientId.getText()),Integer.parseInt(textCantitate.getText()));
                OrderLogic o = new OrderLogic();
                o.deleteOrder(orders,Integer.parseInt(textid.getText()));
            }
        });

        buttonSelA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Orders orders = new Orders(textOrderName.getText(),Integer.parseInt(textProductId.getText()),Integer.parseInt(textClientId.getText()),Integer.parseInt(textCantitate.getText()));
                OrderLogic o = new OrderLogic();
                OrderTable table = new OrderTable(o.findAllOrders());
            }
        });

        add(orderName);
        add(textOrderName);
        add(productId);
        add(textProductId);
        add(clientId);
        add(textClientId);
        add(cantitate);
        add(textCantitate);
        add(id);
        add(textid);
        add(buttonGrid);


        setVisible(true);
    }


}
