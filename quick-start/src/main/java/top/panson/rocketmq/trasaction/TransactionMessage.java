package top.panson.rocketmq.trasaction;

/**
 * @create 2022-11-30 14:07
 * @Author: Panson
 */
public class TransactionMessage {

    public final static String TOPIC = "Transaction_MESSAGE";

    private Integer id;

    public TransactionMessage(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TransactionMessage() {
    }
}
