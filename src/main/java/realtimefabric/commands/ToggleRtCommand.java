package realtimefabric.commands;
import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;
import net.minecraft.world.GameRules;
import realtimefabric.ModConfig;
import realtimefabric.RealTimeMod;

public class ToggleRtCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("togglert").requires(source -> source.hasPermissionLevel(4)).executes(ctx -> {
            ModConfig.Enabled = !ModConfig.Enabled;
            Text status = ModConfig.Enabled ?
                    new LiteralText("ENABLED").setStyle(Style.EMPTY.withColor(Formatting.GREEN))
                    : new LiteralText("DISABLED").setStyle(Style.EMPTY.withColor(Formatting.RED  ));

            ctx.getSource().getWorld().getGameRules().get(GameRules.DO_DAYLIGHT_CYCLE).set(!ModConfig.Enabled, null);
            ctx.getSource().sendFeedback(new LiteralText(RealTimeMod.MOD_NAME + " is now: ").append(status), true);
            return 1;
        }));
    }
}
