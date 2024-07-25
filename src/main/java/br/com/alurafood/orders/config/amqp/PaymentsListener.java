package br.com.alurafood.orders.config.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.alurafood.orders.dto.PaymentDTO;

@Component
public class PaymentsListener {
	
	@RabbitListener(queues = "payment.check")
	public void getMessage(PaymentDTO payment) {
		
		String msg = """
				Dados do pagamento: %s
				Número do Pedido: %s
				Valor R$: %s
				Status: %s
				""".formatted(payment.getId(), 
						payment.getPedidoId(), 
						payment.getValor(), 
						payment.getStatus());
		
		System.out.println("Recebi a mensagem " + msg);
	}

}
