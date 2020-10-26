import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws InterruptedException {// 1. Create config object
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");

        // or read config from file
//        config = Config.fromYAML(new File("config-file.yaml"));
        // 2. Create Redisson instance

        // Sync and Async API
        RedissonClient redisson = Redisson.create(config);
        RLock lock = redisson.getLock("myLock");
        try {
            lock.lock();
            lock.lock();
            System.out.println("into lock");
            Thread.sleep(10000);
        } finally {
            System.out.println("release lock");
            lock.unlock();
            lock.unlock();
        }
        System.exit(0);
    }
}
