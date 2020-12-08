package CovidSpreadTest;

import CovidSpreadSimulation.City;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class CityTest {

    @Test
    void setSize() { City city= new City(1500,1000);
        assertEquals(1500,city.getWidth());
        assertEquals(1000,city.getHeight());
        city.setSize(500,100);
        assertEquals(500,city.getWidth());
        assertEquals(100,city.getHeight());
    }


    @Test
    void getPopsize() {
        City city= new City(1500,1000);
        assertEquals(200,city.getPopsize());//Default value of population is 200
    }

    @Test
    void getWidth() {
        City city= new City(200,1000);
        assertEquals(200,city.getWidth());
    }

    @Test
    void setWidth() {
        City city= new City(200,1000);
        city.setWidth(300);
        assertEquals(300,city.getWidth());
    }

    @Test
    void getHeight() {
        City city= new City(200,100);
        assertEquals(100,city.getHeight());
    }

    @Test
    void setHeight() {
        City city= new City(200,100);
        city.setHeight(500);
        assertEquals(500,city.getHeight());
    }

    @Test
    void getxWalls() {
        City city= new City(1500,1000);
        if(!city.isquarantine){
            assertEquals(0,city.getxWalls()[0]);
            assertEquals(1500,city.getxWalls()[1]);
        }
    }

    @Test
    void setxWalls() {
        City city= new City(1500,1000);
        int[] xwalls= {0,500};
        city.setxWalls(xwalls);
        assertEquals(0,city.getxWalls()[0]);
        assertEquals(500,city.getxWalls()[1]);
    }

    @Test
    void getyWalls() {
        City city= new City(1500,1000);
        if(!city.isquarantine){
            assertEquals(0,city.getyWalls()[0]);
            assertEquals(1000,city.getyWalls()[1]);
        }
    }

    @Test
    void setyWalls() {
        City city= new City(1500,1000);
        int[] ywalls= {0,200};
        city.setyWalls(ywalls);
        assertEquals(0,city.getyWalls()[0]);
        assertEquals(200,city.getyWalls()[1]);
    }

    @Test
    void update() {
        City city =new City(100,100);
        int x=city.population[0].x;
        int y=city.population[0].y;
        city.update();
        assertEquals(false,(x-city.population[0].x)==0);
        assertEquals(false,(y-city.population[0].y)==0);
    }
    @Test
    void isQuarantine() {
        City city =new City(100,100);
        city.isquarantine=true;
        assertEquals(true,city.isQuarantine());
    }
}