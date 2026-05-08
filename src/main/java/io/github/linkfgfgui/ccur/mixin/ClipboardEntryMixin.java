package io.github.linkfgfgui.ccur.mixin;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.simibubi.create.content.equipment.clipboard.ClipboardContent;
import com.simibubi.create.content.equipment.clipboard.ClipboardEntry;

import io.github.linkfgfgui.ccur.Utils;

@Mixin(ClipboardEntry.class)
public class ClipboardEntryMixin {

	@Inject(method = "readAll(Lcom/simibubi/create/content/equipment/clipboard/ClipboardContent;)Ljava/util/List;",
		at = @At("HEAD"), cancellable = true)
	private static void ccur$onReadAll(@Nullable ClipboardContent content,
			CallbackInfoReturnable<List<List<ClipboardEntry>>> cir) {
		if (content == null) {
			cir.setReturnValue(new ArrayList<>());
			return;
		}

		List<List<ClipboardEntry>> saved = content.pages();
		List<List<ClipboardEntry>> entries = new ArrayList<>(saved.size());

		for (List<ClipboardEntry> inner : saved) {
			List<ClipboardEntry> page = new ArrayList<>(inner.size());
			for (ClipboardEntry entry : inner) {
				if (!entry.icon.isEmpty() && entry.itemAmount > 0
						&& Utils.isEnoughItemsInInventory(entry.icon, entry.itemAmount)) {
					entry.checked = true;
				}
				page.add(entry);
			}
			entries.add(page);
		}

		cir.setReturnValue(entries);
	}
}
