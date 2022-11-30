package top.panson.rocketmq.delayedmessage;

/**
 * @create 2022-11-30 14:07
 * @Author: Panson
 */
public class DelayedMessage {

    public final static String TOPIC = "DELAYED_MESSAGE";

    private Integer id;

    public DelayedMessage(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DelayedMessage() {
    }
}
