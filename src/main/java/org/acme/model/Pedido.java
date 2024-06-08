package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Pedido extends PanacheEntity {
    @Column(nullable = false, unique = true)
    public String numeroControle;

    @Column
    public LocalDate dataCadastro;

    @Column(nullable = false)
    public String nome;

    @Column(nullable = false)
    public double valor;

    @Column(nullable = false)
    public int quantidade;

    @Column(nullable = false)
    public double valorTotal;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    public Cliente cliente;

    public Pedido() {
    }

    public Pedido(String numeroControle, LocalDate dataCadastro, String nome, double valor, int quantidade, double valorTotal, Cliente cliente) {
        this.numeroControle = numeroControle;
        this.dataCadastro = dataCadastro;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.cliente = cliente;
    }

    public String getNumeroControle() {
        return numeroControle;
    }

    public void setNumeroControle(String numeroControle) {
        this.numeroControle = numeroControle;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Pedido{" +
            "numeroControle='" + numeroControle + '\'' +
            ", dataCadastro=" + dataCadastro +
            ", nome='" + nome + '\'' +
            ", valor=" + valor +
            ", quantidade=" + quantidade +
            ", valorTotal=" + valorTotal +
            ", cliente=" + cliente +
            '}';
    }
}
