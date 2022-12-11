package top.panson.rocketmq.order;

/**
 * @create 2022-11-30 14:07
 * @Author: Panson
 */
public class OrderMessage {

    public final static String TOPIC = "ORDER_MESSAGE";

    private Integer id;

    public OrderMessage(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderMessage() {
    }
}
