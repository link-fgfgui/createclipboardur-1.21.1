package io.github.linkfgfgui.ccur.mixin;

import com.simibubi.create.content.equipment.clipboard.ClipboardScreen;
import dev.emi.emi.api.EmiApi;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.runtime.EmiHistory;
import dev.emi.emi.runtime.EmiSidebars;
import dev.emi.emi.screen.RecipeScreen;
import io.github.linkfgfgui.ccur.ModCreateClipboardUR;
import io.github.linkfgfgui.ccur.Shared;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Mixin(EmiApi.class)
public class EmiApiMixin {
    @Shadow
    @Final
    private static Minecraft client;

    @Inject(method = "setPages", at = @At("HEAD"), cancellable = true)
    private static void ccur$onSetPages(Map<EmiRecipeCategory, List<EmiRecipe>> recipes, EmiIngredient stack,
                                        CallbackInfo ci) {
        if (!(client.screen instanceof ClipboardScreen)) {
            return;
        }

        recipes = recipes.entrySet().stream()
                .filter(e -> !e.getValue().isEmpty())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (recipes.isEmpty()) {
            ci.cancel();
            return;
        }

        EmiSidebars.lookup(stack);

        EmiHistory.clear();
        EmiHistory.push(client.screen);

        AbstractContainerScreen<?> absScreen;
        if (Shared.screen == null) {
            absScreen = new InventoryScreen(client.player);
        }else{
            absScreen = Shared.screen;
        }

        client.setScreen(new RecipeScreen(absScreen, recipes));
        ci.cancel();
    }
}
