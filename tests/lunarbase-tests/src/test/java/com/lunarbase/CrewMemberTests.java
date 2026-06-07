package com.lunarbase;

import com.lunarbase.domain.entities.CrewMember;
import com.lunarbase.domain.entities.CrewTask;
import com.lunarbase.domain.enums.CrewRole;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CrewMemberTests {

    @Test
    public void crewMember_constructor_setsInitialValues() {
        CrewMember member = new CrewMember("Ana Silva", CrewRole.COMMANDER, "Mission Leadership");

        assertEquals("Ana Silva", member.getName());
        assertEquals(CrewRole.COMMANDER, member.getRole());
        assertEquals("Mission Leadership", member.getSpecialization());
        assertTrue(member.isActive());
    }

    @Test
    public void crewMember_assignShift_updatesLastShift() {
        CrewMember member = new CrewMember("Carlos Santos", CrewRole.ENGINEER, "Mechanical Systems");

        member.assignShift();

        assertNotNull(member.getLastShift());
    }

    @Test
    public void crewMember_deactivate_setsIsActiveFalse() {
        CrewMember member = new CrewMember("Maria Costa", CrewRole.SCIENTIST, "Biology");

        member.deactivate();

        assertFalse(member.isActive());
    }

    @Test
    public void crewMember_activate_setsIsActiveTrue() {
        CrewMember member = new CrewMember("Joao Pedro", CrewRole.MEDIC, "Emergency Medicine");
        member.deactivate();

        member.activate();

        assertTrue(member.isActive());
    }

    @Test
    public void crewTask_constructor_setsInitialValues() {
        CrewTask task = new CrewTask(1, "Repair Solar Panel", "Replace damaged panel", null);

        assertEquals(1, task.getCrewMemberId());
        assertEquals("Repair Solar Panel", task.getTitle());
        assertFalse(task.isCompleted());
    }

    @Test
    public void crewTask_markCompleted_setsIsCompletedTrue() {
        CrewTask task = new CrewTask(1, "Test Task", "Description", null);

        task.markCompleted();

        assertTrue(task.isCompleted());
    }
}