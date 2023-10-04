package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.exceptions.BlankStringException;

import java.util.Objects;

public class BranchLocation {
    private String branchCountry;
    private String branchCity;

    public BranchLocation() {
    }

    public BranchLocation(String branchCountry, String branchCity) {
        Objects.requireNonNull(branchCountry,"El campo branchCountry no puede ser nulo");
        Objects.requireNonNull(branchCity,"El campo branchCity no puede ser nulo");

        if(branchCountry.isBlank()){
            throw new BlankStringException("El campo branchCountry no puede estar vacío");
        }
        if(branchCity.isBlank()){
            throw new BlankStringException("El campo branchCity no puede estar vacío");
        }


        this.branchCountry = branchCountry;
        this.branchCity = branchCity;
    }

    public String getBranchCountry() {
        return branchCountry;
    }

    public String getBranchCity() {
        return branchCity;
    }

}


