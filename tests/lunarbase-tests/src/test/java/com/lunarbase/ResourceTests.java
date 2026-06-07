package com.lunarbase;

import com.lunarbase.domain.entities.Resource;
import com.lunarbase.domain.enums.ResourceType;
import com.lunarbase.domain.enums.ResourceStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResourceTests {

    @Test
    public void resource_constructor_setsInitialValues() {
        Resource resource = new Resource("Oxygen Tank A", ResourceType.OXYGEN, 1000, "Module A");

        assertEquals("Oxygen Tank A", resource.getName());
        assertEquals(ResourceType.OXYGEN, resource.getType());
        assertEquals(1000, resource.getMaxCapacity());
        assertEquals(1000, resource.getCurrentAmount());
        assertEquals(ResourceStatus.STABLE, resource.getStatus());
        assertEquals("Module A", resource.getLocation());
    }

    @Test
    public void resource_consume_reducesAmount() {
        Resource resource = new Resource("Water Tank", ResourceType.WATER, 500, "Module B");
        double initial = resource.getCurrentAmount();

        resource.consume(100);

        assertEquals(initial - 100, resource.getCurrentAmount());
    }

    @Test
    public void resource_consume_cannotGoBelowZero() {
        Resource resource = new Resource("Energy Cell", ResourceType.ENERGY, 100, "Module C");
        resource.consume(150);

        assertEquals(0, resource.getCurrentAmount());
    }

    @Test
    public void resource_replenish_increasesAmount() {
        Resource resource = new Resource("Food Storage", ResourceType.FOOD, 200, "Module D");
        resource.consume(50);

        resource.replenish(30);

        assertEquals(180, resource.getCurrentAmount());
    }

    @Test
    public void resource_replenish_cannotExceedMaxCapacity() {
        Resource resource = new Resource("Fuel Tank", ResourceType.FUEL, 100, "Module E");
        resource.replenish(50);

        assertEquals(100, resource.getCurrentAmount());
    }

    @Test
    public void resource_status_updatesBasedOnPercentage() {
        Resource resource = new Resource("Test Resource", ResourceType.OXYGEN, 100, "Test");
        resource.consume(85);
        assertEquals(ResourceStatus.CRITICAL, resource.getStatus());

        resource.consume(-85);
        resource.consume(35);
        assertEquals(ResourceStatus.WARNING, resource.getStatus());

        resource.consume(-35);
        resource.consume(30);
        assertEquals(ResourceStatus.STABLE, resource.getStatus());
    }

    @Test
    public void resource_consume_throwsOnNegativeAmount() {
        Resource resource = new Resource("Test", ResourceType.ENERGY, 100, "Location");
        assertThrows(IllegalArgumentException.class, () -> resource.consume(-10));
    }

    @Test
    public void resource_replenish_throwsOnNegativeAmount() {
        Resource resource = new Resource("Test", ResourceType.ENERGY, 100, "Location");
        assertThrows(IllegalArgumentException.class, () -> resource.replenish(-10));
    }
}