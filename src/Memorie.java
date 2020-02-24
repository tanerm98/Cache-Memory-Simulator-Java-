
import java.io.*;
import static java.lang.System.out;

/**
 * Contine vectorul ce reprezinta memoria principala;
 * Retine numarul de elemente din memoria principala in indice;
 * Deschide si scrie in fisierul de output;
 * Contine metodele de adaugare in memoria principala si in cache.
 * @author taner
 */
public class Memorie {
    
    Subscriptie [] memPrinc;
    int indice, pozitie;
    Writer out;
    
    /**
     * Deschide fisierul de input;
     * Initializeaza memorie principala;
     * Seteaza nr de elemente la 0.
     * @param x
     * @param output
     * @throws IOException
     */
    public Memorie(int x, String output) throws IOException {
        
        this.out = new FileWriter(output);
        
        this.memPrinc = new Subscriptie [x];
        
        this.indice = this.pozitie = 0;
        
    }
    
    /**
     * Adauga obiect nou in memoria principala;
     * Cauta obiectul de adaugat in memorie;
     * Daca este, il suprascrie si returneaza 1 pentru a fi sters si din cache;
     * In functie de nr de accesari, obiectul e Basic sau Premium.
     * @param nume
     * @param B
     * @param PR
     * @return
     */
    public int addMem (String nume, int B, int PR) {
        
        int duplicat = 0; //retine daca elementul trb sters din cache sau nu
        this.pozitie = this.indice;
        
        for (int i = 0; i < indice; i++) {
            
            if (this.memPrinc[i].nume.equals(nume)) {
                
                this.pozitie = i; //se retine pozitia de suprascriere.
                duplicat = 1;
                break;
                    
            }
        }
                
        if (PR > -1) {
            //PR > -1 daca obiectul e premium
            this.memPrinc[this.pozitie] = new Premium(nume, B, PR);
                
        } else {
                
            this.memPrinc[this.pozitie] = new Basic (nume, B);
                
        }
                
        if (this.pozitie == this.indice) {
            //daca obiectul nu era in memorie, se creste nr de obiecte.
            this.indice++;
                
        }
            
        return duplicat;
        
    }
    
    /**
     * Apeleaza obiect din cache;
     * Daca nu exista, afiseaza 2;
     * Daca exista, decrementeaza nr de subscriptii;
     * Afla cea mai importanta subscriptie valida si daca era in cache sau nu.
     * Scrie datele aflat in output.
     * @param nume
     * @param cach
     * @throws IOException
     */
    public void get (String nume, Cache cach) throws IOException {
        
        int gasitInMemorie = 0; //retine daca ob apelat e in memoria principala.
        
        for (int i = 0; i < this.indice; i++) {
            
            if (memPrinc[i].nume.equals(nume)) {
                
                gasitInMemorie = 1;
                //afla care e cea mai importanta subscriptie valida.
                String subscript = memPrinc[i].getType();
                //afla daca era sau nu deja in cache.
                int x = cach.add(memPrinc[i]);
                //scrie in fisierul de output cele 2 date aflat anterior.
                out.write(String.format("%d %s\n", x, subscript));
                
                return;
                
            }
        }
        
        if (gasitInMemorie == 0) {
            //afiseaza 2 daca obiectul apelat nu e in memoria principala.
            out.write("2\n");
            
        }
    }
    
    /**
     * Inchide fisierul de output.
     * @throws IOException
     */
    public void inchideOutput() throws IOException {
        
        out.flush();
        out.close();
        
    }
}