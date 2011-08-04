import org.junit.Test;
class Organization {
    String name
    List employees = []
}
class Employee {
    String name;
    Address address;
}

class Address {
    String address1
    String address2
    String city
    String state
    String zip
}
public class BuilderTest {
    
    @Test
    void builderTest() {
        def builder = new ObjectGraphBuilder()
        def company = builder.organization( name: 'ACME' ){
            3.times()
            {
                employee( name: "Mike")
                {
                    address(address1:"444 Street Rd.", city:'Mason', state:'OH')
                }
            }
            

        }

        assert company != null
        assert company.employees.size() == 3
    }
}