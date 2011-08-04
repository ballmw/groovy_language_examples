import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

class AssertMixins {
    
    static shouldNotBeNull(object) {
        assert object != null
    }
    
    static shouldBe(object, compareObject) {
        assert object == compareObject
    }
}



public class MixinInsantiyTest {

    @Before
    void setup()
    {
        Object.mixin AssertMixins
    }
        
    @Test
    void testShouldBe() {
        
        def name = "Michael"
        name.shouldNotBeNull()
        name.shouldBe "Michael"
        
        
    }
}