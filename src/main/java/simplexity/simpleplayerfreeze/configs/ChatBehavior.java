package simplexity.simpleplayerfreeze.configs;

import org.jetbrains.annotations.Nullable;

public enum ChatBehavior {
    NO_CHANGE(0),
    FULL_MUTE(1),
    SHADOW_MUTE(2);

    private final int number;

    private ChatBehavior(int number){
        this.number = number;
    }

    public int getNumber(){
        return number;
    }

    @Nullable
    public static ChatBehavior getBehaviorFromInt(int value){
        for (ChatBehavior behavior : ChatBehavior.values()) {
            if (behavior.getNumber() == value) return behavior;
        }
        return null;
    }
}
