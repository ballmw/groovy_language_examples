import org.junit.Test;

public class OperatorTest {

    @Test
    void caseRanges() {
        def years = 5
        def interestRate = 0;
        switch (years) {
            case 1..10: interestRate = 0.076; break;
            case 11..25: interestRate = 0.052; break;
            default: interestRate = 0.037;
        }
        assert interestRate == 0.076
    }
    
    @Test
    void starDot()
    {
        assert [1, 3, 5] == ['a', 'few', 'words']*.size()
    }
    
    @Test
    void expando()
    {
        def player = new Expando()
        player.name = "Dierk"
        player.greeting = { "Hello, my name is $name" }
        
		def name = "Mike"
		
        assert player.greeting() == "Hello, my name is Dierk" 
        player.name = "Jochen"
        assert player.greeting() == "Hello, my name is Jochen" 
    }
    
    @Test
    void gstring()
    {
        //String in " are GStrings and have special powers...
        def foxtype = 'quick'
        def foxcolor = ['b', 'r', 'o', 'w', 'n']
        assert "The $foxtype ${foxcolor.join()} fox" == "The quick brown fox"
    }
    @Test
    void elvis()
    {
        def user;
        def displayName = user ?: "Anonymous"  //more compact Elvis operator
        assert displayName == 'Anonymous'
    }
    
    @Test
    void safe()
    {
        def user 
        def streetName = user?.address?.street
        assert true
    }
    
    @Test 
    void spaceship()
    {
        assert 'a'<=>'b' == -1
    }
}
