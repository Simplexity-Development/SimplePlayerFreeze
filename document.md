# SimplePlayerFreeze - Technical Documentation

## Project Overview

**SimplePlayerFreeze** is a Minecraft server plugin (Paper/Spigot) that provides comprehensive player freezing functionality for server administrators and moderators. The plugin allows staff to temporarily restrict player actions, making it ideal for moderation, investigations, and server management scenarios.

- **Version:** 2.3.0
- **Author:** Rhythmic
- **Java Version:** 21
- **API Version:** 1.20.6
- **Repository:** https://github.com/Simplexity-Development/SimplePlayerFreeze

---

## Architecture Overview

### Core Components

The plugin follows a modular architecture with clear separation of concerns:

```
SimplePlayerFreeze (Main Plugin Class)
├── Commands (Command Executors)
├── Configs (Configuration Management)
├── Events (Custom Events)
├── Freeze (Core Freeze Logic)
├── Hooks (External Plugin Integrations)
└── Listeners (Event Handlers)
```

### Key Design Patterns

1. **Singleton Pattern**: Used in `ConfigHandler` and `LocaleHandler` for centralized configuration management
2. **Event-Driven Architecture**: Leverages Bukkit's event system for reactive behavior
3. **Persistent Data Container**: Uses Minecraft's PDC for storing freeze state across sessions
4. **Hook Pattern**: Optional integrations with PlaceholderAPI and DiscordSRV

---

## Core Features

### 1. Player Freezing System

The plugin implements three types of freezing:

- **INDIVIDUAL**: Single player freeze
- **WORLD**: All players in a specific world
- **SERVER**: All players on the server
- **NONE**: Default state (not frozen)

#### Freeze Effects

When a player is frozen, the following effects can be applied (configurable):

- **Visual Effects**:
  - Glowing effect (default: enabled)
  - Flight mode (default: disabled)
  
- **Movement Restrictions**:
  - Walking speed set to 0
  - Gravity manipulation for jump prevention
  - Vehicle dismounting
  
- **Protection**:
  - Invulnerability (prevents drowning, damage)
  
- **Action Prevention**:
  - Movement and jumping
  - Block/entity interaction
  - Item pickup/drop/use
  - XP pickup
  - Inventory interaction and opening
  - Crafting
  - Hotbar switching
  - Command execution (with whitelist)
  - Combat/attacking
  - Chat (with multiple behavior options)

### 2. Chat Management System

Three chat behavior modes:

- **Mode 0 (NO_CHANGE)**: Frozen players can chat normally
- **Mode 1 (FULL_MUTE)**: Frozen players cannot send messages and receive error
- **Mode 2 (SHADOW_MUTE)**: Frozen players see their messages, but only staff with `spf.chatspy` permission can see them

#### Chat Spy Feature

Staff members can toggle visibility of shadow-muted messages using `/fspy` command.

### 3. Persistence System

Freeze states persist across server restarts and player reconnections using Minecraft's PersistentDataContainer (PDC). The following data is stored per player:

- `isfrozen`: Boolean flag indicating freeze state
- `freezetype`: Enum value storing freeze type
- `previousflyperms`: Tracks if player had flight permissions before freeze
- `freezespy`: Stores chat spy toggle state for staff

### 4. Advanced Freeze Scenarios

#### World-Based Freezing

- `/freezeall <world>`: Freezes all players in a specific world
- New players joining a frozen world are automatically frozen (configurable)
- Players teleporting into frozen worlds are automatically frozen (configurable)

#### Server-Wide Freezing

- `/freezeall`: Freezes entire server
- New logins during server freeze are automatically frozen (configurable)

---

## Commands

| Command | Permission | Description | Usage |
|---------|-----------|-------------|-------|
| `/freeze` | `spf.freeze` | Toggle freeze state of a player | `/freeze <player>` |
| `/unfreeze` | `spf.unfreeze` | Unfreeze a frozen player | `/unfreeze <player>` |
| `/freezeall` | `spf.freezeall.server` or `spf.freezeall.world` | Freeze all players (server/world) | `/freezeall [world]` |
| `/unfreezeall` | `spf.unfreezeall.server` or `spf.unfreezeall.world` | Unfreeze all players (server/world) | `/unfreezeall [world]` |
| `/fspy` | `spf.chatspy` | Toggle chat spy for shadow-muted messages | `/fspy` |
| `/spfreload` | `spf.reload` | Reload plugin configuration | `/spfreload` |

---

## Permissions

### Core Permissions

- `spf.freeze`: Allows freezing players
- `spf.unfreeze`: Allows unfreezing players
- `spf.bypass`: Makes player immune to being frozen
- `spf.notify`: Receive notifications when frozen players log in
- `spf.reload`: Allows reloading configuration
- `spf.chatspy`: View shadow-muted messages from frozen players

### Hierarchical Permissions

- `spf.freezeall`: Base permission for `/freezeall` command
  - `spf.freezeall.server`: Freeze entire server (includes base permission)
  - `spf.freezeall.world`: Freeze specific world (includes base permission)

