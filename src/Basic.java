

/**
 * Subscriptia de baza, limitata.
 * @author taner
 */
public class Basic extends Free{
    
    public int nrB; //se retine nr de accesari de baza

    public Basic() {
    }
    
    /**
     * Apeleaza constructorul clasei radacina, pentru a seta numele unic.
     * Seteaza numarul de accesari posibile.
     * 
     * @param nume
     * @param x
     */
    public Basic(String nume, int x){
        
        super(nume);
        this.nrB = x;
        
    }
    
    @Override
    public void decrement() {
        
        if (this.nrB < 1) {
            //daca nr de accesari este 0, devine Free.
            super.decrement();
            
        } else {
            
            this.nrB--;
            
        }
    }
    
    @Override
    public String getType() {
        
        if (nrB > 0) {
            
            return "Basic";
            
        } else {
            
            //returneaza Free daca nu mai are accesari basic posibile.
            return super.getType();
            
        }
    }
    
}
