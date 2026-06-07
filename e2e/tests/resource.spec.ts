import { test, expect } from '@playwright/test';

const BASE_URL = 'http://localhost:5001';

test.describe('ResourceService API', () => {
  let createdResourceId: number;

  test('POST /resources - should create a new resource', async ({ request }) => {
    const response = await request.post(`${BASE_URL}/resources`, {
      data: {
        name: 'Oxygen Tank Alpha',
        type: 'OXYGEN',
        maxCapacity: 1000,
        location: 'Module A'
      }
    });

    expect(response.ok()).toBeTruthy();
    const resource = await response.json();
    expect(resource.name).toBe('Oxygen Tank Alpha');
    expect(resource.type).toBe('OXYGEN');
    expect(resource.maxCapacity).toBe(1000);
    expect(resource.currentAmount).toBe(1000);
    expect(resource.status).toBe('STABLE');
    createdResourceId = resource.id;
  });

  test('GET /resources - should return all resources', async ({ request }) => {
    const response = await request.get(`${BASE_URL}/resources`);
    expect(response.ok()).toBeTruthy();
    const resources = await response.json();
    expect(Array.isArray(resources)).toBeTruthy();
  });

  test('GET /resources/{id} - should return a specific resource', async ({ request }) => {
    const response = await request.get(`${BASE_URL}/resources/${createdResourceId}`);
    expect(response.ok()).toBeTruthy();
    const resource = await response.json();
    expect(resource.id).toBe(createdResourceId);
    expect(resource.name).toBe('Oxygen Tank Alpha');
  });

  test('GET /resources/{id} - should return 404 for non-existent resource', async ({ request }) => {
    const response = await request.get(`${BASE_URL}/resources/99999`);
    expect(response.status()).toBe(404);
  });

  test('PUT /resources/{id} - should update resource amount', async ({ request }) => {
    const response = await request.put(`${BASE_URL}/resources/${createdResourceId}`, {
      data: 500
    });
    expect(response.ok()).toBeTruthy();
    const resource = await response.json();
    expect(resource.currentAmount).toBe(500);
    expect(resource.status).toBe('STABLE');
  });

  test('POST /resources/{id}/consume - should consume resource', async ({ request }) => {
    const response = await request.post(`${BASE_URL}/resources/${createdResourceId}/consume`, {
      data: 100
    });
    expect(response.ok()).toBeTruthy();
    const resource = await response.json();
    expect(resource.currentAmount).toBe(400);
  });

  test('POST /resources/{id}/replenish - should replenish resource', async ({ request }) => {
    const response = await request.post(`${BASE_URL}/resources/${createdResourceId}/replenish`, {
      data: 200
    });
    expect(response.ok()).toBeTruthy();
    const resource = await response.json();
    expect(resource.currentAmount).toBe(600);
  });

  test('GET /resources/stats - should return resource statistics', async ({ request }) => {
    const response = await request.get(`${BASE_URL}/resources/stats`);
    expect(response.ok()).toBeTruthy();
    const stats = await response.json();
    expect(stats).toHaveProperty('totalResources');
    expect(stats).toHaveProperty('stableCount');
    expect(stats).toHaveProperty('warningCount');
    expect(stats).toHaveProperty('criticalCount');
    expect(stats).toHaveProperty('averageUsagePercentage');
  });

  test('GET /resources/{id}/history - should return resource history', async ({ request }) => {
    const response = await request.get(`${BASE_URL}/resources/${createdResourceId}/history`);
    expect(response.ok()).toBeTruthy();
    const history = await response.json();
    expect(Array.isArray(history)).toBeTruthy();
  });

  test('DELETE /resources/{id} - should delete a resource', async ({ request }) => {
    const response = await request.delete(`${BASE_URL}/resources/${createdResourceId}`);
    expect(response.status()).toBe(204);
  });
});