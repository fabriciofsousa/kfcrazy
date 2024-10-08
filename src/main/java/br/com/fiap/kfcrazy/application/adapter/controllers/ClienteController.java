package br.com.fiap.kfcrazy.application.adapter.controllers;

import br.com.fiap.kfcrazy.domain.exceptions.ClienteNaoEncontradoException;
import br.com.fiap.kfcrazy.infra.adapters.entities.Cliente;
import br.com.fiap.kfcrazy.domain.ports.ClienteServicePort;
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
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/clientes")
@Tag(name = "Cliente", description = "Operações relacionadas a clientes")
public class ClienteController {

    private final ClienteServicePort clienteServicePort;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(
            description = "Criar um novo cliente",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Cliente a ser criado",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = Cliente.class),
                            examples = @ExampleObject(
                                    name = "Exemplo de Cliente",
                                    value = "{\"nome\": \"Teste nome\", \"cpf\": \"89689658702\", \"email\": \"teste.email@fiap.com\"}"
                            )
                    )
            )
    )
    public ResponseEntity<Cliente> criarCliente(@RequestBody @Valid Cliente cliente) {
        Cliente createdCliente = clienteServicePort.criarCliente(cliente);
        return new ResponseEntity<>(createdCliente, HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Listar todos os clientes")
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteServicePort.listarClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("id/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Encontrar cliente por ID")
    public ResponseEntity<Cliente> encontrarClientePorId(@PathVariable("id") Long id) throws ClienteNaoEncontradoException {
        Cliente cliente = null;
        try{
        cliente = clienteServicePort.listarClientes()
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado!"));
        }catch (Exception e){
            throw new ClienteNaoEncontradoException("Cliente não encontrado!");
        }
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @GetMapping("cpf/{cpf}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Encontrar cliente por CPF")
    public ResponseEntity<Cliente> findByCPF(@PathVariable("cpf") String cpf) throws ClienteNaoEncontradoException {
        try{
            Optional<Cliente> cliente = clienteServicePort.findByCpf(cpf);

        }catch (Exception e){
            throw new ClienteNaoEncontradoException("Cliente não encontrado!");
        }
        Cliente cliente = clienteServicePort.findByCpf(cpf).orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado!"));
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

}
