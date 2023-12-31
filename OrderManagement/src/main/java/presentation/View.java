package presentation;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class View extends JFrame{

    JButton buttonClient = new JButton("Client Operations");
    JButton buttonProduct = new JButton("Product Operations");
    JButton buttonOrder = new JButton("Order Operations");

    public View()
    {
        String title = "Order Management";
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(700,650);
        setTitle(title);
        setResizable(false);
        setLocationRelativeTo(null);
        buttonClient.setBounds(225,120,200,50);
        buttonOrder.setBounds(225,180,200,50);
        buttonProduct.setBounds(225,240,200,50);
        add(buttonClient);
        add(buttonOrder);
        add(buttonProduct);
        setVisible(true);


    }

    public void ClientListener(ActionListener l)
    {
        buttonClient.addActionListener(l);
    }
    public void ProductListener(ActionListener l)
    {
        buttonProduct.addActionListener(l);
    }
    public void OrderListener(ActionListener l)
    {
        buttonOrder.addActionListener(l);
    }


}