- `spf.unfreezeall`: Base permission for `/unfreezeall` command
  - `spf.unfreezeall.server`: Unfreeze entire server
  - `spf.unfreezeall.world`: Unfreeze specific world

**Default:** All permissions default to `op`

---

## Configuration

### config.yml

```yaml
# Freeze Persistence
freeze-persist: true              # Maintain freeze state across reconnects

# Visual Effects
freeze-glow: true                 # Apply glowing effect
freeze-dismount: true             # Force dismount from vehicles
freeze-flight: false              # Enable flight mode (prevents client glitches)

# Protection
freeze-invulnerability: true      # Prevent damage while frozen

# Chat Behavior (0 = normal, 1 = full mute, 2 = shadow mute)
chat-behavior: 2
hide-from-discord: true           # Hide shadow-muted messages from DiscordSRV
console-sees-muted-messages: true # Console sees shadow-muted messages
console-notify: true              # Console receives freeze notifications

# Action Prevention
prevent-movement: true
prevent-walking: true
prevent-jumping: true
prevent-interact: true
prevent-xp-pickup: true
prevent-item-pickup: true
prevent-item-drop: true
prevent-item-use: true
prevent-hotbar-switch: true
prevent-commands: true
prevent-crafting: true
prevent-inventory-interaction: true
prevent-inventory-open: true
prevent-attacking: true

# World/Server Freeze Settings
freeze-new-logins: true           # Freeze new logins during server freeze
freeze-world-change: true         # Freeze players entering frozen worlds

# Command Whitelist
whitelisted-commands:
  - /minecraft:msg
  - /minecraft:w
  # ... (messaging commands exempt from command blocking)

# PlaceholderAPI Integration
placeholder-api-format: "<dark_gray>[Frozen]</dark_gray>"
```

### locale.yml

Fully customizable messages using MiniMessage format for text styling. Supports placeholders:
- `<name>`: Player display name
- `<world>`: World name
- `<player>`: Player name in chat
- `<message>`: Chat message content

---

## Event System

### Custom Event: PlayerFreezeEvent

Fired when a player's freeze state changes.

**Event Properties:**
- `Player player`: The affected player
- `boolean frozen`: New freeze state (true/false)
- `FreezeType freezeType`: Type of freeze being applied
- `boolean cancelled`: Cancellable event

**Usage Example:**
```java
@EventHandler
public void onPlayerFreeze(PlayerFreezeEvent event) {
    Player player = event.getPlayer();
    boolean isFrozen = event.isFrozen();
    FreezeType type = event.getFreezeType();
    
    if (type == FreezeType.SERVER) {
        // Handle server-wide freeze
    }
}
```

### Monitored Events

The plugin registers listeners for the following Bukkit events:

- `AsyncPlayerChatEvent`: Chat management
- `PlayerDropItemEvent`: Item drop prevention
- `PlayerExpChangeEvent`: XP pickup prevention
- `PlayerInteractEvent`: Block/entity interaction prevention
- `CraftItemEvent`: Crafting prevention
- `PlayerJoinEvent`: Handle frozen player logins
- `PlayerQuitEvent`: Cleanup on disconnect
- `PlayerMoveEvent`: Movement prevention
- `EntityPickupItemEvent`: Item pickup prevention
- `PlayerItemHeldEvent`: Hotbar switching prevention
- `PlayerItemConsumeEvent`: Item consumption prevention
- `PlayerCommandPreprocessEvent`: Command filtering
- `EntityDamageByEntityEvent`: Attack prevention
- `InventoryOpenEvent`: Inventory opening prevention
- `InventoryClickEvent`: Inventory interaction prevention
- `PlayerChangedWorldEvent`: World transition handling

---

## External Integrations

### PlaceholderAPI Integration

**Placeholder:** `%simpleplayerfreeze_isfrozen%`

Returns the configured format string when a player is frozen, empty otherwise.

**Implementation:** `IsFrozenPlaceholder.java`

### DiscordSRV Integration

When `hide-from-discord: true`, shadow-muted messages (chat behavior 2) are blocked from being sent to Discord.

**Implementation:** `DiscordMessageBlocker.java` and `DiscordSrvRegistration.java`

---

## Technical Implementation Details

### Freeze State Management

The `FreezeFunctionality` class handles all freeze/unfreeze operations:

```java
public static void setFrozen(Player player, FreezeType freezeType)
public static void setUnfrozen(Player player)
```

**Freeze Process:**
1. Check for bypass permission (`spf.bypass`)
2. Store freeze state in PDC
3. Apply configured visual effects
4. Apply movement restrictions
5. Send notification to player

**Unfreeze Process:**
1. Remove freeze flag from PDC
2. Restore original flight permissions
3. Reset movement speeds and gravity
4. Remove visual effects
5. Send notification to player

### Data Storage

Uses Minecraft's PersistentDataContainer with the following NamespacedKeys:

- `simpleplayerfreeze:isfrozen` (Boolean)
- `simpleplayerfreeze:freezetype` (String)
- `simpleplayerfreeze:previousflyperms` (Boolean)
- `simpleplayerfreeze:freezespy` (Boolean)

### Command Whitelisting

Commands are whitelisted to allow specific messaging commands even when frozen. This is critical for Minecraft's chat signature system to prevent player kicks.

