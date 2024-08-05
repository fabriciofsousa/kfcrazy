package br.com.fiap.kfcrazy.pagamento.application.controller;

import br.com.fiap.kfcrazy.pagamento.domain.model.Pagamento;
import br.com.fiap.kfcrazy.pagamento.infraestructure.service.PagamentoService;
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
@Tag(name = "Pagamento", description = "Operações relacionadas a pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(description = "Criar um novo pagamento")
    public ResponseEntity<Pagamento> criarPagamento(@RequestParam String descricao, @RequestParam BigDecimal valor) {
        try {
            Pagamento pagamento = pagamentoService.criarPagamento(descricao, valor);
            return new ResponseEntity<>(pagamento, HttpStatus.CREATED);
        } catch (WriterException | IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Encontrar pagamento por ID")
    public ResponseEntity<Pagamento> encontrarPagamentoPorId(@PathVariable("id") Long id) {
        Optional<Pagamento> pagamentoOptional = pagamentoService.findById(id);
        if (pagamentoOptional.isPresent()) {
            return new ResponseEntity<>(pagamentoOptional.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Listar todos os pagamentos")
    public ResponseEntity<List<Pagamento>> listarPagamentos() {
        List<Pagamento> pagamentos = pagamentoService.getAll();
        return new ResponseEntity<>(pagamentos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Atualizar pagamento por ID")
    public ResponseEntity<Pagamento> atualizarPagamento(@PathVariable("id") Long id, @RequestBody Pagamento pagamento) {
        Pagamento updatedPagamento = pagamentoService.update(id, pagamento);
        if (updatedPagamento != null) {
            return new ResponseEntity<>(updatedPagamento, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(description = "Deletar pagamento por ID")
    public ResponseEntity<Void> deletarPagamento(@PathVariable("id") Long id) {
        pagamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
