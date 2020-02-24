

/**
 * Tipul de cache unde se elimina obiectul cel mai demult introdus.
 * Contine metodele de stergere, eliberare, printare si adaugare in cache.
 * @author taner
 */
public class FIFOCache implements Cache{
    
    Subscriptie [] memCache; //asta reprezinta memoria cache
    int nrMaxCache, nrCrtCache = 0;
    
    /**
     * Seteaza marimea cache si initializeaza vectorul care o reprezinta.
     * @param nrMaxCache
     */
    public FIFOCache(int nrMaxCache) {
        
        this.nrMaxCache = nrMaxCache;
        memCache = new Subscriptie [nrMaxCache];
        
    }
    
    @Override
    public void dupliCache(String nume){
        
        for (int i = 0; i < nrCrtCache; i++) {
            
            if (memCache[i].nume.equals(nume)) {
                //sterge elementul gasit si le aduce pe toate cele din dreapta
                //cu o pozitie mai in stanga -> vor ramane in ordinea vechimii.
                for (int j = i; j < nrCrtCache - 1; j++) {
                    
                    memCache[j] = memCache[j + 1];
                    
                }
                
                this.nrCrtCache--;
                
                return;
            }
        }
        
    }
    
    @Override
    public void remove() {
        
        this.nrCrtCache--;
        //datorita implementarii, ultimul element e mereu cel mai demult introd.
    }
    
    
    @Override
    public int add (Subscriptie x) {
        
        x.decrement(); //scade numarul de subscriptii.
        
        for (int i = 0; i < this.nrCrtCache; i++) {
            
            if (memCache[i].nume.equals(x.nume)) {
                //returneaza 0 daca era deja in cache
                return 0;
                
            }
        }
        
        if (this.nrCrtCache == this.nrMaxCache) {
            //elibereaza memoria daca este plina.
            remove();
            
        }
        
        this.nrCrtCache++;
        //aici se ajunge daca obiectul nu era deja in cache.
        for (int i = this.nrCrtCache - 1; i > 0; i--) {
            //tot vectorul e mutat o pozitie la dreapta.
            memCache[i] = memCache[i - 1];
            
        }
        //noul element e inserat pe prima pozitie -> vechime crescatoare.
        memCache[0] = x;
        //returneaza 1 daca nu era deja in cache, dar a fost adaugat
        return 1;

    }
}
