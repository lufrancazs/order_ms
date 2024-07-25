package br.com.alurafood.orders.dto;

import java.math.BigDecimal;

import br.com.alurafood.orders.dto.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDTO {
	
	private Long id;
	private BigDecimal valor;
	private String nome;
	private String numero;
	private String expiracao;
	private String codigo;
	private PaymentStatus status;
	private Long pedidoId;
	private Long formaDePagamentoId;

}
