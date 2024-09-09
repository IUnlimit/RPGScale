package com.github.illtamer.rpg.scale

import net.Indyuce.mmocore.MMOCore
import org.bukkit.command.CommandSender
import taboolib.common.platform.command.*
import taboolib.expansion.createHelper

@CommandHeader("RPGScale", ["scale"], permission = "RPGScale.command")
object Command {

    @CommandBody
    val main = mainCommand {
        createHelper(true)
    }

    @CommandBody
    val debug = subCommand {
        execute<CommandSender> { sender, _, _ ->
            sender.sendMessage(MMOCore.plugin.statManager.registered.toString())
        }
    }

    @CommandBody
    val reload = subCommand {
        execute<CommandSender> { sender, _, _ ->
            RPGScale.conf.reload()
            sender.sendMessage("Reload Success!")
        }
    }

}