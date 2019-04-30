package io.github.strikerrocker.tweakoni;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;

public interface EntityTickCallback {
    public static final Event<EntityTickCallback> EVENT = EventFactory.createArrayBacked(EntityTickCallback.class,
            (listeners) -> {
                return (livingEntity -> {
                    for (EntityTickCallback entityTickCallback : listeners) {
                        entityTickCallback.update(livingEntity);
                    }
                });
            }
    );

    void update(LivingEntity livingEntity);
}
