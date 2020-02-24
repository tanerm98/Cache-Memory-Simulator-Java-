

/**
 * Subscriptia cea mai neimportanta, nelimitata.
 * @author taner
 */
public class Free extends Subscriptie{

    public Free() {
    }
    
    /**
     * s
     * @param nume
     */
    public Free(String nume){
        super(nume);
    }
    
    @Override
    public void decrement() {
    }
    
    @Override
    public String getType() {
        return "Free";
    }
}
