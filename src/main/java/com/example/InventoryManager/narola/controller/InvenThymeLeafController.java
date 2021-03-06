package com.example.InventoryManager.narola.controller;

import com.example.InventoryManager.narola.entity.Inventory;
import com.example.InventoryManager.narola.service.InventoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping("/inventory")
public class InvenThymeLeafController {

    private final InventoryService inventoryService;
    public InvenThymeLeafController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @RequestMapping("/list")
    public String viewHomePage(Model model) {
        List<Inventory> listProducts = inventoryService.getAll();
        model.addAttribute("listProducts",listProducts);
        return "viewProducts";
    }

    @RequestMapping("/add")
    public String showNewProductPage(Model model) {
        Inventory product = new Inventory();
        model.addAttribute("product", product);
        return "newProduct";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Inventory inventory) {
        inventoryService.save(inventory);
        return "redirect:/inventory/list";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("editProduct");
        Inventory product = inventoryService.getById(id);
        mav.addObject("product", product);
        return mav;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateProduct(@ModelAttribute("product") Inventory inventory) {
        inventoryService.update(inventory);
        return "redirect:/inventory/list";
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        inventoryService.delete(id);
        return "redirect:/inventory/list";
    }
}
