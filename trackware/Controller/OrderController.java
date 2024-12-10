package com.example.trackware.Controller;

import com.example.trackware.Api.ApiResponse;
import com.example.trackware.Model.Orders;
import com.example.trackware.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/get")
    public ResponseEntity getAllOrders() {
        return ResponseEntity.status(200).body(orderService.getAllOrders());
    }

    @PostMapping("/add")
    public ResponseEntity addOrder(@RequestBody @Valid Orders order) {
        orderService.addOrder(order);
        return ResponseEntity.status(200).body(new ApiResponse("Order added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateOrder(@PathVariable Integer id , @RequestBody @Valid Orders order) {
        orderService.updateOrder(id, order);
        return ResponseEntity.status(200).body(new ApiResponse("Order updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return ResponseEntity.status(200).body(new ApiResponse("Order deleted"));
    }

    @PutMapping("/cancelOrder/{order_id}/{companyId}/{branch_id}")
    public ResponseEntity cancelOrder(@PathVariable Integer order_id,@PathVariable Integer companyId,@PathVariable Integer branch_id) {
        orderService.cancelOrder(order_id, companyId, branch_id);
        return ResponseEntity.status(200).body(new ApiResponse("Order cancelled"));
    }
}
