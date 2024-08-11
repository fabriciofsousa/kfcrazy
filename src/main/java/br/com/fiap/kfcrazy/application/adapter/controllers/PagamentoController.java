package br.com.fiap.kfcrazy.application.adapter.controllers;

import br.com.fiap.kfcrazy.infra.adapters.entities.Pagamento;
import br.com.fiap.kfcrazy.domain.ports.PagamentoServicePort;
import com.google.zxing.WriterException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/pagamentos")
@Tag(name = "Pagamento (em construcao)", description = "Operações relacionadas a pagamentos")
public class PagamentoController {

    private final PagamentoServicePort pagamentoServicePort;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(description = "Criar um novo pagamento")
    public ResponseEntity<Pagamento> criarPagamento(@RequestParam String descricao, @RequestParam BigDecimal valor) {
        /*try {
            Pagamento pagamento = pagamentoServicePort.criarPagamento(descricao, valor);
            //return new ResponseEntity<>(pagamento, HttpStatus.CREATED);
        } catch (WriterException | IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }*/
            return null;
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Encontrar pagamento por ID")
    public ResponseEntity<Pagamento> encontrarPagamentoPorId(@PathVariable("id") Long id) {
        //Optional<Pagamento> pagamentoOptional = pagamentoServicePort.findById(id);
        //if (pagamentoOptional.isPresent()) {
            //return new ResponseEntity<>(pagamentoOptional.get(), HttpStatus.OK);
        //} else {
        //    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        //}
            return null;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Listar todos os pagamentos")
    public ResponseEntity<List<Pagamento>> listarPagamentos() {
        //List<Pagamento> pagamentos = pagamentoServicePort.getAll();
        //return new ResponseEntity<>(pagamentos, HttpStatus.OK);
        return null;
    }

}
