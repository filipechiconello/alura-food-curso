package br.com.alurafood.pedidos.amqp;

import br.com.alurafood.pedidos.dto.PagamentoDTO;
import br.com.alurafood.pedidos.dto.StatusPagamento;
import br.com.alurafood.pedidos.http.PagamentoClient;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PagamentoListener {

    @Autowired
    private PagamentoClient client;

    @RabbitListener(queues = "pagamentos.detalhes-pedido")
    public void receiveMessage(PagamentoDTO pagamento) {

        String message = """
                Dados do pagamento: %s
                Número do pedido: %s
                Valor R$: %s
                Status: %s
                """.formatted(
                pagamento.getId(),
                pagamento.getPedidoId(),
                pagamento.getValor(),
                pagamento.getStatus()
        );

        PagamentoDTO pagamentoDTO = client.getById(pagamento.getId());
        pagamentoDTO.setStatus(StatusPagamento.CONFIRMADO);
        client.update(pagamento.getId(), pagamentoDTO);
        System.out.println("Recebi a menssagem da FILA " + message);
        System.out.println("Pagamento Confirmado " + pagamentoDTO);
    }
}