package br.com.fiap.kfcrazy.preparacao.application.controller;

import br.com.fiap.kfcrazy.pedido.domain.model.Pedido;
import br.com.fiap.kfcrazy.preparacao.infraestructure.service.PreparacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preparacao")
@RequiredArgsConstructor
@Tag(name = "Preparação", description = "Operações relacionadas a Preparação do pedido")
public class PreparacaoController {

    private final PreparacaoService preparacaoService;

    @PostMapping("/criar")
    public ResponseEntity<Pedido> criarPedidoFakeCheckout(@RequestBody Pedido pedido) {
        Pedido novoPedido = preparacaoService.criarPedidoFakeCheckout(pedido);
        return ResponseEntity.ok(novoPedido);
    }

    @PutMapping("/enviar/{id}")
    public ResponseEntity<Pedido> enviarParaPreparacao(@PathVariable Long id) {
        Pedido pedido = preparacaoService.enviarParaPreparacao(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/iniciar/{id}")
    public ResponseEntity<Pedido> iniciarPreparacao(@PathVariable Long id) {
        Pedido pedido = preparacaoService.iniciarPreparacao(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/preparar/{id}")
    public ResponseEntity<Pedido> prepararPedido(@PathVariable Long id) {
        Pedido pedido = preparacaoService.prepararPedido(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<Pedido> cancelarPedido(@PathVariable Long id) {
        Pedido pedido = preparacaoService.cancelarPedido(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = preparacaoService.listarPedidos();
        return ResponseEntity.ok(pedidos);
    }
}
