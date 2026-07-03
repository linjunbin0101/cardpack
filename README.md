# 卡包项目分析文档

## 1. 项目概述

本项目是一个基于 **Android Jetpack Compose** 开发的卡包应用，用于管理用户的各类卡片信息，包括银行卡、会员卡、身份证、社保卡、驾驶证、护照等。应用采用 **MVVM 架构模式**，结合 **Room 数据库** 实现本地数据持久化。

## 2. 技术栈

| 分类 | 技术 | 版本 |
|------|------|------|
| 编程语言 | Kotlin | 2.0.21 |
| UI框架 | Jetpack Compose | 2024.12.01 (BOM) |
| 数据库 | Room | 2.6.1 |
| 导航 | Navigation Compose | 2.8.4 |
| 构建工具 | Gradle | 9.1 |
| Android Gradle Plugin | AGP | 8.13.0 |
| 最低SDK | Android | API 28 |

## 3. 项目结构

```
card/
├── app/
│   ├── build.gradle.kts          # 应用模块配置
│   └── src/
│       └── main/
│           ├── java/com/lins/card/
│           │   ├── CardApplication.kt    # 应用入口，单例管理
│           │   ├── MainActivity.kt       # 主Activity
│           │   ├── data/                 # 数据层
│           │   │   ├── local/            # Room数据库相关
│           │   │   │   ├── CardDao.kt
│           │   │   │   └── CardDatabase.kt
│           │   │   ├── model/            # 数据模型
│           │   │   │   ├── Card.kt
│           │   │   │   └── CardCategory.kt
│           │   │   └── repository/       # 数据仓库
│           │   │       └── CardRepository.kt
│           │   └── ui/                   # 界面层
│           │       ├── components/       # 可复用组件
│           │       │   ├── CardItem.kt
│           │       │   ├── CategoryFilter.kt
│           │       │   ├── EmptyState.kt
│           │       │   └── SearchBar.kt
│           │       ├── navigation/       # 导航配置
│           │       │   └── Navigation.kt
│           │       ├── screens/          # 页面
│           │       │   ├── CardEditScreen.kt
│           │       │   └── HomeScreen.kt
│           │       ├── theme/            # 主题配置
│           │       │   ├── Color.kt
│           │       │   ├── Theme.kt
│           │       │   └── Typography.kt
│           │       └── viewmodel/        # 视图模型
│           │           └── CardViewModel.kt
│           └── res/                      # 资源文件
├── build.gradle.kts                      # 根项目配置
└── settings.gradle.kts                   # 项目设置
```

## 4. 核心组件分析

### 4.1 数据层

#### 4.1.1 数据模型

**Card.kt** (`app/src/main/java/com/lins/card/data/model/Card.kt`)

卡片实体类，包含以下字段：

| 字段 | 类型 | 说明 |
|------|------|------|
| id | Long | 主键，自动生成 |
| name | String | 卡片名称（必填） |
| category | String | 卡片分类 |
| holderName | String | 持卡人/卡号 |
| note | String | 备注信息 |
| frontImagePath | String | 正面图片路径 |
| backImagePath | String | 背面图片路径 |
| imagePaths | String | 多张图片路径（用 `|||` 分隔） |
| color | Long | 卡片颜色 |
| isFavorite | Boolean | 是否收藏 |
| createdAt | Long | 创建时间 |
| updatedAt | Long | 更新时间 |
| favoriteAt | Long | 收藏时间 |

**CardCategory.kt** (`app/src/main/java/com/lins/card/data/model/CardCategory.kt`)

卡片分类枚举：

| 枚举值 | 显示名称 | 图标 |
|--------|----------|------|
| BANK | 银行卡 | credit_card |
| MEMBER | 会员卡 | card_membership |
| ID | 身份证 | badge |
| SOCIAL | 社保卡 | health_and_safety |
| DRIVER | 驾驶证 | drive_eta |
| PASSPORT | 护照 | flight |
| OTHER | 其他 | more_horiz |

#### 4.1.2 数据库操作

**CardDao.kt** (`app/src/main/java/com/lins/card/data/local/CardDao.kt`)

数据访问对象，提供以下查询方法：

| 方法 | 功能 |
|------|------|
| getAllCards() | 获取所有卡片，按创建时间降序 |
| getCardsByCategory(category) | 按分类获取卡片 |
| searchCards(query) | 搜索卡片（名称或备注） |
| getFavoriteCards() | 获取收藏的卡片 |
| getCardById(id) | 根据ID获取单张卡片 |
| insertCard(card) | 插入卡片 |
| updateCard(card) | 更新卡片 |
| deleteCard(card) | 删除卡片 |
| deleteCardById(id) | 根据ID删除卡片 |
| getCardCount() | 获取卡片总数 |
| getCardCountByCategory(category) | 获取指定分类的卡片数量 |

**CardDatabase.kt** (`app/src/main/java/com/lins/card/data/local/CardDatabase.kt`)

Room 数据库定义：
- 版本：4
- 实体：`Card`
- 使用 `fallbackToDestructiveMigration()` 策略

#### 4.1.3 数据仓库

