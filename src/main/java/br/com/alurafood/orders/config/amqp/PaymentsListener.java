package br.com.alurafood.orders.config.amqp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentsListener {
	
	@RabbitListener(queues = "payment.check")
	public void getMessage(Message msg) {
		System.out.println("Recebi a mensagem " + msg.toString());
	}

}
