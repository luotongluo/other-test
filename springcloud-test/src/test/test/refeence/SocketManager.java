package refeence;


import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tong.luo
 * @description SocketManager
 * @date 2021/1/25 17:26
 */
public class SocketManager {
    private Map<Socket, User> map = new HashMap<>(64);

    public void setUser(Socket socket, User user) {
        map.put(socket, user);
    }

    public User getUser(Socket socket) {
        return map.get(socket);
    }

    public void removeUser(Socket socket) {
        map.remove(socket);
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }
}
