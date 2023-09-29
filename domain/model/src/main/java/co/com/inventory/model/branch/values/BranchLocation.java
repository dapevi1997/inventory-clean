package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.exceptions.BlankStringException;
import co.com.inventory.model.branch.generic.ValueObject;

import java.util.Objects;

public class BranchLocation implements ValueObject<BranchLocation.Propiedades> {
    private String branchCountry;
    private String branchCity;

    public BranchLocation(String branchCountry, String branchCity) {
        try {
            Objects.requireNonNull(branchCountry);
        }catch (NullPointerException e){
            throw new NullPointerException("El campo branchCountry no puede ser nulo");
        }
        try {
            Objects.requireNonNull(branchCity);
        }catch (NullPointerException e){
            throw new NullPointerException("El campo branchCity no puede ser nulo");
        }
        if(branchCountry.isBlank()){
            throw new BlankStringException("El campo branchCountry no puede estar vacío");
        }
        if(branchCity.isBlank()){
            throw new BlankStringException("El campo branchCity no puede estar vacío");
        }


        this.branchCountry = branchCountry;
        this.branchCity = branchCity;
    }

    @Override
    public Propiedades value() {

        return new Propiedades() {
            @Override
            public String branchCountry() {
                return branchCountry;
            }

            @Override
            public String branchCity() {
                return branchCity;
            }
        };
    }

    public String getBranchCountry() {
        return branchCountry;
    }

    public String getBranchCity() {
        return branchCity;
    }
    public interface Propiedades{
        String branchCountry();
        String branchCity();

    }
}


