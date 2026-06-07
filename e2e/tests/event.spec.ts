import { test, expect } from '@playwright/test';

const BASE_URL = 'http://localhost:5003';

test.describe('EventService API', () => {
  let createdEventId: number;

  test('POST /events - should create a new event', async ({ request }) => {
    const response = await request.post(`${BASE_URL}/events`, {
      data: {
        title: 'Radiation Level High',
        description: 'Radiation levels exceeded safe threshold',
        severity: 'CRITICAL',
        resourceId: null
      }
    });

    expect(response.ok()).toBeTruthy();
    const event = await response.json();
    expect(event.title).toBe('Radiation Level High');
    expect(event.severity).toBe('CRITICAL');
    expect(event.status).toBe('ACTIVE');
    createdEventId = event.id;
  });

  test('GET /events - should return all events', async ({ request }) => {
    const response = await request.get(`${BASE_URL}/events`);
    expect(response.ok()).toBeTruthy();
    const events = await response.json();
    expect(Array.isArray(events)).toBeTruthy();
  });

  test('GET /events/active - should return only active events', async ({ request }) => {
    const response = await request.get(`${BASE_URL}/events/active`);
    expect(response.ok()).toBeTruthy();
    const events = await response.json();
    expect(Array.isArray(events)).toBeTruthy();
    events.forEach((event: any) => {
      expect(event.status).toBe('ACTIVE');
    });
  });

  test('POST /events/{id}/ack - should acknowledge an event', async ({ request }) => {
    const response = await request.post(`${BASE_URL}/events/${createdEventId}/ack`, {
      data: { acknowledgedBy: 'Commander Silva' }
    });
    expect(response.ok()).toBeTruthy();
    const event = await response.json();
    expect(event.status).toBe('ACKNOWLEDGED');
    expect(event.acknowledgedBy).toBe('Commander Silva');
  });

  test('POST /events/{id}/link-resource - should link event to resource', async ({ request }) => {
    const response = await request.post(`${BASE_URL}/events/${createdEventId}/link-resource`, {
      data: { resourceId: 1 }
    });
    expect(response.ok()).toBeTruthy();
    const event = await response.json();
    expect(event.resourceId).toBe(1);
  });
});