

/**
 * Clasa parinte pentru subscriptii.
 * @author taner
 */
public abstract class Subscriptie {

    public String nume;
    
    public Subscriptie() {
    }
    
    /**
     * Seteaza numele obiectului care are subscriptia.
     * @param nume
     */
    protected Subscriptie(String nume) {
        this.nume = nume;
    }

    /**
     * Decrementeaza numarul de subscriptii cel mai important al obiectului.
     */
    public void decrement() {
    }
    
    /**
     * Returneaza numele celei mai importante subscriptii valide.
     * @return
     */
    public String getType() {
        return "";
    }
    
}
