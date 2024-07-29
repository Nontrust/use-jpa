package com.jpa.use.usejpa.controller;

import com.jpa.use.usejpa.domain.enumerate.ItemDType;
import com.jpa.use.usejpa.domain.items.Item;
import com.jpa.use.usejpa.exception.ItemException;
import com.jpa.use.usejpa.service.ItemService;
import com.jpa.use.usejpa.vo.ItemForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model){
        model.addAttribute("form", ItemForm.of());
        model.addAttribute("ItemTypes", ItemDType.values());

        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(ItemForm form) throws ItemException {
        log.warn("create {}",  form);
        Item item = itemService.create(form);
        itemService.save(item);

        return "redirect:/items";
    }


    @GetMapping("/items")
    public String list(Model model){
        List<Item> items = itemService.findAll();
        model.addAttribute("items", items);

        return "items/itemList";
    }

    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId")Long itemId, @Validated Model model){
        Item item = itemService.findOne(itemId);
        ItemForm form = itemService.mapToVo(item);

        log.warn("form ::: {}", form);
        model.addAttribute("form", form);

        return "items/updateItemForm";
    }

    @PostMapping("items/{itemId}/edit")
    public String updateItem(@PathVariable("itemId") Long itemId, @ModelAttribute("form") ItemForm itemForm) throws ItemException {
        itemService.updateItem(itemId, itemForm);

        return "redirect:/items";
    }
}
