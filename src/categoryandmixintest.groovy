import org.junit.Test;

@Category(String)
class PigLatin {
    def pigLatin() {
        def firstCharacter = this[0]
        this = this[1..-1]
        this = this + firstCharacter + 'a'
    }
}

class PigLatinMixin {
    static pigLatin(string) {
        def firstCharacter = string[0]
        string = string[1..-1]
        string = string + firstCharacter + 'a'
        return string
    }
}
public class CategoryAndMixinTest {

    @Test
    void modifyStringWCategory() {
        use(PigLatin) {
            def name = 'Michael'
            assert name.pigLatin() == "ichaelMa"
        }
    }
    
    @Test
    void modifyStringWMixin() {
        String.mixin  PigLatinMixin
        def name = 'Michael'
        assert name.pigLatin() == "ichaelMa"
        
    }
}
