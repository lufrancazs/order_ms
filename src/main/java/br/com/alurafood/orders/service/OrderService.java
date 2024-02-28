package br.com.alurafood.orders.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alurafood.orders.dto.OrderDTO;
import br.com.alurafood.orders.dto.OrderStatusDTO;
import br.com.alurafood.orders.enums.OrderStatus;
import br.com.alurafood.orders.model.Order;
import br.com.alurafood.orders.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private final ModelMapper modelMapper;
	
	public List<OrderDTO> findAll(){
		return repository.findAll().stream()
				.map(p -> modelMapper.map(p, OrderDTO.class))
				.collect(Collectors.toList());
	}
	
	public OrderDTO findById(Long id) {
		Order order = repository.findById(id)
				.orElseThrow(EntityNotFoundException::new);
		
		return modelMapper.map(order, OrderDTO.class);
	}
	
	public OrderDTO create(OrderDTO dto) {
		Order order = modelMapper.map(dto, Order.class);
		
		order.setDataHora(LocalDateTime.now());
		order.setStatus(OrderStatus.REALIZADO);
		order.getItens().forEach(item -> item.setOrder(order));
		Order save = repository.save(order);
		
		return modelMapper.map(order, OrderDTO.class);
	}
	
	public OrderDTO update(Long id, OrderStatusDTO dto) {
		Order order = repository.porIdComItens(id);
		
		if (order == null) {
			throw new EntityNotFoundException();
		}
		
		order.setStatus(dto.getStatus());
		repository.atualizarStatus(dto.getStatus(), order);
		
		return modelMapper.map(order, OrderDTO.class);
	}
	
	public void approvePaymentRequest(Long id) {
		
		Order order = repository.porIdComItens(id);
		
		if(order == null) {
			throw new EntityNotFoundException();
		}
		
		order.setStatus(OrderStatus.PAGO);
		repository.atualizarStatus(OrderStatus.PAGO, order);
	}
	
}