**Important:** Removing `/minecraft:msg` and related commands from the whitelist may cause players to be kicked for missing public keys.

### World and Server Freeze Tracking

- `Util.worldFrozen`: HashMap tracking freeze state per world
- `Util.serverFrozen`: Boolean flag for server-wide freeze

---

## Build Configuration

### Maven Dependencies

1. **Paper API** (1.20.6-R0.1-SNAPSHOT) - Core Minecraft server API
2. **PlaceholderAPI** (2.11.5) - Optional placeholder integration
3. **DiscordSRV** (1.28.0) - Optional Discord integration

### Build Process

```bash
mvn clean package
```

Produces a shaded JAR with all dependencies bundled using `maven-shade-plugin`.

---

## Message Formatting

The plugin uses **MiniMessage** for text formatting, supporting:

- Color codes: `<red>`, `<green>`, `<yellow>`, etc.
- Formatting: `<bold>`, `<italic>`, `<underline>`
- Advanced: Gradients, hover events, click events

**Example:**
```yaml
prefix: "<green><bold>[</bold><yellow>SPF</yellow><bold>]</bold></green> "
```

---

## Class Structure

### Main Classes

| Class | Purpose |
|-------|---------|
| `SimplePlayerFreeze` | Main plugin class, initialization and registration |
| `FreezeFunctionality` | Core freeze/unfreeze logic |
| `FreezeType` | Enum defining freeze types |
| `ConfigHandler` | Configuration management (singleton) |
| `LocaleHandler` | Message management (singleton) |
| `Util` | Utility methods and constants |
| `PlayerFreezeEvent` | Custom event for freeze state changes |

### Command Executors

| Class | Command | Function |
|-------|---------|----------|
| `FreezePlayer` | `/freeze` | Toggle player freeze |
| `UnfreezePlayer` | `/unfreeze` | Unfreeze player |
| `FreezeAll` | `/freezeall` | Freeze world/server |
| `UnfreezeAll` | `/unfreezeall` | Unfreeze world/server |
| `FreezeSpy` | `/fspy` | Toggle chat spy |
| `ReloadConfig` | `/spfreload` | Reload configuration |

### Event Listeners

All listener classes follow the naming pattern `[Action]Listener.java` and implement Bukkit's `Listener` interface.

---

## Best Practices for Server Administrators

### Recommended Configuration

1. **Enable freeze persistence** to maintain accountability across sessions
2. **Use chat behavior 2** (shadow mute) for effective monitoring
3. **Enable console notifications** for audit logging
4. **Keep command whitelist** to prevent signature-related kicks
5. **Enable freeze-invulnerability** to prevent exploit deaths

### Performance Considerations

- The plugin uses efficient event handling with early returns
- PDC storage is lightweight and performant
- No database dependencies
- Minimal memory footprint

### Security Notes

- Players with `spf.bypass` cannot be frozen (give carefully)
- Command whitelist should be reviewed for your server's needs
- Chat behavior 2 provides the best balance of monitoring and player experience

---

## Troubleshooting

### Common Issues

1. **Players getting kicked when frozen**
   - Ensure messaging commands are in the whitelist
   - Related to Minecraft's chat signature system

2. **Flight not being removed properly**
   - Check if player has creative mode or other flight permissions
   - Plugin tracks original flight state

3. **Frozen players can still move slightly**
   - Ensure both `prevent-movement` and `prevent-walking` are enabled
   - Check for conflicting plugins

### Debug Information

Enable Bukkit's debug logging to see detailed plugin operations:
```yaml
# In bukkit.yml or paper.yml
logging:
  level: FINE
```

---

## Development Notes

### Extending the Plugin

The plugin is designed for extensibility:

1. **Custom Events**: Listen for `PlayerFreezeEvent` in your plugins
2. **API Usage**: Access freeze state via `Util.isFrozen(player)`
3. **Configuration**: Add custom config options in `ConfigHandler`
4. **Locale**: Add custom messages in `locale.yml`

### Contributing

The plugin is open source on GitHub. Contributions should:
- Follow existing code style
- Include appropriate comments
- Test with multiple Minecraft versions
- Update documentation

---

## Version History

**Current Version: 2.3.0**

Features in current version:
- Full freeze functionality with multiple types
- Comprehensive action prevention
- Chat management with 3 modes
- PlaceholderAPI integration
- DiscordSRV integration
- Persistence across sessions
- World and server-wide freezing
- Configurable effects and behaviors

---

## License

See LICENSE file in the repository.

---

## Support and Resources

- **GitHub Repository:** https://github.com/Simplexity-Development/SimplePlayerFreeze
- **Issue Tracker:** GitHub Issues
- **API Documentation:** JavaDocs (generate with `mvn javadoc:javadoc`)

---

## Conclusion

SimplePlayerFreeze is a robust, well-architected plugin that provides comprehensive player freezing functionality for Minecraft servers. Its modular design, extensive configuration options, and external integrations make it suitable for servers of all sizes, from small communities to large networks. The plugin's use of modern Java features, efficient event handling, and persistent data storage ensures reliable operation with minimal performance impact.
