package com.lunarbase.domain.interfaces;

import com.lunarbase.domain.entities.Simulation;
import java.util.List;

public interface SimulationRepository extends Repository<Simulation> {
    List<Simulation> findHistory(int limit);
}