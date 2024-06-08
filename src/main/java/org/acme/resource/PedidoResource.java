package org.acme.resource;

import org.acme.dto.PedidoDTO;
import org.acme.model.Pedido;
import org.acme.service.PedidoService;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Tag(name = "Pedido Resource", description = "Gerenciamento de pedidos")
public class PedidoResource {

    @Inject
    PedidoService pedidoService;

    @POST
    @Transactional
    @Operation(summary = "Cria um novo pedido", description = "Cria um novo pedido com os dados fornecidos")
    @APIResponse(responseCode = "201", description = "Pedido criado com sucesso", content = @Content(schema = @Schema(implementation = Pedido.class)))
    public Response criarPedido(PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoService.criarPedido(pedidoDTO);
        return Response.status(Response.Status.CREATED).entity(pedido).build();
    }

    @GET
    @Operation(summary = "Lista todos os pedidos", description = "Retorna uma lista de todos os pedidos cadastrados")
    @APIResponse(responseCode = "200", description = "Lista de pedidos", content = @Content(schema = @Schema(implementation = Pedido.class)))
    public List<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    @GET
    @Path("/{numeroControle}")
    @Operation(summary = "Busca um pedido pelo número de controle", description = "Retorna um pedido com o número de controle")
    @APIResponse(responseCode = "200", description = "Pedido encontrado", content = @Content(schema = @Schema(implementation = Pedido.class)))
    @APIResponse(responseCode = "404", description = "Pedido não encontrado")
    public Pedido buscarPorNumeroControle(@PathParam("numeroControle") String numeroControle) {
        return Pedido.find("numeroControle", numeroControle).firstResult();
    }
}
