import { test, expect } from '@playwright/test';

const BASE_URL = 'http://localhost:5002';

test.describe('SimulationService API', () => {
  let createdSimulationId: number;

  test('POST /simulation - should create a new simulation', async ({ request }) => {
    const response = await request.post(`${BASE_URL}/simulation`, {
      data: {
        name: 'Oxygen Depletion Scenario',
        scenario: 'Simulate oxygen levels during EVA when primary tank fails'
      }
    });

    expect(response.ok()).toBeTruthy();
    const simulation = await response.json();
    expect(simulation.name).toBe('Oxygen Depletion Scenario');
    expect(simulation.result).toBe('Running');
    createdSimulationId = simulation.id;
  });

  test('GET /simulation/{id} - should return a specific simulation', async ({ request }) => {
    const response = await request.get(`${BASE_URL}/simulation/${createdSimulationId}`);
    expect(response.ok()).toBeTruthy();
    const simulation = await response.json();
    expect(simulation.id).toBe(createdSimulationId);
  });

  test('POST /simulation/{id}/run - should run a simulation', async ({ request }) => {
    const response = await request.post(`${BASE_URL}/simulation/${createdSimulationId}/run`);
    expect(response.ok()).toBeTruthy();
    const simulation = await response.json();
    expect(simulation.result).not.toBe('Running');
    expect(simulation.completedAt).not.toBeNull();
  });

  test('GET /simulation/history - should return simulation history', async ({ request }) => {
    const response = await request.get(`${BASE_URL}/simulation/history`);
    expect(response.ok()).toBeTruthy();
    const history = await response.json();
    expect(Array.isArray(history)).toBeTruthy();
  });
});