
import java.util.concurrent.*;
import java.time.*;
import java.time.format.*;

public class WorkerProcess
{
    private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd:HH:mm:ss");
    //https://adambien.blog/roller/abien/entry/sigterm_sigint_sigkill_and_java
    public static void main(String[] args) {

        var shutdownListener = new Thread(){
            public void run(){
                System.out.println("shutdown in 10s ");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {}
            }
        };
        Runtime.getRuntime().addShutdownHook(shutdownListener);

        Executors.newSingleThreadScheduledExecutor().
                scheduleAtFixedRate(() -> System.out.println(LocalDateTime.now().format(df)), 0, 2, TimeUnit.SECONDS);
        
    }
}