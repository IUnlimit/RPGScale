package com.github.illtamer.rpg.scale

import net.Indyuce.mmocore.MMOCore
import net.Indyuce.mmocore.api.event.PlayerResourceUpdateEvent
import net.Indyuce.mmocore.api.player.profess.resource.PlayerResource
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityRegainHealthEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.info
import taboolib.expansion.releaseDataContainer
import taboolib.expansion.setupDataContainer

object Listener {

    private val conf = RPGScale.conf

    @SubscribeEvent
    fun onJoin(event: PlayerJoinEvent) {
        event.player.setupDataContainer()
        setMaxHealthScale(event.player)
    }

    @SubscribeEvent
    fun onQuit(event: PlayerQuitEvent) {
        event.player.releaseDataContainer()
        setMaxHealthScale(event.player)
    }

    @SubscribeEvent
    fun onDamage(event: EntityDamageEvent) {
        if (event.entity is Player) {
            setMaxHealthScale(event.entity as Player)
        }
    }

    // TODO 额外兼容吃东西恢复的饱食度（法力

    @SubscribeEvent
    fun onHealthRegen(event: EntityRegainHealthEvent) {
        if (event.entity is Player) {
            setMaxHealthScale(event.entity as Player)
        }
    }

    @SubscribeEvent
    fun onResourceUpdate(event: PlayerResourceUpdateEvent) {
        event.resource.let {
            info(it.name)
            if ("MANA" == it.name) {
                val maxMana = conf.getInt("max-mana").toDouble()
                event.data.let { data ->
                    // 饱食度缓冲，始终为 0
                    event.player.saturation = 0F
                    event.player.foodLevel = Math.floor(maxMana * (it.getCurrent(data) / it.getMax(data))).toInt()
                }
            } else if ("HEALTH" == it.name) {
                val maxHealth = conf.getInt("max-health").toDouble()
                event.data.let { data ->
                    if (event.player.healthScale != maxHealth && it.getMax(data) > maxHealth) {
                        // 设置爱心数量 + 刷新
                        event.player.healthScale = maxHealth
                    } else {
                        // 刷新
                        event.player.isHealthScaled = true
                    }
                }
            }
        }
    }

    fun setMaxHealthScale(player: Player) {
        val maxHealth = conf.getInt("max-health").toDouble()
        PlayerResource.HEALTH.let {
            val data = MMOCore.plugin.playerDataManager.get(player)
            if (player.healthScale != maxHealth && it.getMax(data) > maxHealth) {
                player.healthScale = maxHealth
            } else {
                player.isHealthScaled = true
            }
        }

    }

}