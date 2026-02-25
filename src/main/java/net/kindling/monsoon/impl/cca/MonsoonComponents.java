package net.kindling.monsoon.impl.cca;

import net.kindling.monsoon.impl.cca.entity.CurrencyGameComponent;
import net.kindling.monsoon.impl.cca.entity.PlayerGameComponent;
import net.kindling.monsoon.impl.cca.world.WorldGameComponent;
import net.minecraft.entity.player.PlayerEntity;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;
import org.ladysnake.cca.api.v3.world.WorldComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.world.WorldComponentInitializer;

public class MonsoonComponents implements EntityComponentInitializer, WorldComponentInitializer {
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.beginRegistration(PlayerEntity.class, PlayerGameComponent.KEY).respawnStrategy(RespawnCopyStrategy.NEVER_COPY).end(PlayerGameComponent::new);
        registry.beginRegistration(PlayerEntity.class, CurrencyGameComponent.KEY).respawnStrategy(RespawnCopyStrategy.NEVER_COPY).end(CurrencyGameComponent::new);
    }

    public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
        registry.register(WorldGameComponent.KEY, WorldGameComponent::new);
    }
}
