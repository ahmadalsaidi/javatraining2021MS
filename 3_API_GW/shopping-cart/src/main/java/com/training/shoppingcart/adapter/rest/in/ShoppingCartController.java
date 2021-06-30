package com.training.shoppingcart.adapter.rest.in;

import com.training.shoppingcart.adapter.rest.in.dto.LineItemDTO;
import com.training.shoppingcart.model.ShoppingCart;
import com.training.shoppingcart.service.ShoppingCartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("shopping-carts")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping
    public String openCart(String userId){
        return shoppingCartService.openShoppingCart(userId);
    }

    @GetMapping("/{cartId}/{userId}")
    public ShoppingCart viewingCart(@PathVariable String cartId,@PathVariable String userId){
        return shoppingCartService.view(cartId, userId);
    }

    @PatchMapping("/add-item/{cartId}/{userId}/")
    public void addingItem(@PathVariable String cartId,@PathVariable String userId,@RequestBody LineItemDTO lineItemDTO){

        shoppingCartService.addItem(cartId,userId, lineItemDTO.getItemId(), lineItemDTO.getQty());
    }

    @PatchMapping("/update-item/{cartId}/{userId}/")
    public void updatingItem(@PathVariable String cartId,@PathVariable String userId,@RequestBody LineItemDTO lineItemDTO){

        shoppingCartService.updateItemQty(cartId,userId, lineItemDTO.getItemId(), lineItemDTO.getQty());
    }

    @DeleteMapping("/delete-item/{cartId}/{userId}/{itemId}")
    public void updatingItem(@PathVariable String cartId,@PathVariable String userId,@PathVariable String itemId){

        shoppingCartService.removeItem(cartId,userId,itemId);
    }
}
