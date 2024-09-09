import io.izzel.taboolib.gradle.UNIVERSAL

dependencies {
    // 如果不需要跨平台，可以在此处引入 Bukkit 核心
     compileOnly("ink.ptms.core:v11903:11903:mapped")
     compileOnly("ink.ptms.core:v11903:11903:universal")

    compileOnly("io.lumine:MythicLib-dist:1.6.2-SNAPSHOT")
    compileOnly("net.Indyuce:MMOCore-API:1.12.1-SNAPSHOT")
}

// 子模块
taboolib { subproject = true }