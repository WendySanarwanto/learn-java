package me.wendysa.contactsdemo.repositories;

import me.wendysa.contactsdemo.models.Schedule;
import org.junit.*;
import static org.junit.Assert.*;

public class ScheduleRepositoryTest {
    private ScheduleRepository scheduleRepository;

    @Before
    public void initialise() {
        scheduleRepository = new ScheduleRepository();
    }

    @After
    public void tearDown() {
        scheduleRepository.deleteAll();
    }

    @Test
    public void createNullSchedule() {
        Schedule nullSchedule = scheduleRepository.push( null );
        assertNull(nullSchedule);
    }
}
