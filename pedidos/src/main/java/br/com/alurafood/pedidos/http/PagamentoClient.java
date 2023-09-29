package br.com.alurafood.pedidos.http;

import br.com.alurafood.pedidos.dto.PagamentoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("pagamentos-ms")
public interface PagamentoClient {

        @GetMapping("/pagamentos/{id}")
        PagamentoDTO getById(@PathVariable Long id);

        @PutMapping("/pagamentos/{id}")
        PagamentoDTO update(@PathVariable Long id, @RequestBody PagamentoDTO pagamentoDTO);
}