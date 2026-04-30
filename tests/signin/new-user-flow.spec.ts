// spec: specs/itvx-signin-test-plan.md
// seed: tests/seed.spec.ts

import { test, expect } from '@playwright/test';

test.describe('Sign In Flow - New Users', () => {
  test('First-time user registration flow', async ({ page }) => {
    // 1. Navigate to ITVX sign-in page
    await page.goto('https://www.itv.com/watch/user/signin');

    // Verify the 'Sign in or register' heading is visible
    await expect(page.getByRole('heading', { name: 'Sign in or register' })).toBeVisible();

    const emailInput = page.getByTestId('input-field');
    const continueButton = page.getByRole('button', { name: 'Continue' });
    const cookieDialog = page.locator('#cassie-widget');
    const acceptCookiesButton = page.locator('#cassie-widget button', { hasText: 'Accept' }).first();

    if (await acceptCookiesButton.count() > 0 && await acceptCookiesButton.isVisible()) {
      await acceptCookiesButton.click();
      await expect(cookieDialog).toBeHidden({ timeout: 5000 });
    }

    // 2. Enter a new (unregistered) email address
    await emailInput.fill('newuser.test.20260430@example.com');

    // Verify the Continue button is visible and enabled
    await expect(continueButton).toBeVisible();
    await expect(continueButton).toBeEnabled();

    // 3. Click Continue button to proceed with new user registration
    await continueButton.click();

    // Verify successful navigation to registration page
    await expect(page).toHaveURL(/.*\/user\/register/);
    
    // Verify registration page displays password setup screen
    await expect(page.getByRole('heading', { name: 'Set up your account' })).toBeVisible();
    
    // Verify the email is displayed on the registration page
    await expect(page.getByText('newuser.test.20260430@example.com')).toBeVisible();
  });
});
