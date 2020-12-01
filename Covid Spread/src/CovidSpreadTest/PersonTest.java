package CovidSpreadTest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import CovidSpreadSimulation.Person;

class PersonTest {

	  @Test
	    public void testPersonsize(){
	    	Person person=new Person(1500, 1500);
	    	person.getSize();
	        assertEquals(15,person.getSize(),6);
	    }
}
