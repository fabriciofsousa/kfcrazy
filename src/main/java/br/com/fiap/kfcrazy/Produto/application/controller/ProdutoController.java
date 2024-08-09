package br.com.fiap.kfcrazy.Produto.application.controller;

import br.com.fiap.kfcrazy.Produto.application.dto.ProdutoDTO;
import br.com.fiap.kfcrazy.Produto.domain.Enum.CategoriaProduto;
import br.com.fiap.kfcrazy.Produto.domain.model.Produto;
import br.com.fiap.kfcrazy.Produto.infraestructure.service.ProdutoService;
import br.com.fiap.kfcrazy.pedido.application.mapper.ProdutoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produto", description = "Operações relacionadas a Produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    @Operation(summary = "Cria um novo produto",
            description = "Cria um novo produto com os detalhes fornecidos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            schema = @Schema(implementation = Produto.class),
                            examples = @ExampleObject(
                                    name = "Exemplo de Hambúrguer",
                                    summary = "Exemplo de criação de hambúrguer clássico",
                                    value = "{\n" +
                                            "    \"categoria\": \"LANCHE\",\n" +
                                            "    \"nome\": \"Hambúrguer Clássico\",\n" +
                                            "    \"descricao\": \"Hambúrguer com carne, queijo, alface e tomate, servido com pão fresco.\",\n" +
                                            "    \"preco\": 22.00,\n" +
                                            "    \"ingredientes\": [\n" +
                                            "        {\n" +
                                            "            \"nome\": \"Carne de Bovino\"\n" +
                                            "        },\n" +
                                            "        {\n" +
                                            "            \"nome\": \"Queijo Cheddar\"\n" +
                                            "        },\n" +
                                            "        {\n" +
                                            "            \"nome\": \"Alface\"\n" +
                                            "        },\n" +
                                            "        {\n" +
                                            "            \"nome\": \"Tomate\"\n" +
                                            "        },\n" +
                                            "        {\n" +
                                            "            \"nome\": \"Pão de Hambúrguer\"\n" +
                                            "        }\n" +
                                            "    ]\n" +
                                            "}"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Produto criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
            }
    )
    public ResponseEntity<Produto> create(@RequestBody ProdutoDTO produtoDTO) {
        Produto produto = ProdutoMapper.INSTANCE.toEntity(produtoDTO);

        Produto createdProduto = produtoService.create(produto);
        return new ResponseEntity<>(createdProduto, HttpStatus.CREATED);
    }

    @Operation(summary = "Encontra um produto pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id) {
        Optional<Produto> produto = produtoService.findById(id);
        return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Encontra um produto por categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @GetMapping("categoria/{categoriaProduto}")
    public ResponseEntity<Produto> findById(@PathVariable CategoriaProduto categoriaProduto) {
        try {
            Optional<Produto> produto = Optional.ofNullable(produtoService.getByCategoria(categoriaProduto));
            return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Lista todos os produtos")
    @ApiResponse(responseCode = "200", description = "Lista de produtos")
    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        List<Produto> produtos = produtoService.findAll();
        return ResponseEntity.ok(produtos);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualiza um produto existente",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            schema = @Schema(implementation = Produto.class),
                            examples = @ExampleObject(
                                    name = "Exemplo de alteracao de Hambúrguer",
                                    summary = "Exemplo de alteracao de hambúrguer clássico para hambúrguer clássico com Bacon",
                                    value = "{\n" +
                                            "  \"categoria\": \"LANCHE\",\n" +
                                            "  \"nome\": \"Hambúrguer Clássico com Bacon\",\n" +
                                            "  \"descricao\": \"Hambúrguer com carne, queijo, alface, bacon e tomate, servido com pão fresco.\",\n" +
                                            "  \"preco\": 88,\n" +
                                            "  \"ingredientes\": [\n" +
                                            "    {\n" +
                                            "      \"id\": 1,\n" +
                                            "      \"nome\": \"Carne de Bovino\"\n" +
                                            "    },\n" +
                                            "    {\n" +
                                            "      \"id\": 2,\n" +
                                            "      \"nome\": \"Queijo Cheddar\"\n" +
                                            "    },\n" +
                                            "    {\n" +
                                            "      \"id\": 3,\n" +
                                            "      \"nome\": \"Alface\"\n" +
                                            "    },\n" +
                                            "    {\n" +
                                            "      \"id\": 4,\n" +
                                            "      \"nome\": \"Tomate\"\n" +
                                            "    },\n" +
                                            "    {\n" +
                                            "      \"id\": 5,\n" +
                                            "      \"nome\": \"Pão de Hambúrguer\"\n" +
                                            "    },\n" +
                                            "    {\n" +
                                            "      \"id\": 6,\n" +
                                            "      \"nome\": \"Bacon\"\n" +
                                            "    }\n" +
                                            "  ]\n" +
                                            "}"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
            }
    )
    public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody Produto produto) {
        try {
            Produto updatedProduto = produtoService.update(id, produto);
            return ResponseEntity.ok(updatedProduto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Remove um produto pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            produtoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}