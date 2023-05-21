/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

/**
 *
 * @author Bruno
 */
public class Pouzece {
    
    private String adresa;
    private int postanskiBroj;
    private String grad;

    public Pouzece() {
    }

    public Pouzece(String adresa, int postanskiBroj, String grad) {
        this.adresa = adresa;
        this.postanskiBroj = postanskiBroj;
        this.grad = grad;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(int postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }
    
    
}
