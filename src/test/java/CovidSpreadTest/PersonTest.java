package CovidSpreadTest;

import CovidSpreadSimulation.Person;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void getState() { Person person =new Person(1500, 1500);
        System.out.println(person.getState());
        assertEquals(Person.State.HEALTHY,person.getState());
    }

    @Test
    void setSick() {Person person =new Person(1500, 1500);
        person.setSick();
        assertEquals(Person.State.SICK,person.getState());
    }


    @Test
    void getNextX() {Person person =new Person(1500, 1500);
        person.setxVel(1);
        person.x=1;
        assertEquals(2,person.getNextX(),0);
    }

    @Test
    void getNextY() {Person person =new Person(1500, 1500);
        person.setyVel(2);
        person.y=1;
        assertEquals(3,person.getNextY(),0);
    }

    @Test
    void getSize() {Person person=new Person(1500, 1500);
        person.getSize();
        assertEquals(15,person.getSize(),6);
    }


    @Test
    void update() {
        Person person=  new Person(100,100);
        int[] xwalls = {0,100};
        int[] ywalls ={0,100};
        person.update(xwalls,ywalls,100,100);
        assertEquals(false,person.getNextX()==0);
        assertEquals(false,person.getNextY()==0);
    }

    @Test
    void collided() {
    }
}