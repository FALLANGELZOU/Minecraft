import com.angel.core.Entity.SignCommand;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Angel_zou
 * @Date: Created in 10:30 2020/8/8
 * @Connection: ahacgn@gmail.com
 * @Description: test
 */
public class Test1 {

    public static void main(String[] args) {


        List<String> commands = Arrays.asList("/give &p 1 1 &x &y &z","&p &p 1 1 &x");
        Pattern pattern = Pattern.compile("(&.+?)");
        //System.out.println("尝试匹配!");
        for (int i = 0; i < commands.size(); i++){
            String v = commands.get(i);

            Matcher matcher = pattern.matcher(v);
            while (matcher.find()){
                String var = matcher.group(1);
                switch (var){
                    case "&p":
                        v = v.replaceFirst("&p","Angel_zou");
                        break;
                    case "&x":
                        v = v.replaceFirst("&x","1.0");
                        break;
                    case "&y":
                        v = v.replaceFirst("&y","2.0");
                        break;
                    case "&z":
                        v = v.replaceFirst("&z","3.0");
                        break;
                    default:
                        break;
                }

            }
            commands.set(i,v);
        }

        commands.forEach(System.out::println);

    }

}

