package br.com.fiap.kfcrazy.application.adapter.controllers;

import br.com.fiap.kfcrazy.domain.dto.request.ProdutoDTO;
import br.com.fiap.kfcrazy.domain.dto.response.ProdutoResponseDTO;
import br.com.fiap.kfcrazy.domain.enums.CategoriaProduto;
import br.com.fiap.kfcrazy.domain.mapper.IngredienteMapper;
import br.com.fiap.kfcrazy.infra.adapters.entities.Ingrediente;
import br.com.fiap.kfcrazy.infra.adapters.entities.Produto;
import br.com.fiap.kfcrazy.domain.ports.ProdutoServicePort;
import br.com.fiap.kfcrazy.domain.mapper.ProdutoMapper;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produto", description = "Operações relacionadas a Produtos")
public class ProdutoController {

    @Autowired
    private ProdutoServicePort produtoServicePort;

    @PostMapping
    @Operation(summary = "Cria um novo produto",
            description = "Cria um novo produto com os detalhes fornecidos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            schema = @Schema(implementation = ProdutoDTO.class),
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
    public ResponseEntity<ProdutoResponseDTO> create(@RequestBody ProdutoDTO produtoDTO) {
        Produto produto = ProdutoMapper.INSTANCE.toEntity(produtoDTO);
        Produto createdProduto = produtoServicePort.create(produto);
        ProdutoResponseDTO responseDTO = ProdutoMapper.INSTANCE.toDto(createdProduto);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Encontra um produto pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable Long id) {
        Optional<Produto> produto = produtoServicePort.findById(id);
        return produto.map(p -> ResponseEntity.ok(ProdutoMapper.INSTANCE.toDto(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Encontra um produto por categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @GetMapping("/categoria/{categoriaProduto}")
    public ResponseEntity<List<ProdutoResponseDTO>> findByCategoria(@PathVariable CategoriaProduto categoriaProduto) {
        List<Produto> produtos = produtoServicePort.getByCategoria(categoriaProduto);
        List<ProdutoResponseDTO> responseDTOs = produtos.stream()
                .map(ProdutoMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
        return responseDTOs.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(responseDTOs);
    }

    @Operation(summary = "Lista todos os produtos")
    @ApiResponse(responseCode = "200", description = "Lista de produtos")
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> findAll() {
        List<Produto> produtos = produtoServicePort.findAll();
        List<ProdutoResponseDTO> responseDTOs = produtos.stream()
                .map(ProdutoMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualiza um produto existente",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            schema = @Schema(implementation = ProdutoDTO.class),
                            examples = @ExampleObject(
                                    name = "Exemplo de alteração de Hambúrguer",
                                    summary = "Exemplo de alteração de hambúrguer clássico para hambúrguer clássico com Bacon",
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
    public ResponseEntity<ProdutoResponseDTO> update(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        try {
            Produto updatedProduto = produtoServicePort.update(id, produtoDTO);
            ProdutoResponseDTO responseDTO = ProdutoMapper.INSTANCE.toDto(updatedProduto);
            return ResponseEntity.ok(responseDTO);
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
            produtoServicePort.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
