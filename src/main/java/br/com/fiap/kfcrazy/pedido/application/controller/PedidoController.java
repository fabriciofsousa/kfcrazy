package br.com.fiap.kfcrazy.pedido.application.controller;

import br.com.fiap.kfcrazy.pedido.domain.model.Pedido;
import br.com.fiap.kfcrazy.pedido.infrastructure.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(description = "Iniciar um novo pedido")
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

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Atualizar pedido por ID")
    public ResponseEntity<Pedido> update(@RequestBody Pedido pedido, @PathVariable("id") Long id) throws Exception {
        Pedido updatedPedido = pedidoService.update(id, pedido);
        return new ResponseEntity<>(updatedPedido, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(description = "Deletar pedido por ID")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
