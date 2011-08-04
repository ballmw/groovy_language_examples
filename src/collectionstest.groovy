import org.junit.Test;

public class CollectionsTest {

    @Test
    void declaringCollections() {
        def list = [5, 6, 7, 8]
        assert list.get(2) == 7
        assert list[2] == 7
        assert list instanceof java.util.List

        def emptyList = []
        assert emptyList.size() == 0
        emptyList.add(5)
        assert emptyList.size() == 1

        emptyList<<6
		
        assert emptyList.size() == 2
    }

    @Test
    void iterateList() {
        //[1, 2, 3].each{ value -> println "Item: $value" }
        //['a', 'b', 'c'].eachWithIndex{ value, i -> println "$i: $value" }
        println ( [1, 2, 3]*.multiply(2) == [1, 2, 3].collect{ it.multiply(2) })
		println( [1, 2, 3]*.multiply(2))
    }

    @Test
    void cloneList() {
        def list1= ['a', 'b', 'c']
        def list2 = new ArrayList( list1 )
        //construct a new list, seeded with the same items as in list1
        assert list2 == list1 // == checks that each corresponding element is the same
        def list3 = list1.clone()
        assert list3 == list1
    }
    @Test
    void arrayCompare() {

        //These are strings... not chars...
        def list = ['a', 'b', 'c', 'd']
        def newList = []

        list.collect( newList ) { new String(it.toUpperCase()) }

        assert newList == ['A', 'B', 'C', 'D']
        print newList
    }

    @Test
    void otherMethods()
    {
        assert [1, 2, 3].find{ it > 1 } == 2
        assert [1, 2, 3].findAll{ it > 1 } == [2, 3]
        assert ['a', 'b', 'c', 'd', 'e'].findIndexOf{ it in ['c', 'e', 'g']} == 2
        //find first item that satisfies closure
        assert [1, 2, 3].every{ it < 5 }
        assert ! [1, 2, 3].every{ it < 3 }
        assert [1, 2, 3].any{ it > 2 }
        assert ! [1, 2, 3].any{ it > 3 }

        // sum anything with a plus() method
        assert [1, 2, 3, 4, 5, 6].sum() == 21
        assert ['a', 'b', 'c', 'd', 'e'].sum{
            it=='a'?1: it=='b'?2: it=='c'?3: it=='d'?4: it=='e'?5: 0
        } == 15
        assert ['a', 'b', 'c', 'd', 'e'].sum() == 'abcde'
        assert [['a', 'b'], ['c', 'd']].sum() == ['a', 'b', 'c', 'd']
		assert [['a', 'b'], ['c', 'd']].sum().sum() == 'abcd'
        // an initial value can be provided
        assert [].sum(1000) == 1000
        assert [1, 2, 3].sum(1000) == 1006

        assert [1, 2, 3].join('-') == '1-2-3'
        assert [1, 2, 3].inject('counting: '){ str, item -> str + item } == 'counting: 123'
        assert [1, 2, 3].inject(0){ count, item -> count + item } == 6
    }

    @Test
    void crazyListOperatorStuff()
    {
        assert ['a', 'b', 'c', 'b', 'b']- 'c' == ['a', 'b', 'b', 'b']
        //remove 'c', and return resulting list
        assert ['a', 'b', 'c', 'b', 'b']- 'b' == ['a', 'c']
        //remove all 'b', and return resulting list
        assert ['a', 'b', 'c', 'b', 'b']- ['b', 'c']== ['a']
        //remove all 'b' and 'c', and return resulting list
        assert ['a', 'b', 'c', 'b', 'b'].minus('b') == ['a', 'c']
        //equivalent method name for -
        assert ['a', 'b', 'c', 'b', 'b'].minus( ['b', 'c']) == ['a']
        def list= [1, 2, 3, 4, 3, 2, 1]
        list -= 3
        assert list == [1, 2, 4, 2, 1] //use -= to remove 3, permanently
        assert ( list -= [2, 4]) == [ 1, 1] //remove 2's and 4's, permane
    }
    
    @Test
    void sortLists()
    {
        assert [6,3,9,2,7,1,5].sort() == [1,2,3,5,6,7,9]
        
        def list= ['abc', 'z', 'xyzuvw', 'Hello', '321']
        assert list.sort{ it.size() } == ['z', 'abc', '321', 'Hello', 'xyzuvw']
        
        def list2= [7,4,-6,-1,11,2,3,-9,5,-13]
        assert list2.sort{a,b-> a.equals(b)? 0: Math.abs(a)<Math.abs(b)? -1: 1 } ==
            [-1, 2, 3, 4, 5, -6, 7, -9, 11, -13]
        def mc= [
          compare: {a,b-> a.equals(b)? 0: Math.abs(a)<Math.abs(b)? -1: 1 }
        ] as Comparator
        assert list2.sort(mc) == [-1, 2, 3, 4, 5, -6, 7, -9, 11, -13]
        
        def list3= [6,-3,9,2,-7,1,5]
        Collections.sort(list3)
        assert list3 == [-7,-3,1,2,5,6,9]
        Collections.sort(list3, mc)
        assert list3 == [1,2,-3,5,6,-7,9]
    }
    
    @Test
    void ranges()
    {
        assert 5..8 == [5,6,7,8] //includes both values
        assert 5..<8 == [5, 6, 7] //excludes specified top value
        assert ('a'..'d') == ['a','b','c','d']
        def n=0
        (1..10).each{ n += it }
        assert n == 55
    }
    
    @Test
    void sets()
    {
        def s1= [1,2,3,3,3,4] as Set,
        s2= [4,3,2,1] as Set,
        s3= new HashSet( [1,4,2,4,3,4] )
    assert s1.class == HashSet && s2.class == HashSet
        //the specific kind of set being used
    assert s1 == s2
    assert s1 == s3
    assert s2 == s3
    assert s1.asList() && s1.toList()
        //a choice of two methods to convert a set to a list
    assert ( ([] as Set) << null << null << null ) == [null] as Set
    }
}
