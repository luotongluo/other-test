package refeence;

import java.lang.ref.ReferenceQueue;
import java.net.Socket;

/**
 * @author tong.luo
 * @description WeakReferencce
 * @date 2021/1/25 17:35
 */
public class WeakReferencce<S> {
    private static ReferenceQueue<Socket> referenceQueue = new ReferenceQueue<>();

    public static void main(String[] args) throws Exception {
        SocketManager socketManager = new SocketManager();

    }
}
