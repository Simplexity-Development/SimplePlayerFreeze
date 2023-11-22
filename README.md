# Simple Player Freeze

A simple plugin that allows freezing and unfreezing players.

### Permissions:
- spf.freeze:
  - Allows a user to freeze another
- spf.unfreeze:
  - Allows a user to unfreeze someone who is frozen
- spf.notify:
  - Notifies this user when a frozen player logs in
- spf.bypass:
  - Prevents this user from being frozen
- spf.reload:
  - Allows this user to reload the configuration

### Commands:
- /freeze <player>
  - Freezes a player, or unfreezes them if they are frozen
- /unfreeze <player>
  - unfreezes a frozen player
- /spfreload 
  - Reloads the configuration

### Default configuration

```yml
# Should freezing persist between restarts?
freeze-persist: true
# Should someone glow when they are frozen?
freeze-glow:  true
# Should someone be force-dismounted when frozen?
freeze-dismount: true
# Should someone be force-set to fly when frozen? This will prevent them from having weird glitches with their client
# Constantly trying to force them down and the server constantly re-setting them, however I have not tested this extensively
# With people who know how to abuse glitches. As such it is false by default
freeze-flight: false
# Should the user be invulnerable while they're frozen? (prevents issues like drowning if frozen while underwater)
freeze-invulnerability: true
# Which things should be prevented when someone is frozen?
prevent-movement: true
prevent-interact: true
prevent-chat: true
prevent-xp-pickup: true
prevent-item-pickup: true
prevent-item-drop: true
prevent-item-use: true
prevent-hotbar-switch: true
prevent-commands: true
prevent-crafting: true
whitelisted-commands:
  - /msg
  - /r
#-------------------- [Locale] --------------------
prefix: "<green><bold>[</bold><yellow>SPF</yellow><bold>]</bold></green> "
no-permission: "<red>You do not have permission to run this command!</red>"
no-player: "<red>You did not provide a valid player, please check your spelling and try again</red>"
freeze-message: "<green><name></green> <aqua>has been frozen</aqua>"
unfreeze-message: "<green><name></green> <yellow>has been unfrozen</yellow>"
reload-message: "<gold>The Simple Player Freeze config has been reloaded</gold>"
have-been-frozen: "<red><bold>[NOTICE]</bold></red> <gray>You have been frozen. You cannot move, interact, or chat"
have-been-unfrozen: "<green><bold>[NOTICE]</bold></green> <gray>You have been unfrozen. You can move, interact, and chat again."
cannot-chat: "You are frozen and cannot chat"
cannot-use-command: "You are frozen and cannot use that command"
login-message: "<red><bold>[NOTICE]</bold></red> <gray>You were frozen during a previous session. You cannot move, interact, or chat"
login-notif: "<dark_gray><bold><name></bold></dark_gray> <gray>has logged in. They were frozen during a previous session."
login-notif-now-unfrozen: "<dark_gray><bold><name></bold></dark_gray> <gray>has logged in. They were frozen during a previous session. They will now be unfrozen."
cannot-be-frozen: "<red>That player cannot be frozen!</red>"
not-frozen: "<red>That player is not frozen!</red>"
```