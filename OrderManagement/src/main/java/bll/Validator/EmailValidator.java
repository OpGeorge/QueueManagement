package bll.Validator;

import model.Clients;

import java.util.regex.Pattern;

public class EmailValidator implements Validator<Clients> {

    private static String emailPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    @Override
    public void validate(Clients clients) {
        Pattern pattern = Pattern.compile(emailPattern);
        if(!pattern.matcher(clients.getEmail()).matches())
        {
            throw new IllegalArgumentException("Wrong email");
        }
    }
}
