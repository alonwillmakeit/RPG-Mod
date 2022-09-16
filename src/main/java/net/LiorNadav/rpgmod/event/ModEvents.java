package net.LiorNadav.rpgmod.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.item.ModItems;
import net.LiorNadav.rpgmod.networking.ModMessages;
import net.LiorNadav.rpgmod.networking.packet.*;
import net.LiorNadav.rpgmod.util.ModItemProperties;
import net.LiorNadav.rpgmod.weapon_leveling_system.archer.bow.PlayerBow;
import net.LiorNadav.rpgmod.weapon_leveling_system.archer.bow.PlayerBowProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.archer.slingshot.PlayerSlingshot;
import net.LiorNadav.rpgmod.weapon_leveling_system.archer.slingshot.PlayerSlingshotProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.battle_axe.PlayerBattleAxe;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.battle_axe.PlayerBattleAxeProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.broadsword.PlayerBroadsword;
import net.LiorNadav.rpgmod.villager.ModVillagers;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.broadsword.PlayerBroadswordProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.knife.PlayerKnife;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.knife.PlayerKnifeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = RPGMod.MOD_ID)
public class ModEvents {

    //------------------------------------------Trades----------------------------------------------
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event){
        if(event.getType() == ModVillagers.DARK_MAGE.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.AZURITE_DUST.get(), 1);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(ModItems.AZURITE_INGOT.get(), 1),
                    stack,10,8,0.02F));
        }

        if(event.getType() == ModVillagers.DARK_MAGE.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.ADAMANTITE_DUST.get(), 1);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(ModItems.ADAMANTITE_INGOT.get(), 1),
                    stack,10,8,0.02F));
        }

        if(event.getType() == ModVillagers.DARK_MAGE.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.JADEITE_DUST.get(), 1);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(ModItems.JADEITE_INGOT.get(), 1),
                    stack,10,8,0.02F));
        }
    }



    //------------------------------------------Knife Events----------------------------------------------
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event){
        if(!event.getObject().getCapability(PlayerKnifeProvider.PLAYER_KNIFE).isPresent()){
            event.addCapability(new ResourceLocation(RPGMod.MOD_ID, "properties_knife_levels"), new PlayerKnifeProvider());
        }
        if(!event.getObject().getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).isPresent()){
            event.addCapability(new ResourceLocation(RPGMod.MOD_ID, "properties_broadsword_levels"), new PlayerBroadswordProvider());
        }
        if(!event.getObject().getCapability(PlayerBattleAxeProvider.PLAYER_BATTLE_AXE).isPresent()){
            event.addCapability(new ResourceLocation(RPGMod.MOD_ID, "properties_battle_axe_levels"), new PlayerBattleAxeProvider());
        }
        if(!event.getObject().getCapability(PlayerSlingshotProvider.PLAYER_SLINGSHOT).isPresent()){
            event.addCapability(new ResourceLocation(RPGMod.MOD_ID, "properties_slingshot_levels"), new PlayerSlingshotProvider());
        }
        if(!event.getObject().getCapability(PlayerBowProvider.PLAYER_BOW).isPresent()){
            event.addCapability(new ResourceLocation(RPGMod.MOD_ID, "properties_bow_levels"), new PlayerBowProvider());
        }
    }
    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event){
        if (event.isWasDeath()){
            event.getOriginal().getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
            event.getOriginal().getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
            event.getOriginal().getCapability(PlayerBattleAxeProvider.PLAYER_BATTLE_AXE).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerBattleAxeProvider.PLAYER_BATTLE_AXE).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
            event.getOriginal().getCapability(PlayerBowProvider.PLAYER_BOW).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerBowProvider.PLAYER_BOW).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
            event.getOriginal().getCapability(PlayerSlingshotProvider.PLAYER_SLINGSHOT).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerSlingshotProvider.PLAYER_SLINGSHOT).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }
    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event){
        event.register(PlayerKnife.class);
        event.register(PlayerBroadsword.class);
        event.register(PlayerBattleAxe.class);
        event.register(PlayerSlingshot.class);
        event.register(PlayerBow.class);
    }
    //---------------------------------------General Events----------------------------------------------
    @SubscribeEvent
    public static void onEnemyHit(LivingHurtEvent event){
        Entity player = event.getSource().getEntity();
        if (player instanceof Player){
            String mainItemName = ((Player) player).getMainHandItem().getItem().toString();
            switch(mainItemName){

                //---------------------------Starter Tier-------------------------------//
                case "starter_knife":
                    player.getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(knifeExperience -> {
                        knifeExperience.addExperience((int)event.getAmount());
                        ModMessages.sendToServer(new KnifeLevelC2SPacket());
                    });
                    break;
                case "starter_slingshot":
                    player.getCapability(PlayerSlingshotProvider.PLAYER_SLINGSHOT).ifPresent(slingshotExperience -> {
                        slingshotExperience.addExperience((int)event.getAmount());
                        ModMessages.sendToServer(new SlingshotLevelC2SPacket());
                    });
                    break;
                //---------------------------Beginner Tier-------------------------------//
                case "beginner_broadsword":
                    player.getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(knifeExperience -> {
                        if (knifeExperience.getKnifeLevel() == 10){
                            player.getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).ifPresent(broadswordExperience -> {
                                broadswordExperience.addExperience((int)event.getAmount());
                                ModMessages.sendToServer(new BroadswordLevelC2SPacket());
                            });
                        }
                    });
                    break;
                case "beginner_battle_axe":
                    player.getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(knifeExperience -> {
                        if (knifeExperience.getKnifeLevel() == 10){
                            player.getCapability(PlayerBattleAxeProvider.PLAYER_BATTLE_AXE).ifPresent(battleAxeExperience -> {
                                battleAxeExperience.addExperience((int)event.getAmount());
                                ModMessages.sendToServer(new BattleAxeLevelC2SPacket());
                            });
                        }
                    });
                    break;
                case "beginner_bow":
                    player.getCapability(PlayerSlingshotProvider.PLAYER_SLINGSHOT).ifPresent(slingshotExperience -> {
                        if (slingshotExperience.getSlingshotLevel() == 10){
                            player.getCapability(PlayerBowProvider.PLAYER_BOW).ifPresent(bowExperience -> {
                                bowExperience.addExperience((int)event.getAmount());
                                ModMessages.sendToServer(new BowLevelC2SPacket());
                            });
                        }
                    });
                    break;

                //---------------------------Advanced Tier-------------------------------//
                case "advanced_broadsword":
                    player.getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(knifeExperience -> {
                        if (knifeExperience.getKnifeLevel() == 10){
                            player.getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).ifPresent(broadswordExperience -> {
                                if (broadswordExperience.getTier() >= 1) {
                                    broadswordExperience.addExperience((int) event.getAmount());
                                    ModMessages.sendToServer(new BroadswordLevelC2SPacket());
                                }
                            });
                        }
                    });
                    break;

                case "advanced_battle_axe":
                    player.getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(knifeExperience -> {
                        if (knifeExperience.getKnifeLevel() == 10){
                            player.getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).ifPresent(battleAxeExperience -> {
                                if (battleAxeExperience.getTier() >= 1) {
                                    battleAxeExperience.addExperience((int) event.getAmount());
                                    ModMessages.sendToServer(new BattleAxeLevelC2SPacket());
                                }
                            });
                        }
                    });
                    break;
                //---------------------------Superior Tier-------------------------------//
                case "superior_broadsword":
                    player.getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(knifeExperience -> {
                        if (knifeExperience.getKnifeLevel() == 10){
                            player.getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).ifPresent(broadswordExperience -> {
                                if (broadswordExperience.getTier() >= 2) {
                                    broadswordExperience.addExperience((int) event.getAmount());
                                    ModMessages.sendToServer(new BroadswordLevelC2SPacket());
                                }
                            });
                        }
                    });
                    break;
                case "superior_battle_axe":
                    player.getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(knifeExperience -> {
                        if (knifeExperience.getKnifeLevel() == 10){
                            player.getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).ifPresent(battleAxeExperience -> {
                                if (battleAxeExperience.getTier() >= 2) {
                                    battleAxeExperience.addExperience((int) event.getAmount());
                                    ModMessages.sendToServer(new BattleAxeLevelC2SPacket());
                                }
                            });
                        }
                    });
                    break;
                //---------------------------Dev Tools-------------------------------//
                case "apple":
                    player.getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(knifeExperience -> {
                        knifeExperience.addExperience(1000);
                        ModMessages.sendToServer(new KnifeLevelC2SPacket());
                    });
                    player.getCapability(PlayerSlingshotProvider.PLAYER_SLINGSHOT).ifPresent(slingshotExperience -> {
                        slingshotExperience.addExperience(1000);
                        ModMessages.sendToServer(new SlingshotLevelC2SPacket());
                    });
                    player.getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).ifPresent(broadswordExperience -> {
                        broadswordExperience.addExperience(1000);
                        ModMessages.sendToServer(new BroadswordLevelC2SPacket());
                    });
                    break;
                default:
                    break;
            }
        }
    }

}
