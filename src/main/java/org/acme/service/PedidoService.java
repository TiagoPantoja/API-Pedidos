package org.acme.service;

import org.acme.dto.PedidoDTO;
import org.acme.model.Cliente;
import org.acme.model.Pedido;
import org.acme.repository.PedidoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class PedidoService {

    @Inject
    PedidoRepository pedidoRepository;

    @Transactional
    public Pedido criarPedido(PedidoDTO pedidoDTO) {
        if (pedidoRepository.findByNumeroControle(pedidoDTO.numeroControle).isPresent()) {
            throw new WebApplicationException("Número de controle já cadastrado", 400);
        }

        Pedido pedido = new Pedido();
        pedido.numeroControle = pedidoDTO.numeroControle;
        pedido.dataCadastro = pedidoDTO.dataCadastro != null ? pedidoDTO.dataCadastro : LocalDate.now();
        pedido.nome = pedidoDTO.nomeProduto;
        double desconto = calcularDesconto(pedido.quantidade, pedido.valor);
        pedido.valorTotal = (pedido.quantidade * pedido.valor) - desconto;
        pedido.cliente = Cliente.findById(pedidoDTO.codigoCliente);

        if (pedido.cliente == null) {
            throw new WebApplicationException("Cliente não encontrado", 404);
        }

        pedidoRepository.persist(pedido);
        return pedido;
    }

    private double calcularDesconto(int quantidade, double valorUnitario) {
        if (quantidade >= 10) {
            return quantidade * valorUnitario * 0.10;
        } else if (quantidade > 5) {
            return quantidade * valorUnitario * 0.05;
        } else {
            return 0;
        }
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.listAll();
    }
}
