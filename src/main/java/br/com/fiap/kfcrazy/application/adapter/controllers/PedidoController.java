package br.com.fiap.kfcrazy.application.adapter.controllers;

import br.com.fiap.kfcrazy.domain.adapters.services.ClienteService;
import br.com.fiap.kfcrazy.domain.dto.request.IngredienteRequestDTO;
import br.com.fiap.kfcrazy.domain.dto.request.PedidoRequestDTO;
import br.com.fiap.kfcrazy.domain.dto.request.ProdutoRequestDTO;
import br.com.fiap.kfcrazy.domain.exceptions.PedidoNaoEncontradoException;
import br.com.fiap.kfcrazy.domain.ports.ClienteServicePort;
import br.com.fiap.kfcrazy.domain.ports.IngredienteServicePort;
import br.com.fiap.kfcrazy.domain.ports.ProdutoServicePort;
import br.com.fiap.kfcrazy.infra.adapters.entities.Cliente;
import br.com.fiap.kfcrazy.infra.adapters.entities.Ingrediente;
import br.com.fiap.kfcrazy.infra.adapters.entities.Pedido;
import br.com.fiap.kfcrazy.domain.ports.PedidoServicePort;
import br.com.fiap.kfcrazy.infra.adapters.entities.Produto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/pedidos")
@Tag(name = "Pedido", description = "Operações relacionadas a pedidos")
public class PedidoController {

    private final PedidoServicePort pedidoServicePort;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Cria um novo pedido",
            description = "Cria um novo pedido com os detalhes fornecidos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            schema = @Schema(implementation = PedidoRequestDTO.class),
                            examples = @ExampleObject(
                                    name = "Exemplo de Pedido",
                                    summary = "Exemplo de criação de um pedido com IDs de cliente e produtos",
                                    value = "{\n" +
                                            "  \"clienteId\": 1,\n" +
                                            "  \"produtos\": [\n" +
                                            "    {\n" +
                                            "      \"id\": 1,\n" +
                                            "      \"ingredientes\": [\n" +
                                            "        {\"id\": 1, \"quantidade\": 2},\n" +
                                            "        {\"id\": 2, \"quantidade\": 1},\n" +
                                            "        {\"id\": 3, \"quantidade\": 1},\n" +
                                            "        {\"id\": 4, \"quantidade\": 1},\n" +
                                            "        {\"id\": 5, \"quantidade\": 1}\n" +
                                            "      ]\n" +
                                            "    }\n" +
                                            "  ]\n" +
                                            "}"
                            )
                    )
            )
    )
    public ResponseEntity<Pedido> iniciarPedido(@RequestBody @Valid PedidoRequestDTO pedidoRequestDTO) {
        Pedido createdPedido = null;
        try{


            createdPedido = pedidoServicePort.create(pedidoRequestDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(createdPedido, HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Listar todos os pedidos")
    public ResponseEntity<List<Pedido>> getAll() {
        List<Pedido> pedidos = pedidoServicePort.getAll();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Encontrar pedido por ID")
    public ResponseEntity<Pedido> findById(@PathVariable("id") Long id) {
        Pedido pedido = pedidoServicePort.findById(id);
        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(description = "cancelar pedido por ID")
    public ResponseEntity<Void> cancelar(@PathVariable("id") Long id) {
        pedidoServicePort.cancelar(id);
        return ResponseEntity.noContent().build();
    }
}
