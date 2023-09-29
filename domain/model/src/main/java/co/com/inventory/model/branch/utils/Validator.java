package co.com.inventory.model.branch.utils;

import java.util.Objects;

public class Validator {
    public static String notNullValidate(Object o) {
        try {
            Objects.requireNonNull(o);
        }catch (NullPointerException e){
            throw new NullPointerException("El campo " + o.toString() + " branchName no puede ser nulo");
        }
        return o.toString();

    }
}
