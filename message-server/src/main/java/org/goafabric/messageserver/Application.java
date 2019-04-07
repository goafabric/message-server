
package org.goafabric.messageserver;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class Application {

    public static void main(String[] args) {
        System.out.println("yo");
    }

}
