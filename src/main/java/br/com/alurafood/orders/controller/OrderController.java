package br.com.alurafood.orders.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alurafood.orders.dto.OrderDTO;
import br.com.alurafood.orders.dto.OrderStatusDTO;
import br.com.alurafood.orders.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService service;

	@GetMapping
	public List<OrderDTO>findAll(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderDTO> findById(@PathVariable @NotNull Long id){
		OrderDTO dto = service.findById(id);
		
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping("/porta")
	public String retornPort(@Value("${local.server.port}") String port) {
		return String.format("Requisição respondida pela instancia, executando na porta %s", port);
	}
	
	@PostMapping()
	public ResponseEntity<OrderDTO> createOrder(@RequestBody @Valid OrderDTO dto, UriComponentsBuilder uriBuilder){
		OrderDTO createOrder = service.create(dto);
		
		URI adress = uriBuilder.path("/orders/{id}").buildAndExpand(createOrder.getId()).toUri();
		
		return ResponseEntity.created(adress).body(createOrder);
	}
	
	@PutMapping("/{id}/status")
	public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderStatusDTO status){
		OrderDTO dto = service.update(id, status);
		
		return ResponseEntity.ok(dto);
	}
	
	@PutMapping("/{id}/paid")
	public ResponseEntity<Void> approvedPayment(@PathVariable @NotNull Long id){
		service.approvePaymentRequest(id);
		
		return ResponseEntity.ok().build();
	}
}
