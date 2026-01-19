package dev.voxelparrot.opac_commands.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class AliasCommands {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {

        var openpacParties = dispatcher.register(
                CommandManager.literal("openpac-parties")
                        .executes(ctx -> {
                            ctx.getSource().sendFeedback(
                                    () -> Text.literal("OPAC executed command with /party"), false
                            );
                            return 1;
                        })
        );

        var openpacClaims = dispatcher.register(
                CommandManager.literal("openpac-claims")
                        .executes(ctx -> {
                            ctx.getSource().sendFeedback(
                                    () -> Text.literal("OPAC executed command with /claims"), false
                            );
                            return 1;
                        })
        );

        var openpacChat = dispatcher.register(
                CommandManager.literal("opm")
                        .executes(ctx -> {
                            ctx.getSource().sendFeedback(
                                    () -> Text.literal("OPAC sent party chat with /pc"), false
                            );
                            return 1;
                        })
        );

        dispatcher.register(CommandManager.literal("party").redirect(openpacParties));
        dispatcher.register(CommandManager.literal("claims").redirect(openpacClaims));
        dispatcher.register(CommandManager.literal("pc").redirect(openpacChat));


        dispatcher.register(
                CommandManager.literal("claim")
                        .requires(src -> src.isExecutedByPlayer())
                        .executes(ctx -> {
                            var source = ctx.getSource();
                            var dispatcher2 = source.getServer()
                                    .getCommandManager()
                                    .getDispatcher();

                            dispatcher2.execute("openpac-claims claim", source);
                            return 1;
                        })
        );

        dispatcher.register(
                CommandManager.literal("unclaim")
                        .requires(src -> src.isExecutedByPlayer())
                        .executes(ctx -> {
                            var source = ctx.getSource();
                            var dispatcher2 = source.getServer()
                                    .getCommandManager()
                                    .getDispatcher();

                            dispatcher2.execute("openpac-claims unclaim", source);
                            return 1;
                        })
        );
    }
}
