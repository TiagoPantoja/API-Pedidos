package org.acme.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDate;

@Schema(name = "PedidoDTO", description = "DTO para criação de um pedido")
public class PedidoDTO {
    @Schema(description = "Número de controle do pedido", required = true)
    public String numeroControle;

    @Schema(description = "Data de cadastro do pedido")
    public LocalDate dataCadastro;

    @Schema(description = "Nome do produto", required = true)
    public String nomeProduto;

    @Schema(description = "Valor unitário do produto", required = true)
    public Double valorUnitario;

    @Schema(description = "Quantidade de produtos")
    public Integer quantidade;

    @Schema(description = "Código do cliente", required = true)
    public int codigoCliente;
}
