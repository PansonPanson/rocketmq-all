package top.panson.rocketmq.simplemessage;

/**
 * @create 2022-11-29 15:45
 * @Author: Panson
 */
public class SimpleMessage {

    public static final String TOPIC = "SIMPLE_MESSAGE";

    private Integer id;

    public SimpleMessage(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SimpleMessage() {
    }
}
