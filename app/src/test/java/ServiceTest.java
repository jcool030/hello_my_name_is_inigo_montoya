import com.seg2105app.services.Service;

import org.junit.Test;
import static org.junit.Assert.*;

public class ServiceTest {

    Service service = new Service("Test Service", 5.00);

    @Test
    public void testGetName(){
        assertEquals("Test Service", service.getName());
    }

    @Test
    public void testGetRate(){
        assertEquals(5.00, service.getRate(), 0);
    }

    @Test
    public void testSetName(){
        service.setName("New Name");
        assertEquals("New Name", service.getName());
    }

    @Test
    public void testSetRate(){
        service.setRate(10.00);
        assertEquals(10.00, service.getRate(), 0);
    }
}