**CardRepository.kt** (`app/src/main/java/com/lins/card/data/repository/CardRepository.kt`)

数据仓库层，封装对 `CardDao` 的访问，提供统一的数据接口给 ViewModel。

### 4.2 视图模型层

**CardViewModel.kt** (`app/src/main/java/com/lins/card/ui/viewmodel/CardViewModel.kt`)

主页面视图模型，管理卡片列表状态：

- `uiState`: 持有卡片列表数据，收藏卡片优先显示
- `addCard()`: 添加卡片
- `updateCard()`: 更新卡片
- `deleteCard()`: 删除卡片
- `toggleFavorite()`: 切换收藏状态

**CardEditViewModel** (内嵌于 `CardEditScreen.kt`)

编辑页面视图模型，管理卡片编辑状态：

- `state`: 编辑状态（名称、分类、持有人、备注、图片、收藏）
- `loadCard(id)`: 加载已有卡片数据
- `toCard()`: 将状态转换为 Card 对象
- `isValid()`: 验证表单有效性

### 4.3 界面层

#### 4.3.1 页面

**HomeScreen.kt** (`app/src/main/java/com/lins/card/ui/screens/HomeScreen.kt`)

首页，展示卡片列表：
- Scaffold 布局，顶部标题栏（点击可添加卡片）
- 空状态显示
- 卡片列表（带淡入动画）
- 长按卡片可切换收藏

**CardEditScreen.kt** (`app/src/main/java/com/lins/card/ui/screens/CardEditScreen.kt`)

编辑/添加卡片页面：
- 表单字段：名称、分类、持卡人、备注
- 图片上传与管理（支持多张图片）
- 图片预览（支持缩放拖拽）
- 收藏按钮
- 删除确认对话框

#### 4.3.2 组件

| 组件 | 功能 |
|------|------|
| CardItem | 卡片列表项，显示图标、名称、持有人、分类、收藏状态 |
| CategoryFilter | 分类筛选器，包含收藏和所有分类选项 |
| SearchBar | 搜索输入框，支持清除 |
| EmptyState | 空状态提示，带添加按钮 |

#### 4.3.3 导航

**Navigation.kt** (`app/src/main/java/com/lins/card/ui/navigation/Navigation.kt`)

定义两个页面路由：
- `home`: 首页
- `card_edit/{cardId}`: 编辑页面（cardId=0 表示新建）

### 4.4 主题

**Color.kt** (`app/src/main/java/com/lins/card/ui/theme/Color.kt`)

定义应用颜色体系：

| 颜色变量 | 值 | 用途 |
|----------|-----|------|
| Primary | #2196F3 | 主色调（蓝色） |
| PrimaryVariant | #1976D2 | 主色调变体 |
| Secondary | #03DAC6 | 次要色调 |
| Background | #FAFAFA | 背景色 |
| Surface | #FFFFFF | 表面色（卡片背景） |
| Outline | #79747E | 边框/提示文字 |
| CategoryBank | #4CAF50 | 银行卡分类色 |
| CategoryMember | #FF9800 | 会员卡分类色 |
| CategoryId | #2196F3 | 身份证分类色 |
| CategorySocial | #E91E63 | 社保卡分类色 |
| CategoryDriver | #9C27B0 | 驾驶证分类色 |
| CategoryPassport | #00BCD4 | 护照分类色 |
| CategoryOther | #607D8B | 其他分类色 |

**Typography.kt** (`app/src/main/java/com/lins/card/ui/theme/Typography.kt`)

定义应用字体样式，基于 Material Design 3 标准：

| 样式 | 字号 | 字重 | 行高 |
|------|------|------|------|
| displayLarge | 57sp | Normal | 64sp |
| displayMedium | 45sp | Normal | 52sp |
| displaySmall | 36sp | Normal | 44sp |
| headlineLarge | 32sp | Normal | 40sp |
| headlineMedium | 28sp | Normal | 36sp |
| headlineSmall | 24sp | Normal | 32sp |
| titleLarge | 22sp | Medium | 28sp |
| titleMedium | 16sp | Medium | 24sp |
| titleSmall | 14sp | Medium | 20sp |
| bodyLarge | 16sp | Normal | 24sp |
| bodyMedium | 14sp | Normal | 20sp |
| bodySmall | 12sp | Normal | 16sp |
| labelLarge | 14sp | Medium | 20sp |
| labelMedium | 12sp | Medium | 16sp |
| labelSmall | 11sp | Medium | 16sp |

**Theme.kt** (`app/src/main/java/com/lins/card/ui/theme/Theme.kt`)

应用主题入口，配置 Light 配色方案和 Typography。

## 5. 核心功能

### 5.1 卡片管理

- **添加卡片**: 输入名称、选择分类、填写持有人信息、添加备注、上传图片
- **编辑卡片**: 修改已有卡片的所有信息
- **删除卡片**: 弹出确认对话框，确认后删除
- **查看卡片**: 点击卡片进入编辑页面查看详情

### 5.2 分类管理

支持7种卡片分类：银行卡、会员卡、身份证、社保卡、驾驶证、护照、其他，每种分类有独立的颜色标识。

