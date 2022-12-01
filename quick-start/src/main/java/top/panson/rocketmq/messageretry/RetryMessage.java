package top.panson.rocketmq.messageretry;

/**
 * @create 2022-12-01 12:52
 * @Author: Panson
 */
public class RetryMessage {

    public static final String TOPIC = "RETRY_MESSAGE";

    private Integer id;

    public RetryMessage(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RetryMessage() {
    }

}
