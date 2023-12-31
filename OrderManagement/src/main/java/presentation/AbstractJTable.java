package presentation;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

public class AbstractJTable<T> extends JFrame {

    JTable table;

    public AbstractJTable(ArrayList<T> t)
    {

        int lenght = t.get(0).getClass().getDeclaredFields().length;
        String[] Col=new String[lenght];
        String[][] dateTabel = new String[t.size()][lenght];

        Field[] colnames =  t.get(0).getClass().getDeclaredFields();
        for(int i=0 ; i<lenght; i++) {
            Col[i] = colnames[i].getName();
        }

        int i =0;
        for(T tItr:t)
        {
            Field[] field = tItr.getClass().getDeclaredFields();
            for(int j = 0; j<lenght;j++)
            {
                field[j].setAccessible(true);
                try{
                    dateTabel[i][j]= field[j].get(tItr).toString();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            i++;
        }

        table = new JTable(dateTabel,Col);
        table.setBounds(100,100,500,500);
        table.setRowHeight(20);
        add(new JScrollPane(table));
        setTitle(t.get(0).getClass().getName());
        setSize(700,650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
