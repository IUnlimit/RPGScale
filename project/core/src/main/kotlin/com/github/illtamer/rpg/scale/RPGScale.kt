package com.github.illtamer.rpg.scale

import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.getDataFolder
import taboolib.common.platform.function.info
import taboolib.expansion.setupPlayerDatabase
import taboolib.module.configuration.Config
import taboolib.module.configuration.Configuration
import java.io.File

object RPGScale: Plugin() {

    @Config
    lateinit var conf: Configuration
        private set

    @Awake(LifeCycle.ENABLE)
    fun enable() {
        // 初始化数据库, 随后可直接调用 player 中的拓展函数
        setupPlayerDatabase(File(getDataFolder(), "data.sqlite"))
        info("RPGScale init success!")
    }

}