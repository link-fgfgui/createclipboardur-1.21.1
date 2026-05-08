package io.github.linkfgfgui.ccur.mixin;

import com.simibubi.create.content.equipment.clipboard.ClipboardEntry;
import com.simibubi.create.content.equipment.clipboard.ClipboardScreen;
import dev.emi.emi.api.EmiApi;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.config.EmiConfig;
import dev.emi.emi.runtime.EmiFavorites;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ClipboardScreen.class)
public class ClipboardScreenMixin {

    @Shadow
    private int hoveredEntry;

    @Shadow
    private List<ClipboardEntry> currentEntries;

    @Shadow
    private int editingIndex;

    @Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
    private void ccur$onKeyPressed(int keyCode, int scanCode, int modifiers,
                                   CallbackInfoReturnable<Boolean> cir) {
        if (editingIndex != -1)
            return;

        if (hoveredEntry < 0 || hoveredEntry >= currentEntries.size())
            return;
        ClipboardEntry entry = currentEntries.get(hoveredEntry);
        if (entry.icon.isEmpty())
            return;

        EmiStack stack = EmiStack.of(entry.icon, entry.itemAmount);

        if (EmiConfig.viewRecipes.matchesKey(keyCode, scanCode)) {
            EmiApi.displayRecipes(stack);
            cir.setReturnValue(true);
        } else if (EmiConfig.viewUses.matchesKey(keyCode, scanCode)) {
            EmiApi.displayUses(stack);
            cir.setReturnValue(true);
        } else if (EmiConfig.favorite.matchesKey(keyCode, scanCode)) {
            EmiFavorites.addFavorite(stack);
            cir.setReturnValue(true);
        }
    }
}
