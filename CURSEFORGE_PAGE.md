# CreateClipboardUR

[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.1-green)](https://www.minecraft.net)
[![Mod Loader](https://img.shields.io/badge/Mod%20Loader-NeoForge-orange)](https://neoforged.net)

**CreateClipboardUR** is a lightweight client-side mod that provides deep integration between Create's **Clipboard** and **EMI**.

## Features

### 1. EMI Keybind Support (U / R / A)

While hovering over an item entry in the Create Clipboard screen, you can use EMI's shortcuts:

- **R** — View recipes for that item
- **U** — View uses for that item
- **A** — Add the item to EMI favorites

No need to exit the clipboard screen to look up recipes.

### 2. Auto-Check Inventory Items

The clipboard automatically checks whether you have enough of each required item in your inventory. Entries with sufficient stock are automatically marked as "checked", letting you quickly see if you have all the materials needed for a build.

### 3. Right-Click to Open Clipboard

In any inventory, crafting table, or container screen, **right-clicking** a clipboard item opens the clipboard screen directly — no need to place it in the world. Closing the clipboard returns you to the original container screen, with full item transfer support.

### 4. Seamless Recipe Screen Navigation

When jumping from the clipboard screen to an EMI recipe screen, closing the recipe view correctly returns you to the original container (inventory, crafting table, etc.) rather than defaulting to the player inventory, maintaining a smooth browsing experience.

## Dependencies

| Mod | Notes |
|-----|-------|
| Create | Provides the Clipboard feature |
| EMI | Item and recipe viewer |
| NeoForge | Mod loader (1.21.1) |

## FAQ

**Q: Does this mod need to be installed on the server?**

A: No. This is a client-only mod — install it on the client side only.

**Q: Is it compatible with JEI?**

A: This mod is built specifically for EMI. If you use JEI, please look for a comparable alternative.

**Q: Does it conflict with other Create add-ons?**

A: The mod only mixins into the clipboard screen with a small footprint, so conflicts with other add-ons are unlikely.