### 5.3 收藏功能

- 长按卡片可快速收藏/取消收藏
- 收藏的卡片在列表顶部优先显示
- 编辑页面可切换收藏状态

### 5.4 图片管理

- 支持添加多张卡片图片
- 图片存储在应用私有目录（`/files/card_images/`）
- 图片预览支持缩放和拖拽
- 可删除已上传的图片

### 5.5 搜索功能

支持按卡片名称和备注进行模糊搜索。

## 6. 架构设计

### 6.1 MVVM 架构

```
┌─────────────────────────────────────────────────────────────┐
│                        UI Layer                             │
│  HomeScreen ─── CardEditScreen ─── Components              │
└───────────────────┬─────────────────────────────────────────┘
                    │ observe
                    ▼
┌─────────────────────────────────────────────────────────────┐
│                     ViewModel Layer                         │
│  CardViewModel ─── CardEditViewModel                        │
└───────────────────┬─────────────────────────────────────────┘
                    │ call
                    ▼
┌─────────────────────────────────────────────────────────────┐
│                     Repository Layer                        │
│              CardRepository                                 │
└───────────────────┬─────────────────────────────────────────┘
                    │ access
                    ▼
┌─────────────────────────────────────────────────────────────┐
│                       Data Layer                            │
│  CardDao ─── CardDatabase ─── Card/CardCategory            │
└─────────────────────────────────────────────────────────────┘
```

### 6.2 数据流

1. **UI 事件**: 用户操作（点击、输入等）
2. **ViewModel**: 处理业务逻辑，调用 Repository
3. **Repository**: 封装数据访问，调用 DAO
4. **DAO**: 执行数据库操作
5. **Flow**: 数据变化通过 Flow 自动通知 ViewModel
6. **UI 更新**: ViewModel 通过 StateFlow 暴露状态，UI 自动更新

## 7. 构建配置

### 7.1 签名配置

```kotlin
signingConfigs {
    create("release") {
        storeFile = rootProject.file("comlinscard")
        storePassword = "comlinscard"
        keyAlias = "comlinscard"
        keyPassword = "comlinscard"
    }
}
```

### 7.2 构建类型

**Release**:
- 开启代码混淆 (`minifyEnabled = true`)
- 开启资源压缩 (`shrinkResources = true`)
- 使用 release 签名配置

### 7.3 依赖配置

| 依赖 | 用途 |
|------|------|
| androidx.core.ktx | Android Core KTX |
| androidx.activity.compose | Compose 活动支持 |
| androidx.compose.bom | Compose 物料清单 |
| androidx.ui | Compose UI 核心 |
| androidx.material3 | Material Design 3 |
| room.runtime/ktx/compiler | Room 数据库 |
| navigation.compose | Compose 导航 |
| lifecycle.viewmodel.compose | ViewModel Compose 集成 |
| biometric | 生物识别支持 |

## 8. 安全性

- **图片存储**: 图片保存在应用私有目录（`/files/card_images/`），其他应用无法访问
- **数据库**: 使用 Room 本地数据库，数据存储在应用私有目录
- **代码混淆**: Release 构建启用 ProGuard 混淆
- **权限声明**: 已在 AndroidManifest.xml 中声明必要权限

### 8.1 应用权限

| 权限 | 用途 |
|------|------|
| `READ_EXTERNAL_STORAGE` | 读取外部存储（兼容旧版Android） |
| `READ_MEDIA_IMAGES` | 读取媒体图片（Android 13+） |
| `USE_BIOMETRIC` | 生物识别认证（已声明但尚未实现相关功能） |
| `VIBRATE` | 振动反馈 |

## 9. 性能优化

- **Flow 异步数据流**: 数据库查询使用 Flow，支持响应式数据更新
- **图片压缩**: `BitmapFactory.Options.inSampleSize = 2` 降低内存占用
- **分页加载**: 列表使用 LazyColumn，按需加载
- **动画优化**: 使用 Compose 内置动画，性能良好

## 10. 待优化点

1. **搜索功能未启用**: `SearchBar` 和 `CategoryFilter` 组件已实现，但 `HomeScreen` 中未集成
2. **数据库迁移**: 当前使用 `fallbackToDestructiveMigration()`，生产环境应实现迁移逻辑
3. **图片清理**: 删除卡片时未清理关联的图片文件
4. **空状态优化**: 搜索无结果时缺少专门的空状态提示
5. **测试覆盖率**: 当前只有示例测试，缺少单元测试和集成测试
6. **生物识别功能**: 已声明 `USE_BIOMETRIC` 权限并引入 `biometric` 库，但尚未实现相关功能
7. **DataStore 未使用**: 已引入 `datastore-preferences` 依赖，但尚未在代码中使用

## 11. 总结

本项目是一个功能完整的卡包管理应用，采用现代化的 Jetpack Compose 技术栈和 MVVM 架构模式。核心功能包括卡片的增删改查、分类管理、收藏功能和图片管理。代码结构清晰，遵循单一职责原则，具有良好的可维护性和扩展性。