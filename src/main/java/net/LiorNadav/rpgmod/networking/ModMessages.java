package net.LiorNadav.rpgmod.networking;

import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.networking.packet.BroadswordLevelC2SPacket;
import net.LiorNadav.rpgmod.networking.packet.KnifeLevelC2SPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(RPGMod.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();
        INSTANCE = net;

        net.messageBuilder(KnifeLevelC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(KnifeLevelC2SPacket::new)
                .encoder(KnifeLevelC2SPacket::toByte)
                .consumerMainThread(KnifeLevelC2SPacket::handle)
                .add();

        net.messageBuilder(BroadswordLevelC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(BroadswordLevelC2SPacket::new)
                .encoder(BroadswordLevelC2SPacket::toByte)
                .consumerMainThread(BroadswordLevelC2SPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message){
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player){
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
