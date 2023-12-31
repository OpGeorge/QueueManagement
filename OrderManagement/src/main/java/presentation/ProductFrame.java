package presentation;

import bll.OrderLogic;
import bll.ProductLogic;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProductFrame extends JFrame {

    JPanel buttonGrid = new JPanel(new GridLayout(2,3));
    JButton buttonSelId = new JButton("SelectByID");
    JButton buttonInsert = new JButton("Insert");
    JButton buttonUpdate = new JButton("Update");
    JButton buttonDelete = new JButton("Delete");
    JButton buttonSelA = new JButton("Select all");



    JLabel id = new JLabel("ID");
    JTextField textid = new JTextField();

    JLabel cantitate = new JLabel("Cantitate");
    JTextField textCantitate = new JTextField("-1");
    JLabel pret = new JLabel("Pret");
    JTextField textPret = new JTextField("-1");
    JLabel numeProdus = new JLabel("Nume Produs");
    JTextField textNumeProdus = new JTextField("exemplu");

    public ProductFrame() {
        setTitle("Product");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setSize(700,650);
        setResizable(false);
        setLocationRelativeTo(null);


        cantitate.setBounds(50,50,150,50);
        textCantitate.setBounds(150,65,150,25);


        pret.setBounds(50,80,150,50);
        textPret.setBounds(150,95,150,25);

        numeProdus.setBounds(50,110,150,50);
        textNumeProdus.setBounds(150,125,150,25);




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
                Product products = new Product(Integer.parseInt(textCantitate.getText()),Integer.parseInt(textPret.getText()),textNumeProdus.getText());
                ProductLogic o = new ProductLogic();
                ArrayList<Product> arr = new ArrayList<Product>();


                try{textid.getText();}
                catch(Exception ex)
                {
                    throw new IllegalArgumentException("Lipseste ID-ul");
                }

                arr.add(o.selectProduct(products,Integer.parseInt(textid.getText())));
                ProductTable table = new ProductTable(arr);

            }
        });
        buttonInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product products = new Product(Integer.parseInt(textCantitate.getText()),Integer.parseInt(textPret.getText()),textNumeProdus.getText());
                ProductLogic p = new ProductLogic();
                p.insertProduct(products);

            }
        });

        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product products = new Product(Integer.parseInt(textCantitate.getText()),Integer.parseInt(textPret.getText()),textNumeProdus.getText());
                ProductLogic p = new ProductLogic();
                p.updateProduct(products,Integer.parseInt(textid.getText()));

            }
        });

        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product products = new Product(Integer.parseInt(textCantitate.getText()),Integer.parseInt(textPret.getText()),textNumeProdus.getText());
                ProductLogic p = new ProductLogic();
                p.deleteProduct(products,Integer.parseInt(textid.getText()));
            }
        });

        buttonSelA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product products = new Product(Integer.parseInt(textCantitate.getText()),Integer.parseInt(textPret.getText()),textNumeProdus.getText());
                ProductLogic p = new ProductLogic();
                ProductTable table = new ProductTable(p.findAllProducts());
            }
        });

        add(cantitate);
        add(textCantitate);
        add(pret);
        add(textPret);
        add(numeProdus);
        add(textNumeProdus);

        add(id);
        add(textid);
        add(buttonGrid);


        setVisible(true);
    }

}
