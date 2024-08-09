package br.com.fiap.kfcrazy.pedido.application.controller;

import br.com.fiap.kfcrazy.pedido.application.dto.request.PedidoRequestDTO;
import br.com.fiap.kfcrazy.pedido.domain.model.Pedido;
import br.com.fiap.kfcrazy.pedido.infrastructure.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/pedidos")
@Tag(name = "Pedido", description = "Operações relacionadas a pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Cria um novo pedido",
            description = "Cria um novo pedido com os detalhes fornecidos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            schema = @Schema(implementation = PedidoRequestDTO.class),
                            examples = @ExampleObject(
                                    name = "Exemplo de Pedido",
                                    summary = "Exemplo de criação de um pedido com hambúrguer clássico e refrigerante",
                                    value = "{\n" +
                                            "  \"cliente\": {\n" +
                                            "    \"cpf\": \"12345678901\",\n" +
                                            "    \"nome\": \"Marcelo de Nobrega\",\n" +
                                            "    \"email\": \"marcelo.de.nobrega@gmail.com\"\n" +
                                            "  },\n" +
                                            "  \"produtos\": [\n" +
                                            "    {\n" +
                                            "      \"categoria\": \"LANCHE\",\n" +
                                            "      \"nome\": \"Hambúrguer Clássico\",\n" +
                                            "      \"descricao\": \"Hambúrguer com carne, queijo, alface e tomate, servido com pão fresco.\",\n" +
                                            "      \"preco\": 50.80,\n" +
                                            "      \"ingredientes\": [\n" +
                                            "        {\n" +
                                            "          \"nome\": \"Carne de Bovino\",\n" +
                                            "          \"quantidade\": 1\n" +
                                            "        },\n" +
                                            "        {\n" +
                                            "          \"nome\": \"Queijo Cheddar\",\n" +
                                            "          \"quantidade\": 2\n" +
                                            "        },\n" +
                                            "        {\n" +
                                            "          \"nome\": \"Alface\",\n" +
                                            "          \"quantidade\": 1\n" +
                                            "        },\n" +
                                            "        {\n" +
                                            "          \"nome\": \"Tomate\",\n" +
                                            "          \"quantidade\": 3\n" +
                                            "        },\n" +
                                            "        {\n" +
                                            "          \"nome\": \"Pão de Hambúrguer\",\n" +
                                            "          \"quantidade\": 2\n" +
                                            "        }\n" +
                                            "      ]\n" +
                                            "    },\n" +
                                            "    {\n" +
                                            "      \"categoria\": \"BEBIDA\",\n" +
                                            "      \"nome\": \"Coca Cola Lata\",\n" +
                                            "      \"descricao\": \"Refrigerante de coca cola em lata, 350ml.\",\n" +
                                            "      \"preco\": 7.00,\n" +
                                            "      \"ingredientes\": [\n" +
                                            "        {\n" +
                                            "          \"nome\": \"Lata de coca cola\",\n" +
                                            "          \"quantidade\": 1\n" +
                                            "        }\n" +
                                            "      ]\n" +
                                            "    }\n" +
                                            "  ]\n" +
                                            "}"
                            )
                    )
            )
    )
    public ResponseEntity<Pedido> iniciarPedido(@RequestBody @Valid Pedido pedido) {
        Pedido createdPedido = pedidoService.create(pedido);
        return new ResponseEntity<>(createdPedido, HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Listar todos os pedidos")
    public ResponseEntity<List<Pedido>> getAll() {
        List<Pedido> pedidos = pedidoService.getAll();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Encontrar pedido por ID")
    public ResponseEntity<Pedido> findById(@PathVariable("id") Long id) {
        Pedido pedido = pedidoService.findById(id);
        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(description = "Deletar pedido por ID")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
