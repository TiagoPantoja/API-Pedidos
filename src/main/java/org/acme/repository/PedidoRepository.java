package org.acme.repository;

import org.acme.model.Pedido;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {
    public Optional<Pedido> findByNumeroControle(String numeroControle) {
        return find("numeroControle", numeroControle).firstResultOptional();
    }
}
