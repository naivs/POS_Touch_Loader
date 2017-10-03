package data;

import java.io.Serializable;

public class Customer implements Serializable {

    private Long date;
    private String name;
    private String surname;
    private int companyId;
    private String email;
    private String product;
    private String problem;
    private String solution;
    private boolean status;

    public Customer(){
        this(null, "", "", "", 0, "", "", "", false);
    }

    public Customer(Long date, String name, String surname, String email, int companyId, String product, String problem, String solution, boolean status) {
        this.date = date;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.companyId = companyId;
        this.product = product;
        this.problem = problem;
        this.solution = solution;
        this.status = status;
    }

    public Long getDate(){
        return date;
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public int getCompanyId(){
        return companyId;
    }

    public String getEmail(){
        return email;
    }

    public String getProduct(){
        return product;
    }

    public String getProblem(){
        return problem;
    }

    public String getSolution(){
        return solution;
    }

    public boolean getStatus(){
        return status;
    }

    public void setDate(Long date){
        this.date = date;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public void setCompanyId(int companyId){
        this.companyId = companyId;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setProduct(String product){
        this.product = product;
    }

    public void setProblem(String problem){
        this.problem = problem;
    }

    public void setSolution(String solution){
        this.solution = solution;
    }

    public void setStatus(boolean status){
        this.status = status;
    }
}
