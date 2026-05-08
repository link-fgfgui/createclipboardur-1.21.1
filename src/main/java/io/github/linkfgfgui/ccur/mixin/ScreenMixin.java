package io.github.linkfgfgui.ccur.mixin;

import com.simibubi.create.content.equipment.clipboard.ClipboardScreen;
import io.github.linkfgfgui.ccur.Shared;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class ScreenMixin {
    @SuppressWarnings("ConstantConditions")
    @Inject(method = "onClose", at = @At("HEAD"))
    private void ccur$onClose(CallbackInfo ci) {
        if ((Object) this instanceof ClipboardScreen && Shared.screen != null){
            Shared.screen.onClose();
            Shared.screen = null;
        }
    }
}
