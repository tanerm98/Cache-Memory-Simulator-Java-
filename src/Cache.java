

/**
 * Interfata Cache, retine metodele comune.
 * @author taner
 */
public interface Cache {
    
    /**
     * Adauga un element nou din cache, sau il acceseaza daca deja exista.
     * @param x
     * @return
     */
    public int add (Subscriptie x);
    
    /**
     * Sterge un element din cache, ales in functie de tipul de cache folosit.
     */
    public void remove ();
    
    /**
     * Sterge obiectul cu numele specificat din Cache.
     * @param nume
     */
    public void dupliCache(String nume);
    
}