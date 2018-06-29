package sax.lab.aws.lambda;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by SalvatoreAngelo.DiSa on 27/06/2018.
 */
public class Customer {

    private String customerId;
    private String cognome;
    private String nome;
    private String numeroCivico;
    private String via;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroCivico() {
        return numeroCivico;
    }

    public void setNumeroCivico(String numeroCivico) {
        this.numeroCivico = numeroCivico;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    @Override
    public String toString() {
        return  "customerId: " + customerId + " cognome: " + cognome + " nome: " + nome + " numeroCivico: "
                + numeroCivico + " via: " + via;
    }
}
