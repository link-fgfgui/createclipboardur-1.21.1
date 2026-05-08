package io.github.linkfgfgui.ccur.mixin;

import com.simibubi.create.content.equipment.clipboard.ClipboardScreen;
import io.github.linkfgfgui.ccur.ModCreateClipboardUR;
import io.github.linkfgfgui.ccur.Shared;
import net.createmod.catnip.gui.ScreenOpener;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractContainerScreen.class)
public class AbstractContainerScreenMixin {
    @Inject(method = "slotClicked", at = @At("HEAD"), cancellable = true)
    private void ccur$slotClicked(Slot slot, int slotId, int mouseButton, ClickType type, CallbackInfo ci) {
        if (type == ClickType.PICKUP && mouseButton == 1 && slot.getItem().getCount() == 1) {
            Shared.screen = ((AbstractContainerScreen<?>) (Object) this);
            ScreenOpener.open(new ClipboardScreen(slotId, slot.getItem().getComponents(), null));
            ci.cancel();
        }
    }

    @Inject(method = "onClose" , at = @At("HEAD"))
    private void ccur$onClose(CallbackInfo ci) {
        Shared.screen = null;
    }
}
