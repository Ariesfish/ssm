package xyz.ariesfish.cglib;

// Code Generation Library
// dynamic proxy extends class
public class Producer {

    public void saleProduct(float money) {
        System.out.println("Sale product, and get money " + money);
    }

    public void postService(float money) {
        System.out.println("Provide service, and get money " + money);
    }
}
