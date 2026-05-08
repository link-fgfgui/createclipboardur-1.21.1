package io.github.linkfgfgui.ccur;

import dev.emi.emi.api.recipe.EmiPlayerInventory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public class Utils {
    public static boolean isEnoughItemsInInventory(ItemStack is, int count) {
        var player = Minecraft.getInstance().player;
        if (player == null) return false;
        var stack = EmiPlayerInventory.of(player).inventory.get(EmiStack.of(is.getItem()));
        return stack != null && stack.getAmount() >= count;
    }
}
