import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Author chenLiang
 * @Date 2022/5/30 10:19
 */
public class emojiTest {
    public static void main(String[] args) {
        String str = "An  awesome  string with a few  emojis!";
        Collection<Emoji> collection = new ArrayList<Emoji>();
        collection.add(EmojiManager.getForAlias("wink")); // This is

        System.out.println(EmojiParser.removeAllEmojis(str));
        System.out.println(EmojiParser.removeAllEmojisExcept(str, collection));
        System.out.println(EmojiParser.removeEmojis(str, collection));
    }
}
