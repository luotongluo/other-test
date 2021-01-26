package refeence;

import org.apache.catalina.Group;
import org.apache.catalina.Role;
import org.apache.catalina.UserDatabase;

import java.net.Socket;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author tong.luo
 * @description MapTest
 * https://juejin.cn/post/6921347272353513486?utm_source=gold_browser_extension
 * @date 2021/1/25 17:24
 */
public class MapTest {
    private Map<Socket, User> map = new WeakHashMap<>(64);

    public static void main(String[] args) throws Exception{

        SocketManager socketManager = new SocketManager();
        Socket socket = new Socket();
        User user = new User();

        socketManager.setUser(socket,user);

        socket = null;

        System.gc();
        TimeUnit.SECONDS.sleep(2L);
        System.out.println(socketManager.isEmpty());
    }
}
