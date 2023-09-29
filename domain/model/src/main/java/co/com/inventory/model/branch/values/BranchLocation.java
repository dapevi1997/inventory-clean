package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.generic.ValueObject;

public class BranchLocation implements ValueObject<BranchLocation.Propiedades> {
    private String branchCountry;
    private String branchCity;

    public BranchLocation(String branchCountry, String branchCity) {
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


