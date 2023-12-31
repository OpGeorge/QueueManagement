package bll.Validator;

import model.Clients;

import java.util.regex.Pattern;

public class PhoneNrValidator implements Validator<Clients> {

    private static String nrTelPattern = "^[0-9]{10,}$";

    @Override
    public void validate(Clients clients) {
        Pattern pattern = Pattern.compile(nrTelPattern);
        if(!pattern.matcher(clients.getNrTel()).matches())
        {
            throw new IllegalArgumentException("Wrong phone number");
        }
    }
}

