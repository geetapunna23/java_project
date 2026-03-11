import { test, expect } from '@playwright/test';

test('has title', async ({ page }) => {
  await page.goto('https://playwright.dev/');

  // Expect a title "to contain" a substring.
  await expect(page).toHaveTitle(/Playwright/);
});

test('get started link', async ({ page }) => {
  await page.goto('https://playwright.dev/');

  // Click the get started link.
  await page.getByRole('link', { name: 'Get started' }).click();

  // Expects page to have a heading with the name of Installation.
  await expect(page.getByRole('heading', { name: 'Installation' })).toBeVisible();
});

test('screenshot example.com', async ({ page }) => {
  await page.goto('https://example.com');
  await expect(page).toHaveTitle(/Example Domain/);
  await page.screenshot({ path: 'screenshots/example.com.png', fullPage: true });
});

test('google logo visible', async ({ page }) => {
  await page.goto('https://www.google.com/ncr', { waitUntil: 'domcontentloaded' });

  // Dismiss cookie/consent dialog if present
  const consent = page.locator(
    'button:has-text("I agree"), button:has-text("I Agree"), button:has-text("Accept all"), button:has-text("Accept")'
  );
  if (await consent.count() > 0) {
    await consent.first().click();
  }

  const logo = page.locator('img[alt="Google"], #hplogo, svg[aria-label="Google"]');
  await expect(logo.first()).toBeVisible({ timeout: 10000 });
});
