import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class MainTest {

    @Test
    @Timeout(22)
    @Disabled
    public void testExecutionTime() throws Exception {
        String[] args = {};
        Main.main(args);
    }
}
