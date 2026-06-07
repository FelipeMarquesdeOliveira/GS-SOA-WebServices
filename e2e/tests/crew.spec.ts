import { test, expect } from '@playwright/test';

const BASE_URL = 'http://localhost:5006';

test.describe('CrewService API', () => {
  let createdMemberId: number;

  test('POST /crew - should create a new crew member', async ({ request }) => {
    const response = await request.post(`${BASE_URL}/crew`, {
      data: {
        name: 'Ana Silva',
        role: 'COMMANDER',
        specialization: 'Mission Leadership'
      }
    });

    expect(response.ok()).toBeTruthy();
    const member = await response.json();
    expect(member.name).toBe('Ana Silva');
    expect(member.role).toBe('COMMANDER');
    expect(member.isActive).toBe(true);
    createdMemberId = member.id;
  });

  test('GET /crew - should return all crew members', async ({ request }) => {
    const response = await request.get(`${BASE_URL}/crew`);
    expect(response.ok()).toBeTruthy();
    const members = await response.json();
    expect(Array.isArray(members)).toBeTruthy();
  });

  test('GET /crew/{id} - should return a specific crew member', async ({ request }) => {
    const response = await request.get(`${BASE_URL}/crew/${createdMemberId}`);
    expect(response.ok()).toBeTruthy();
    const member = await response.json();
    expect(member.id).toBe(createdMemberId);
  });

  test('POST /crew/task - should create a task for crew member', async ({ request }) => {
    const response = await request.post(`${BASE_URL}/crew/task`, {
      data: {
        crewMemberId: createdMemberId,
        title: 'Repair Solar Panel',
        description: 'Replace damaged panel in Module B',
        dueDate: '2026-06-15T00:00:00'
      }
    });

    expect(response.ok()).toBeTruthy();
    const task = await response.json();
    expect(task.title).toBe('Repair Solar Panel');
    expect(task.crewMemberId).toBe(createdMemberId);
    expect(task.isCompleted).toBe(false);
  });

  test('GET /crew/{id}/tasks - should return tasks for a crew member', async ({ request }) => {
    const response = await request.get(`${BASE_URL}/crew/${createdMemberId}/tasks`);
    expect(response.ok()).toBeTruthy();
    const tasks = await response.json();
    expect(Array.isArray(tasks)).toBeTruthy();
  });

  test('POST /crew/{id}/shift - should assign shift to crew member', async ({ request }) => {
    const response = await request.post(`${BASE_URL}/crew/${createdMemberId}/shift`);
    expect(response.ok()).toBeTruthy();
    const member = await response.json();
    expect(member.lastShift).not.toBeNull();
  });
});