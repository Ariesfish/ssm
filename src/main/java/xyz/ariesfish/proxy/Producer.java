package xyz.ariesfish.proxy;

// dynamic proxy implements interface
public class Producer implements IProducer {

    public void saleProduct(float money) {
        System.out.println("Sale product, and get money " + money);
    }

    public void postService(float money) {
        System.out.println("Provide service, and get money " + money);
    }
}
