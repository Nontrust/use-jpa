package com.jpa.use.usejpa.controller;

import com.jpa.use.usejpa.domain.Member;
import com.jpa.use.usejpa.domain.Order;
import com.jpa.use.usejpa.domain.items.Item;
import com.jpa.use.usejpa.exception.item.NotEnoughStockException;
import com.jpa.use.usejpa.service.ItemService;
import com.jpa.use.usejpa.service.MemberService;
import com.jpa.use.usejpa.service.OrderService;
import com.jpa.use.usejpa.vo.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model){
        List<Member> members = memberService.findAll();
        List<Item> items = itemService.findAll();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("order")
    String createOrder(
            @RequestParam("memberId") Long memberId,
            @RequestParam("itemId") Long itemId,
            @RequestParam("count") Integer count
    ) throws NotEnoughStockException {
        orderService.order(memberId, null,  itemId, count);
        return "redirect:/orders";
    }

    @GetMapping("orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSerch, Model model){
        List<Order> orders = orderService.findAll(orderSerch);
        model.addAttribute("orders", orders);

        return "order/orderList";
    }

    @PostMapping("orders/{orderId}/cancel")
    public String orderCancel(
            @PathVariable("orderId") Long orderId,
            Model model
    ){
        orderService.cancel(orderId);

        return "redirect:/orders";
    }
}

