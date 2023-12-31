package presentation;

import bll.ClientLogic;
import model.Clients;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClientFrame extends JFrame {

    JPanel buttonGrid = new JPanel(new GridLayout(2,3));
    JButton buttonSelId = new JButton("SelectByID");
    JButton buttonInsert = new JButton("Insert");
    JButton buttonUpdate = new JButton("Update");
    JButton buttonDelete = new JButton("Delete");
    JButton buttonSelA = new JButton("Select all");



    JLabel id = new JLabel("ID");
    JTextField textid = new JTextField();

    JLabel numePrenume = new JLabel("Nume Prenume");
    JTextField textNumePrenume = new JTextField("nume_prenume");
    JLabel adresa = new JLabel("Adresa");
    JTextField textAdresa = new JTextField("adr");
    JLabel nrTel = new JLabel("Numar telefon");
    JTextField textNrTel = new JTextField("0123456789");
    JLabel email = new JLabel("Email");
    JTextField textEmail = new JTextField("e@example.com");
    public ClientFrame() {
        setTitle("Clinet");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setSize(700,650);
        setResizable(false);
        setLocationRelativeTo(null);


        numePrenume.setBounds(50,50,150,50);
        textNumePrenume.setBounds(150,65,150,25);


        adresa.setBounds(50,80,150,50);
        textAdresa.setBounds(150,95,150,25);

        nrTel.setBounds(50,110,150,50);
        textNrTel.setBounds(150,125,150,25);


        email.setBounds(50,140,150,50);
        textEmail.setBounds(150,155,150,25);

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
                Clients client = new Clients(textNumePrenume.getText(),textAdresa.getText(),textNrTel.getText(),textEmail.getText());
                ClientLogic c = new ClientLogic();
                ArrayList<Clients> arr = new ArrayList<Clients>();


                try{textid.getText();}
                catch(Exception ex)
                {
                    throw new IllegalArgumentException("Lipseste ID-ul");
                }

                    arr.add(c.selectClinet(client,Integer.parseInt(textid.getText())));
                    ClientTable table = new ClientTable(arr);




            }
        });
        buttonInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clients client = new Clients(textNumePrenume.getText(),textAdresa.getText(),textNrTel.getText(),textEmail.getText());
                ClientLogic c = new ClientLogic();
                c.insertClinet(client);
            }
        });

        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clients client = new Clients(textNumePrenume.getText(),textAdresa.getText(),textNrTel.getText(),textEmail.getText());
                ClientLogic c = new ClientLogic();
                c.updateClient(client,Integer.parseInt(textid.getText()));
            }
        });

        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clients client = new Clients(textNumePrenume.getText(),textAdresa.getText(),textNrTel.getText(),textEmail.getText());
                ClientLogic c = new ClientLogic();
                c.deleteClient(client,Integer.parseInt(textid.getText()));
            }
        });

        buttonSelA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clients client = new Clients(textNumePrenume.getText(),textAdresa.getText(),textNrTel.getText(),textEmail.getText());
                ClientLogic c = new ClientLogic();
                ClientTable table = new ClientTable(c.findAllClinets());




            }
        });

        add(numePrenume);
        add(textNumePrenume);
        add(adresa);
        add(textAdresa);
        add(nrTel);
        add(textNrTel);
        add(email);
        add(textEmail);
        add(id);
        add(textid);
        add(buttonGrid);


        setVisible(true);
    }


}
