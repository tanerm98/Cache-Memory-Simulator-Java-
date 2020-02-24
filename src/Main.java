
import java.io.*;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Clasa principala.
 * @author taner
 */
public class Main {

    /**
     * Deschide fisierul de input si citeste tot din el;
     * Seteaza tipul de cache cerut;
     * Pentru fiecare comanda, apeleaza metoda corespunzatoare;
     * Apeleaza metoda care inchide fisierul de output, deschis in alta clasa.
     * 
     * @param args
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        //deschiderea fisierelor de Input
        File input = new File(args[0]);
        Scanner in = new Scanner(input);
        
        String metoda = in.nextLine();
        int nrMaxCache = in.nextInt();
        int nrComenzi = in.nextInt();
        
        //memoria principala si cache
        int x = 255;
        Memorie princ = new Memorie(x, args[1]);
        Cache cach;
        
        //stabilirea tipului de cache utilizat
        if (metoda.equals("LRU")) {
            
            cach = new LRUCache(nrMaxCache);
            
        } else if (metoda.equals("FIFO")){
            
            cach = new FIFOCache(nrMaxCache);
            
        } else {
            
            cach = new LFUCache(nrMaxCache);
        }
        
        String comanda, nume, dump;
        int PR = -1, B; //retinerea nr de subscriptii
        
        //citirea comenzilor
        while (in.hasNextLine()) {
            
            comanda = in.next();
            nume = in.next();

            if (in.hasNextInt()) {
                
                B = in.nextInt();
                PR = -1;
                
                if (in.hasNextInt()) {  
                    
                    PR = in.nextInt();
                    
                }
                
                //se ajunge aici numai daca comanda este ADD
                //executarea operatiei de add in memoria principala
                x = princ.addMem(nume, B, PR);
                //x memoreaza daca obiectul exista deja in memorie
                if (x == 1) {

                    cach.dupliCache(nume); /*dupa ce s-a sters duplicatul din
                                             memorie, se sterge si din cache*/
                }
                
            } else {
                
                //aici se ajunge cand comanda este GET
                princ.get(nume, cach);
                
            }
            
            dump = in.nextLine();
            //se citeste ENTER-ul de la sfarsitul liniei
            
        }
        
        princ.inchideOutput(); //se inchide fisierul de output
        
    }
    
}
