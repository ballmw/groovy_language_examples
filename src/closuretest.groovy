import org.junit.Test;



class ClosureTest {
    def println = {  String param ->
        assert param == 'Hello World'
        print param + '\n'
    }

    @Test
    void helloWorld() {
        println 'Hello World'
    }

    @Test
    void goodbyeWorld() {
        println 'Goodbye World'
    }

    @Test
    void helloWorldClosure() {
        def helloWorld = { println 'Hello World2' }
        helloWorld()
    }

    @Test
    void implicitVariables() {
        def clos = {
            print it
            print '\n'
            assert it == 'this is it'
        }
        clos( "this is it" )
    }

    @Test
    void implicitThis() {
        def clos = {
            print this.class.name + '\n'
            print delegate.class.name + '\n'
            def nestedClos = { print owner.class.name + '\n' }
            nestedClos()
        }
        clos()
    }

    @Test
    void closureParameters() {
        def list = ['a', 'b', 'c', 'd']
        def newList = []

        list.collect( newList ) { it.toUpperCase() }

        assert newList == ['A', 'B', 'C', 'D']
        print newList
    }

    @Test
    void closureDef() {
        def list = ['a', 'b', 'c', 'd']
        def newList = []

        def clos = { it.toUpperCase() }
        list.collect( newList, clos )

        assert newList == ["A", "B", "C", "D"]
    }
}
