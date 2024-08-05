package br.com.fiap.kfcrazy.cliente.application.controller;

import br.com.fiap.kfcrazy.cliente.domain.model.Cliente;
import br.com.fiap.kfcrazy.cliente.infraestructure.service.ClienteService;
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
@RequestMapping("/clientes")
@Tag(name = "Cliente", description = "Operações relacionadas a clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(description = "Criar um novo cliente")
    public ResponseEntity<Cliente> criarCliente(@RequestBody @Valid Cliente cliente) {
        Cliente createdCliente = clienteService.criarCliente(cliente);
        return new ResponseEntity<>(createdCliente, HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Listar todos os clientes")
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Encontrar cliente por ID")
    public ResponseEntity<Cliente> encontrarClientePorId(@PathVariable("id") Long id) {
        Cliente cliente = clienteService.listarClientes()
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Atualizar cliente por ID")
    public ResponseEntity<Cliente> atualizarCliente(@RequestBody @Valid Cliente cliente, @PathVariable("id") Long id) {
        Cliente updatedCliente = clienteService.atualizarCliente(id, cliente);
        if (updatedCliente != null) {
            return new ResponseEntity<>(updatedCliente, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(description = "Deletar cliente por ID")
    public ResponseEntity<Void> deletarCliente(@PathVariable("id") Long id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
