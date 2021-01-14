/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sequencia;

/**
 *
 * @author Diego
 */
public class Orf {
    
    private int tamanho;
    private int inicio;
    private int frame;
    private int fim;
    private String orf;
                               
    public Orf(int inicio, int frame, int fim, String orf, int tamanho) {
        this.inicio = inicio;
        this.frame = frame;
        this.fim = fim;
        this.orf = orf;
        this.tamanho = tamanho;
    }
    
    
    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public int getFim() {
        return fim;
    }

    public void setFim(int fim) {
        this.fim = fim;
    }

    public String getOrf() {
        return orf;
    }

    public void setOrf(String orf) {
        this.orf = orf;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
    
      
    
    
    
}
