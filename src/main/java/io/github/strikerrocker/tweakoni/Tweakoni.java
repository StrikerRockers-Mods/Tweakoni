package io.github.strikerrocker.tweakoni;

import io.github.strikerrocker.tweakoni.events.Events;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.item.block.BlockItem;
import net.minecraft.potion.Potion;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Tweakoni implements ModInitializer {
    public static BlockEntityType BLOCKER;
    public static Potion HASTE;
    public static Potion STRONG_HASTE;

    public static void setFinalStatic(Field field, Object newValue) throws Exception {
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, newValue);
    }

    @Override
    public void onInitialize() {
        Events.ENTITY_UPDATE_EVENT.register((entity, world) -> {
            if (!world.isClient) {
                BlockPos pos = new BlockPos(entity.x, entity.y, entity.z);
                if (world.getBlockState(pos.down()).getBlock() instanceof MagmaBlock && entity instanceof PlayerEntity) {
                    if (entity.isSneaking()) {
                        entity.damage(DamageSource.HOT_FLOOR, 1.0F);
                    }
                }
            }
        });
        HASTE = Registry.register(Registry.POTION, "haste", new Potion("haste", new StatusEffectInstance(StatusEffects.HASTE, 4000, 0, true, true)));
        STRONG_HASTE = Registry.register(Registry.POTION, "strong_haste", new Potion("strong.haste", new StatusEffectInstance(StatusEffects.HASTE, 4000, 1, true, true)));
        //BLOCKER = Registry.register(Registry.BLOCK_ENTITY, new Identifier("tweakoni", "blocker"), BlockEntityType.Builder.create(BlockerBlockEntity::new).method_11034(null));
        //Registry.register(Registry.BLOCK,new Identifier("tweakoni","blocker"),new SpawnBlockerBlock(FabricBlockSettings.of(Material.EARTH).build()));
        try {
            System.out.println("Registry Replacing Sea lantern");
            setFinalStatic(Blocks.class.getField("SEA_LANTERN"), Registry.register(Registry.BLOCK, new Identifier("minecraft", "sea_lantern"), new Block(FabricBlockSettings.of(Material.EARTH, MaterialColor.QUARTZ).strength(0.3f, 0.3f).sounds(BlockSoundGroup.GLASS).lightLevel(15).build())));
            setFinalStatic(Items.class.getField("field_8305"), Registry.register(Registry.ITEM, new Identifier("minecraft", "sea_lantern"), new BlockItem(Blocks.SEA_LANTERN, new Item.Settings().itemGroup(ItemGroup.BUILDING_BLOCKS))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}