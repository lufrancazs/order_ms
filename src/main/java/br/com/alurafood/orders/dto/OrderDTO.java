package br.com.alurafood.orders.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderDTO {
	
	private Long id;
	private LocalDateTime dataHora;
	private OrderStatusDTO status;
	private List<OrderItemDTO> itens = new ArrayList<>();

}
