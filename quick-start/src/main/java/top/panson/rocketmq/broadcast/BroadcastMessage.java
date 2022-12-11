package top.panson.rocketmq.broadcast;

/**
 * @create 2022-11-30 14:07
 * @Author: Panson
 */
public class BroadcastMessage {

    public final static String TOPIC = "BROADCAST_MESSAGE";

    private Integer id;

    public BroadcastMessage(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BroadcastMessage() {
    }
}
