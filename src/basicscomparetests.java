import static org.junit.Assert.*;
import org.junit.Test;

public class BasicsCompareTests {

    @Test
    public void closureParameters() {
        char[] list = new char[] { 'a', 'b', 'c', 'd' };
        char[] newList = new char[4];

        int i = 0;
        for (char c : list) {
            newList[i] = Character.toUpperCase(c);
            i++;
        }
        assert (newList == new char[] { 'A', 'B', 'C', 'D' });
        System.out.println(newList);
    }

    @Test
    public void closureParameters2() {
        Character[] list = new Character[] { Character.valueOf('a'), Character.valueOf('b'), Character.valueOf('c'),
                Character.valueOf('d') };
        Character[] newList = new Character[4];

        int i = 0;
        for (char c : list) {
            newList[i] = Character.toUpperCase(c);
            i++;
        }
        assertTrue(newList == new Character[] { new Character('A'), new Character('B'), new Character('C'),
                new Character('D') });
        System.out.println(newList);
    }

}