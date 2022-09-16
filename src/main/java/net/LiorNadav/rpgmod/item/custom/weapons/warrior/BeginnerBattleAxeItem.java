package net.LiorNadav.rpgmod.item.custom.weapons.warrior;

import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.knife.PlayerKnifeProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class BeginnerBattleAxeItem extends SwordItem {
    public BeginnerBattleAxeItem(Tier tier, int i, float v, Properties properties) {
        super(tier, i, v, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (pAttacker instanceof Player){
            Player player = ((Player)pAttacker);
            player.getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(knifeLevel -> {
                if (knifeLevel.getKnifeLevel() < 10){
                    pAttacker.setSecondsOnFire(10);
                    ((Player)pAttacker).getInventory().removeItem(pStack);
                    pAttacker.sendSystemMessage(Component.literal("You are not worthy for this kind of power!"));
                }
            });
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
