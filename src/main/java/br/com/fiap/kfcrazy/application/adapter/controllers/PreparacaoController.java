package br.com.fiap.kfcrazy.application.adapter.controllers;

import br.com.fiap.kfcrazy.infra.adapters.entities.Pedido;
import br.com.fiap.kfcrazy.domain.ports.PreparacaoServicePort;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preparacao")
@RequiredArgsConstructor
@Tag(name = "Preparação (Em Construção)", description = "Operações relacionadas a Preparação do pedido")
public class PreparacaoController {

    private final PreparacaoServicePort preparacaoServicePort;

    @PostMapping("/criar")
    public ResponseEntity<Pedido> criarPedidoFakeCheckout(@RequestBody Pedido pedido) {
        Pedido novoPedido = preparacaoServicePort.criarPedidoFakeCheckout(pedido);
        //return ResponseEntity.ok(novoPedido);
        return null;
    }

    @PutMapping("/enviar/{id}")
    public ResponseEntity<Pedido> enviarParaPreparacao(@PathVariable Long id) {
        Pedido pedido = preparacaoServicePort.enviarParaPreparacao(id);
        if (pedido != null) {
            //return ResponseEntity.ok(pedido);
            return null;
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/iniciar/{id}")
    public ResponseEntity<Pedido> iniciarPreparacao(@PathVariable Long id) {
        Pedido pedido = preparacaoServicePort.iniciarPreparacao(id);
        if (pedido != null) {
            //return ResponseEntity.ok(pedido);
            return null;
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/preparar/{id}")
    public ResponseEntity<Pedido> prepararPedido(@PathVariable Long id) {
        Pedido pedido = preparacaoServicePort.prepararPedido(id);
        return null;
        /*if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }*/
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<Pedido> cancelarPedido(@PathVariable Long id) {
        return null;
/*        Pedido pedido = preparacaoServicePort.cancelarPedido(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }*/
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Pedido>> listarPedidos() {
        /*List<Pedido> pedidos = preparacaoServicePort.listarPedidos();
        return ResponseEntity.ok(pedidos);*/
        return null;
    }
}
