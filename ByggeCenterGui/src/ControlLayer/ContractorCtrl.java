package ControlLayer;
import ModelLayer.Contractor;
import ModelLayer.ContractorContainer;
import ModelLayer.Product;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Public class ContractorCtrl used to manipulate Contractor objects
 */
public class ContractorCtrl extends PersonCtrl{    
    private ArrayList<Contractor> contractors;
    private final String fileName = "contractors.ser";
    private ContractorContainer contCont;
     
    /**
     * Constructor of clas ContractorCtrl
     */
    public ContractorCtrl(){
        contractors = new ArrayList<>();
        contCont = ContractorContainer.getInstance();
        contractors = contCont.getContractors();
    }
    
    public ArrayList<Contractor> getContractors(){
        return contractors;
    }
    
    /**
     *  Creates a new contractor and add it to a file
     */
    public Contractor createContractor(String name,String address, String phone, String email,ArrayList contractors){
        Contractor cont = new Contractor(name,address,phone,email);
        addPerson(fileName,cont,contractors);
        return cont;
    }
    
    /**
     * Return a Contractor object if it was found in the collection null otherwise
     */
    public Contractor findContractor(String id){
        for(Contractor contractor : contractors){
            if(contractor.getID().equals(id)){
                return contractor;
            }
        }
        return null;
    }
    
    public TreeMap<Product,Integer> getReorderFromContractor(Contractor cont){
        return cont.getRequestForReorder();
    }
    
    /** @return the filename where the contractors are saved
     */
    public String getFileName(){
        return fileName;
    }
}
