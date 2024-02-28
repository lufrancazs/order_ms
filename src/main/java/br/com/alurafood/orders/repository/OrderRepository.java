package br.com.alurafood.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.alurafood.orders.entities.Order;
import br.com.alurafood.orders.enums.OrderStatus;
import jakarta.transaction.Transactional;

public interface OrderRepository extends JpaRepository<Order, Long>{
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update Order p set p.status = :status where p = :order")
	void atualizarStatus(OrderStatus status, Order order);
	
	@Query(value = "SELECT p from Order p LEFT JOIN FETCH p.itens where p.id = :id")
	Order porIdComItens(Long id);
}
