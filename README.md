# RPGScale

Bukkit 已经为了血条实现了压缩效果，因此本插件仅设置了玩家的 healthScale 属性来同步生命值百分比

插件默认在更新时情况额外饱食度，并根据 `MMOCore` 的最大魔法值 `MAX_MANA` 与当前魔法值 `MANA` 属性动态计算比例，实时更新玩家的饱食度 `foodLevel`

## 构建发行版本

发行版本用于正常使用, 不含 TabooLib 本体。

```
./gradlew build
```

## 构建开发版本

开发版本包含 TabooLib 本体, 用于开发者使用, 但不可运行。

```
./gradlew taboolibBuildApi -PDeleteCode
```

> 参数 -PDeleteCode 表示移除所有逻辑代码以减少体积。
