package com.example.trackware.Service;

import com.example.trackware.Api.ApiException;
import com.example.trackware.Model.*;
import com.example.trackware.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final BranchRepository branchRepository;
    private final ItemsRepository itemsRepository;
    private final WareHouseRepository wareHouseRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }
    public void addOrder(Orders order) {
        Items items = itemsRepository.findItemsById(order.getItem_id());
        if (isOrderValid(order.getBranch_id(),order.getWarehouse_id(),order.getItem_id(),order.getQuantity())) {
            order.setTotalPrice(items.getPrice() * order.getQuantity());
            order.setStatus("Approved");
            orderRepository.save(order);
            items.setQuantity(items.getQuantity() - order.getQuantity());
            itemsRepository.save(items);
        }else {
            order.setTotalPrice(0);
            order.setStatus("Rejected");
            orderRepository.save(order);
        }
    }
    public Boolean isOrderValid(Integer branch_id,Integer wareHouse_id ,Integer item_id,Integer quantity) {
        Branch branch = branchRepository.findBranchById(branch_id);
        WareHouse wareHouse = wareHouseRepository.findWareHouseById(wareHouse_id);
        Items items = itemsRepository.findItemsById(item_id);

        if (branch == null ){
            throw new ApiException("Branch Not Found");
        }
        if(wareHouse == null ){
            throw new ApiException("Warehouse Not Found");
        }
        if( items == null){
            throw new ApiException("Item Not Found");
        }
        if (items.getQuantity() >= quantity){
            return true;
        }
        return false;
    }


    public void updateOrder(Integer id, Orders order) {
        Orders updateOrder = orderRepository.findOrderById(id);
        if (updateOrder == null) {
            throw new ApiException("Order not found");
        }
        updateOrder.setQuantity(order.getQuantity());
        updateOrder.setNotes(order.getNotes());
        updateOrder.setStatus(order.getStatus());
        updateOrder.setTotalPrice(order.getTotalPrice());
        updateOrder.setWarehouse_id(order.getWarehouse_id());
        updateOrder.setBranch_id(order.getBranch_id());
        updateOrder.setItem_id(order.getItem_id());
        updateOrder.setUpdatedAt(LocalDateTime.now());
        orderRepository.save(updateOrder);
    }

    public void deleteOrder(Integer id) {
        Orders order = orderRepository.findOrderById(id);
        if (order == null) {
            throw new ApiException("Order not found");
        }
        orderRepository.delete(order);
    }

    public void cancelOrder(Integer order_id,Integer company_id,Integer branch_id) {
        Orders order = orderRepository.findOrderById(order_id);
        Branch branch = branchRepository.findBranchByIdAndCompanyId(branch_id,company_id);
        if (order == null) {
            throw new ApiException("Order not found");
        }
        if (branch == null) {
            throw new ApiException("Branch not found");
        }
        if (order.getBranch_id() == branch_id) {
            if (branch.getCompany_id() == company_id) {
                Items items = itemsRepository.findItemsById(order.getItem_id());
                items.setQuantity(items.getQuantity() + order.getQuantity());
                itemsRepository.save(items);
                order.setStatus("Cancel");
                orderRepository.save(order);
            }
        }
    }
}
