

package sequencia;

/**
 *
 * @author Diego
 */



public class SequenciaFasta {
    
    
     private String sequencia;
     private  String nomeSequencia;
    

    public SequenciaFasta(String sequencia, String nomeSequencia) {
        this.sequencia = sequencia;
        this.nomeSequencia = nomeSequencia;
    }

    

    public String getSequencia() {
        return sequencia;
    }

    public void setSequencia(String sequencia) {
        this.sequencia = sequencia;
    }

    public String getNomeSequencia() {
        return nomeSequencia;
    }

    public void setNomeSequencia(String nomeSequencia) {
        this.nomeSequencia = nomeSequencia;
    }
   
    
    
}
