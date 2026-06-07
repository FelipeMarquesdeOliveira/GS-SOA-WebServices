import { defineConfig } from '@playwright/test';

export default defineConfig({
  testDir: './tests',
  timeout: 30000,
  retries: 1,
  use: {
    baseURL: 'http://localhost:5001',
    headless: true,
  },
  projects: [
    {
      name: 'ResourceService',
      testMatch: /resource.*\.spec\.ts/,
    },
    {
      name: 'EventService',
      testMatch: /event.*\.spec\.ts/,
      use: { baseURL: 'http://localhost:5003' }
    },
    {
      name: 'CrewService',
      testMatch: /crew.*\.spec\.ts/,
      use: { baseURL: 'http://localhost:5006' }
    },
    {
      name: 'SimulationService',
      testMatch: /simulation.*\.spec\.ts/,
      use: { baseURL: 'http://localhost:5002' }
    },
  ],
});