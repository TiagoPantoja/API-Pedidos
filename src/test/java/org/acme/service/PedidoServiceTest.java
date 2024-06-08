package org.acme.service;

import org.acme.dto.PedidoDTO;
import org.acme.model.Cliente;
import org.acme.model.Pedido;
import org.acme.repository.PedidoRepository;

import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@QuarkusTest
public class PedidoServiceTest {

    @InjectMocks
    PedidoService pedidoService;

    @Mock
    PedidoRepository pedidoRepository;

    @Mock
    Cliente clienteMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Transactional
    public void testCriarPedido_sucesso() {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.numeroControle = "123";
        pedidoDTO.nomeProduto = "Produto Teste";
        pedidoDTO.valorUnitario = 100.0;
        pedidoDTO.quantidade = 10;
        pedidoDTO.codigoCliente = 1;

        when(pedidoRepository.findByNumeroControle(any())).thenReturn(Optional.empty());
        when(Cliente.findById(any())).thenReturn(clienteMock);

        Pedido pedido = pedidoService.criarPedido(pedidoDTO);

        assertNotNull(pedido);
        assertEquals("123", pedido.getNumeroControle());
        assertEquals("Produto Teste", pedido.getNome());
        assertEquals(100.0, pedido.getValor());
        assertEquals(10, pedido.getQuantidade());
        assertEquals(900.0, pedido.getValorTotal());
        assertEquals(clienteMock, pedido.getCliente());

        verify(pedidoRepository, times(1)).persist(any(Pedido.class));
    }

    @Test
    @Transactional
    public void testCriarPedido_numeroControleJaCadastrado() {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.numeroControle = "123";
        pedidoDTO.nomeProduto = "Produto Teste";
        pedidoDTO.valorUnitario = 100.0;
        pedidoDTO.quantidade = 10;
        pedidoDTO.codigoCliente = 1;

        when(pedidoRepository.findByNumeroControle(any())).thenReturn(Optional.of(new Pedido()));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            pedidoService.criarPedido(pedidoDTO);
        });

        assertEquals("Número de controle já cadastrado", exception.getMessage());
        verify(pedidoRepository, never()).persist(any(Pedido.class));
    }

    @Test
    @Transactional
    public void testCriarPedido_clienteNaoEncontrado() {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.numeroControle = "123";
        pedidoDTO.nomeProduto = "Produto Teste";
        pedidoDTO.valorUnitario = 100.0;
        pedidoDTO.quantidade = 10;
        pedidoDTO.codigoCliente = 1;

        when(pedidoRepository.findByNumeroControle(any())).thenReturn(Optional.empty());
        when(Cliente.findById(anyInt())).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            pedidoService.criarPedido(pedidoDTO);
        });

        assertEquals("Cliente não encontrado", exception.getMessage());
        verify(pedidoRepository, never()).persist(any(Pedido.class));
    }

    @Test
    @Transactional
    public void testCriarPedido_dataCadastroDefault() {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.numeroControle = "123";
        pedidoDTO.nomeProduto = "Produto Teste";
        pedidoDTO.valorUnitario = 100.0;
        pedidoDTO.quantidade = 2;
        pedidoDTO.codigoCliente = 1;

        when(pedidoRepository.findByNumeroControle(any())).thenReturn(Optional.empty());
        when(Cliente.findById(anyInt())).thenReturn(clienteMock);

        Pedido pedido = pedidoService.criarPedido(pedidoDTO);

        assertNotNull(pedido);
        assertEquals(LocalDate.now(), pedido.getDataCadastro());
    }
}