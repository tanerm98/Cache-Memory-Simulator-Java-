

/**
 * Sunscriptia premium cu numar limitat de accesari.
 * @author taner
 */
public class Premium extends Basic{
    
    public int nrP;

    public Premium() {
    }
    
    /**
     * Apeleaza constructorul clasei radacina, pentru a seta numele unic.
     * Seteaza numarul de accesari premium si basic.
     * 
     * @param nume
     * @param x
     * @param y
     */
    public Premium(String nume, int x, int y) {
        
        super(nume, x);
        this.nrP = y;
        
    }
    
    @Override
    public void decrement() {
        
        if (this.nrP < 1) {
            
            super.decrement();
            //decrementeaza din Basic daca Premium nu mai este valid
            
        } else {
            
            this.nrP--;
            
        }
    }
    
    @Override
    public String getType() {
        
        if (nrP > 0) {
            
            return "Premium";
            
        } else {
            
            return super.getType();
            //returneaza Basic daca Premium nu mai are accesari
            
        }
    }

}
