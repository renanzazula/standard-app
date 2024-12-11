package com.standard.controller;

import com.standard.domain.Order;
import com.standard.service.order.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping(OrderController.BASE_URL)
public class OrderController {

    public static final String BASE_URL = "/private/v1/order";

    private final OrderService orderService;

    // 1 - next
    @PostMapping("/create")
    public ResponseEntity<Order> create(@RequestBody Order order) {
        return new ResponseEntity<>(orderService.create(order), HttpStatus.OK);
    }

    // 2 - get order by id
    @GetMapping("/{id}/confirm")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(orderService.findById(new Order(id)), HttpStatus.OK);
    }

    // 3 - confirm
    @PostMapping("/confirm")
    public ResponseEntity<Order> updateStatusOrder(@RequestBody Order order) {
        return new ResponseEntity<>(orderService.updateStatusOrder(order), HttpStatus.OK);
    }

    // 4 imprimir recibo ou enviar por email

    // 2 cancelar venda
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Venda alterarStatusVendaParaNaoRealizada(@RequestBody Venda venda){
//        return vendaService.alterarStatusVendaParaNaoRealizada(venda);
//    }

}
