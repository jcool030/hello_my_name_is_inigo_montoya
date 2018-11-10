import com.seg2105app.delieverable1.users.HomeOwner;

import static org.junit.Assert.*;
import org.junit.Test;


public class UserTest {

    HomeOwner user1 = new HomeOwner("user1", "1234", "user", "one", "HomeOwner");

    @Test //tests function of getUsername method in User class
    public void testGetUsername(){
        assertEquals("user1", user1.getUsername());
    }

    @Test
    public void testGetPassword(){
        assertEquals("1234", user1.getPassword());
    }

    @Test
    public void testGetFirstName(){
        assertEquals("user", user1.getFirstName());
    }

    @Test
    public void testGetLastName(){
        assertEquals("one", user1.getLastName());
    }

    @Test
    public void testGetType(){
        assertEquals("HomeOwner", user1.getType());
    }
}
