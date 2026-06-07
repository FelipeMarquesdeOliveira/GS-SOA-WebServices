package com.lunarbase;

import com.lunarbase.domain.exceptions.NotFoundException;
import com.lunarbase.domain.exceptions.BusinessRuleException;
import com.lunarbase.domain.exceptions.ResourceCriticalException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExceptionTests {

    @Test
    public void notFoundException_containsEntityId() {
        NotFoundException ex = new NotFoundException("Resource", 42);

        assertEquals("Resource with id 42 was not found.", ex.getMessage());
        assertEquals(42, ex.getEntityId());
    }

    @Test
    public void businessRuleException_containsMessage() {
        BusinessRuleException ex = new BusinessRuleException("Cannot delete resource with active sensors.");

        assertEquals("Cannot delete resource with active sensors.", ex.getMessage());
    }

    @Test
    public void resourceCriticalException_containsDetails() {
        ResourceCriticalException ex = new ResourceCriticalException(1, "Oxygen Tank", "Level below 10%");

        assertEquals(1, ex.getResourceId());
        assertEquals("Oxygen Tank", ex.getResourceName());
        assertTrue(ex.getMessage().contains("Oxygen Tank"));
    }
}