package co.com.inventory.usecase.generic.commands;


public class CreateBranchCommand {
    private String branchName;
    private String branchCountry;
    private String branchCity;

    public CreateBranchCommand() {
    }

    public CreateBranchCommand(String branchName, String branchCountry, String branchCity) {
        this.branchName = branchName;
        this.branchCountry = branchCountry;
        this.branchCity = branchCity;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchCountry() {
        return branchCountry;
    }

    public void setBranchCountry(String branchCountry) {
        this.branchCountry = branchCountry;
    }

    public String getBranchCity() {
        return branchCity;
    }

    public void setBranchCity(String branchCity) {
        this.branchCity = branchCity;
    }
}
