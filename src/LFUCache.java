
/**
 * Tipul de cache unde se elimina obiectul cel mai putin folosit.
 * Contine metodele de stergere, eliberare, printare si adaugare in cache.
 * @author taner
 */
public class LFUCache implements Cache{
    
    Subscriptie [] memCache; //asta reprezinta memoria cache
    int nrMaxCache, nrCrtCache = 0;
    int [] contor; //vector auxiliar care retine numarul de accesari
    
    /**
     * Seteaza marimea cache si initializeaza vectorul care o reprezinta.
     * Initializeaza vectorul in care se retine nr de accesari.
     * @param nrMaxCache
     */
    public LFUCache(int nrMaxCache) {
        
        this.nrMaxCache = nrMaxCache;
        memCache = new Subscriptie [nrMaxCache];
        contor = new int[nrMaxCache];
        
        for (int i = 0; i < nrMaxCache; i++) {
            
            contor[i] = 0;
            
        }
    }
    
    @Override
    public void dupliCache(String nume){
        
        for (int i = 0; i < nrCrtCache; i++) {
            
            if (memCache[i].nume.equals(nume)) {
                //sterge elementul gasit si le aduce pe toate cele din dreapta
                //cu o pozitie mai in stanga -> vor ramane in ordinea vechimii.
                for (int j = i; j < nrCrtCache - 1; j++) {
                    
                    memCache[j] = memCache[j + 1];
                    contor[j] = contor[j + 1];
                    
                }
                
                this.nrCrtCache--;
                
                return;
            }
        }
        
    }
    
    @Override
    public void remove() {
        
        int min = 999999999;
        int poz = 0;
        
        for (int i = 0; i < nrCrtCache; i++) {
            
            if (contor[i] <= min) {
                
                min = contor[i];
                poz = i;
                
            }
        }
        //retine in poz cel mai vechi element cu numarul minim de accesari.
        dupliCache(memCache[poz].nume);
        
    }
    
    
    @Override
    public int add (Subscriptie x) {
        
        x.decrement(); //scade numarul de subscriptii.
        
        for (int i = 0; i < this.nrCrtCache; i++) {
            
            if (memCache[i].nume.equals(x.nume)) {
                 //gaseste obiectul deja in cache
                int accesari = contor[i] + 1;
                
                for (int j = i; j > 0; j--) {
                    //aduce obiectul accesat pe prima pozitie;
                    memCache[j] = memCache[j - 1];
                    contor[j] = contor[j - 1];
                    //timestampurile obiectelor raman in ordine crescatoare.
                }
                
                memCache[0] = x;
                contor[0] = accesari;
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
            contor[i] = contor[i - 1];
            
        }
        //noul element e inserat pe prima pozitie -> timestamp crescator.
        memCache[0] = x;
        contor[0] = 1;
        //returneaza 1 daca nu era deja in cache, dar a fost adaugat
        return 1;

    }
        
}