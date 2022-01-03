package br.com.evieira.desafioDev.model.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CNAB")
public class Cnab {

    public Cnab() {
        super();
    }

    public Cnab(final Integer tipo, final LocalDateTime dtTransacao, final Float valor, final String cpf,
                final String cartao, final String nmDono, final String nmLoja) {
        super();
        this.tipo = tipo;
        this.dtTransacao = dtTransacao;
        this.valor = valor;
        this.cpf = cpf;
        this.cartao = cartao;
        this.nmDono = nmDono;
        this.nmLoja = nmLoja;
    }

    public Cnab(Integer id, Integer tipo, LocalDateTime dtTransacao, Float valor, String cpf, String cartao, String nmDono, String nmLoja) {
        super();
        this.id = id;
        this.tipo = tipo;
        this.dtTransacao = dtTransacao;
        this.valor = valor;
        this.cpf = cpf;
        this.cartao = cartao;
        this.nmDono = nmDono;
        this.nmLoja = nmLoja;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 1)
    private Integer tipo;

    private LocalDateTime dtTransacao;

    private Float valor;

    @Column(length = 11)
    private String cpf;

    @Column(length = 12)
    private String cartao;

    @Column(length = 14)
    private String nmDono;

    @Column(length = 19)
    private String nmLoja;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(final Integer tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDtTransacao() {
        return dtTransacao;
    }

    public void setDtTransacao(final LocalDateTime dtTransacao) {
        this.dtTransacao = dtTransacao;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(final Float valor) {
        this.valor = valor;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(final String cpf) {
        this.cpf = cpf;
    }

    public String getCartao() {
        return cartao;
    }

    public void setCartao(final String cartao) {
        this.cartao = cartao;
    }

    public String getNmDono() {
        return nmDono;
    }

    public void setNmDono(final String nmDono) {
        this.nmDono = nmDono;
    }

    public String getNmLoja() {
        return nmLoja;
    }

    public void setNmLoja(final String nmLoja) {
        this.nmLoja = nmLoja;
    }
}
