# ITVX Sign In Test Plan

## Application Overview

ITVX is a streaming platform owned by ITV that allows users to watch live TV, on-demand content, and premium content. The sign-in page is the entry point for user authentication. This test plan covers comprehensive sign-in scenarios including valid credentials, invalid inputs, edge cases, error handling, and security considerations.

## Test Scenarios

### 1. Email Validation Scenarios

**Seed:** `tests/seed.spec.ts`

#### 1.1. Sign in with valid email format

**File:** `tests/signin/email-validation.spec.ts`

**Steps:**
  1. Navigate to ITVX sign-in page at https://www.itv.com/watch/user/signin
    - expect: Sign in page loads successfully
    - expect: Email input field is visible and empty
    - expect: Continue button is present but disabled
  2. Enter a valid email address (e.g., testuser@gmail.com) in the email field
    - expect: Email text is entered correctly
    - expect: Success validation indicator appears
    - expect: Continue button becomes enabled
  3. Click the Continue button
    - expect: Button shows loading state
    - expect: Page processes the email check

#### 1.2. Sign in with different valid email domains

**File:** `tests/signin/email-validation.spec.ts`

**Steps:**
  1. Navigate to ITVX sign-in page
    - expect: Page loads successfully
  2. Enter email with corporate domain (e.g., user@company.co.uk)
    - expect: Email is accepted
    - expect: Validation passes
    - expect: Continue button becomes enabled
  3. Click Continue and verify the flow
    - expect: System processes corporate email successfully

#### 1.3. Invalid email format rejection

**File:** `tests/signin/email-validation.spec.ts`

**Steps:**
  1. Navigate to ITVX sign-in page
    - expect: Page loads successfully
  2. Enter invalid email format (e.g., notanemail)
    - expect: Error validation indicator appears
    - expect: Continue button remains disabled
  3. Clear field and enter another invalid format (e.g., user@.com)
    - expect: Error validation indicator appears
    - expect: Continue button remains disabled

#### 1.4. Email field with special characters

**File:** `tests/signin/email-validation.spec.ts`

**Steps:**
  1. Navigate to ITVX sign-in page
    - expect: Page loads successfully
  2. Enter email with special characters allowed in emails (e.g., user+tag@example.com)
    - expect: Email is accepted
    - expect: Validation passes
    - expect: Continue button becomes enabled

### 2. Input Field Behavior

**Seed:** `tests/seed.spec.ts`

#### 2.1. Email field accepts and displays input correctly

**File:** `tests/signin/input-behavior.spec.ts`

**Steps:**
  1. Navigate to ITVX sign-in page
    - expect: Email input field is visible with placeholder 'e.g. name@mail.com'
  2. Click on the email field and type a valid email address
    - expect: Text is entered character by character
    - expect: Field displays the entered text correctly
  3. Clear the field using backspace
    - expect: All characters are removed
    - expect: Validation indicator disappears
    - expect: Continue button becomes disabled

#### 2.2. Whitespace handling in email field

**File:** `tests/signin/input-behavior.spec.ts`

**Steps:**
  1. Navigate to ITVX sign-in page
    - expect: Page loads successfully
  2. Enter email with leading whitespace (e.g., '  user@example.com')
    - expect: System trims leading whitespace
    - expect: Validation passes
    - expect: Continue button becomes enabled
  3. Clear field and enter email with trailing whitespace (e.g., 'user@example.com  ')
    - expect: System trims trailing whitespace
    - expect: Validation passes

#### 2.3. Case insensitivity of email addresses

**File:** `tests/signin/input-behavior.spec.ts`

**Steps:**
  1. Navigate to ITVX sign-in page
    - expect: Page loads successfully
  2. Enter email in uppercase (e.g., USER@EXAMPLE.COM)
    - expect: Email is accepted
    - expect: Validation passes
  3. Clear field and enter same email in mixed case (e.g., User@Example.Com)
    - expect: Email is accepted
    - expect: Validation passes

#### 2.4. Maximum email length handling

**File:** `tests/signin/input-behavior.spec.ts`

**Steps:**
  1. Navigate to ITVX sign-in page
    - expect: Page loads successfully
  2. Enter a valid email at maximum allowed length (e.g., very-long-email-address@subdomain.example.com)
    - expect: Email is accepted without truncation
    - expect: Validation passes

### 3. Sign In Flow - New Users

**Seed:** `tests/seed.spec.ts`

#### 3.1. First-time user registration flow

**File:** `tests/signin/new-user-flow.spec.ts`

**Steps:**
  1. Navigate to ITVX sign-in page
    - expect: Sign in page is displayed
    - expect: Heading shows 'Sign in or register'
  2. Enter a new (unregistered) email address
    - expect: Email validation passes
    - expect: Continue button is enabled
  3. Click Continue button
    - expect: System checks if email exists in database
    - expect: If new, redirect to registration page or password creation screen

#### 3.2. New user sees appropriate guidance

**File:** `tests/signin/new-user-flow.spec.ts`

**Steps:**
  1. Navigate to ITVX sign-in page
    - expect: Page displays helpful text: 'Not sure if you've already registered with us?' and 'Don't worry, we can tell from your email address whether you already have an ITVX or ITV account.'
  2. Verify informational text is clear and visible
    - expect: Guidance text helps users understand the unified login system

### 4. Sign In Flow - Existing Users

**Seed:** `tests/seed.spec.ts`

#### 4.1. Existing user can proceed to password entry

**File:** `tests/signin/existing-user-flow.spec.ts`

**Steps:**
  1. Navigate to ITVX sign-in page
    - expect: Sign in page loads
  2. Enter an existing user email address (from known test account)
    - expect: Email validation passes
    - expect: Continue button is enabled
  3. Click Continue button
    - expect: System recognizes email as existing user
    - expect: Next page displays password field or password entry screen

#### 4.2. Returning user experience is consistent

**File:** `tests/signin/existing-user-flow.spec.ts`

**Steps:**
  1. Navigate to sign-in page and enter existing user email
    - expect: Email is accepted
  2. Click Continue and observe the transition
    - expect: Flow is smooth without errors
    - expect: User is guided to next authentication step

### 5. Button States and Interactions

**Seed:** `tests/seed.spec.ts`

#### 5.1. Continue button disabled state on empty field

**File:** `tests/signin/button-states.spec.ts`

**Steps:**
  1. Navigate to ITVX sign-in page
    - expect: Page loads successfully
    - expect: Email field is empty
  2. Observe the Continue button state
    - expect: Continue button is disabled (grayed out)
    - expect: Button is not clickable

#### 5.2. Continue button becomes enabled with valid email

**File:** `tests/signin/button-states.spec.ts`

**Steps:**
  1. Navigate to ITVX sign-in page
    - expect: Continue button is initially disabled
  2. Enter a valid email address
    - expect: Continue button becomes enabled (normal color)
    - expect: Button is clickable
  3. Delete one character to make email invalid
    - expect: Continue button becomes disabled again

#### 5.3. Continue button shows loading state during submission

**File:** `tests/signin/button-states.spec.ts`

**Steps:**
  1. Navigate to ITVX sign-in page and enter valid email
    - expect: Continue button is enabled
  2. Click Continue button immediately
    - expect: Button shows loading indicator (spinner or 'In progress' text)
    - expect: Button becomes disabled to prevent double-submission

#### 5.4. Button text and accessibility

**File:** `tests/signin/button-states.spec.ts`

**Steps:**
  1. Inspect the Continue button
    - expect: Button text clearly says 'Continue'
    - expect: Button has appropriate ARIA labels for screen readers

### 6. Error Handling and Edge Cases

**Seed:** `tests/seed.spec.ts`

#### 6.1. Network error handling

**File:** `tests/signin/error-handling.spec.ts`

**Steps:**
  1. Navigate to ITVX sign-in page and enter valid email
    - expect: Form is ready
  2. Simulate network failure and click Continue
    - expect: Error dialog appears with message 'Something went wrong'
    - expect: User sees options to 'Refresh page' or visit 'ITVX help'
    - expect: Email input is preserved

#### 6.2. Backend service unavailable

**File:** `tests/signin/error-handling.spec.ts`

**Steps:**
  1. Navigate to sign-in page
    - expect: Page loads
  2. Enter email and click Continue when backend is down
    - expect: Error message displays
    - expect: User can retry or access help

#### 6.3. Error recovery and retry

**File:** `tests/signin/error-handling.spec.ts`

**Steps:**
  1. Trigger an error by clicking Continue during network issue
    - expect: Error dialog appears with 'Refresh page' button
  2. Click 'Refresh page' button
    - expect: Page reloads
    - expect: Email field is cleared
    - expect: Form is ready for new entry

#### 6.4. Help link functionality

**File:** `tests/signin/error-handling.spec.ts`

**Steps:**
  1. Trigger error and click 'ITVX help' button
    - expect: User is taken to ITVX Help page (help.itv.com)
    - expect: New tab or navigation occurs

### 7. Page Navigation and UX

**Seed:** `tests/seed.spec.ts`

#### 7.1. Close button functionality

**File:** `tests/signin/navigation.spec.ts`

**Steps:**
  1. Navigate to ITVX sign-in page
    - expect: Close button (X) is visible in top-left corner
  2. Click the Close button
    - expect: Sign-in modal/page closes
    - expect: User is redirected to homepage or previous page

#### 7.2. ITVX logo click navigation

**File:** `tests/signin/navigation.spec.ts`

**Steps:**
  1. Navigate to sign-in page
    - expect: ITVX logo is visible
  2. Click on the ITVX logo
    - expect: User is taken to ITVX homepage
    - expect: Sign-in page closes

#### 7.3. Browser back button functionality

**File:** `tests/signin/navigation.spec.ts`

**Steps:**
  1. Navigate to ITVX sign-in page
    - expect: Page loads
  2. Click browser back button
    - expect: User is taken to previous page or homepage

#### 7.4. Help link in guidance text

**File:** `tests/signin/navigation.spec.ts`

**Steps:**
  1. Navigate to sign-in page
    - expect: Help text section is visible
  2. Look for any clickable links in guidance section
    - expect: If links exist, verify they navigate to appropriate help pages

### 8. Accessibility and Compliance

**Seed:** `tests/seed.spec.ts`

#### 8.1. Screen reader compatibility

**File:** `tests/signin/accessibility.spec.ts`

**Steps:**
  1. Navigate to sign-in page with screen reader enabled
    - expect: Page title is announced
    - expect: Email label is associated with input field
    - expect: Continue button is announced with its state (enabled/disabled)

#### 8.2. Keyboard navigation

**File:** `tests/signin/accessibility.spec.ts`

**Steps:**
  1. Navigate to sign-in page
    - expect: Page loads
  2. Press Tab key to navigate through form elements
    - expect: Focus moves from Close button to ITVX logo to Email field to Continue button
    - expect: Focus indicators are visible
  3. With email field focused, type email address
    - expect: Email is entered correctly
  4. Press Tab to focus Continue button and press Enter
    - expect: Form submits successfully

#### 8.3. Color contrast for readability

**File:** `tests/signin/accessibility.spec.ts`

**Steps:**
  1. Inspect sign-in page visual elements
    - expect: Text has sufficient color contrast with background
    - expect: Disabled button state is visually distinct

#### 8.4. ARIA labels and roles

**File:** `tests/signin/accessibility.spec.ts`

**Steps:**
  1. Inspect HTML elements for ARIA attributes
    - expect: Email input has appropriate label
    - expect: Button roles are defined
    - expect: Validation feedback is announced to assistive tech

### 9. Security Considerations

**Seed:** `tests/seed.spec.ts`

#### 9.1. Email not stored in browser history

**File:** `tests/signin/security.spec.ts`

**Steps:**
  1. Navigate to sign-in page and enter an email address
    - expect: Email is displayed in form
  2. Submit form and check browser history
    - expect: Email address should not appear in auto-complete suggestions for future visits

#### 9.2. HTTPS connection required

**File:** `tests/signin/security.spec.ts`

**Steps:**
  1. Verify the sign-in page URL
    - expect: URL uses HTTPS protocol (https://www.itv.com/watch/user/signin)
    - expect: Secure connection indicator is present

#### 9.3. Form data submission security

**File:** `tests/signin/security.spec.ts`

**Steps:**
  1. Enter email and monitor network traffic
    - expect: Email is sent via HTTPS POST request
    - expect: No sensitive data is logged in client-side code

#### 9.4. CSRF protection

**File:** `tests/signin/security.spec.ts`

**Steps:**
  1. Verify the sign-in form structure
    - expect: Form includes CSRF token or uses SameSite cookie attribute
    - expect: Request headers include necessary security tokens

### 10. Responsive Design and Device Compatibility

**Seed:** `tests/seed.spec.ts`

#### 10.1. Mobile device sign-in experience

**File:** `tests/signin/responsive-design.spec.ts`

**Steps:**
  1. Navigate to sign-in page on mobile device (viewport 375x667)
    - expect: Page renders correctly
    - expect: All form elements are visible and accessible
  2. Enter email on mobile keyboard
    - expect: Email input field is large enough for touch
    - expect: Keyboard doesn't obstruct form
  3. Click Continue button on mobile
    - expect: Button is large enough for touch interaction
    - expect: Form submits successfully

#### 10.2. Tablet sign-in experience

**File:** `tests/signin/responsive-design.spec.ts`

**Steps:**
  1. Navigate to sign-in page on tablet (viewport 768x1024)
    - expect: Layout is optimized for tablet
    - expect: Form elements are appropriately sized

#### 10.3. Desktop sign-in experience

**File:** `tests/signin/responsive-design.spec.ts`

**Steps:**
  1. Navigate to sign-in page on desktop (viewport 1920x1080)
    - expect: Page layout is centered and readable
    - expect: Form has appropriate width

#### 10.4. Sign-in modal display on different screen sizes

**File:** `tests/signin/responsive-design.spec.ts`

**Steps:**
  1. Verify modal/dialog appearance across different viewport sizes
    - expect: Modal is properly centered
    - expect: Content is not truncated
    - expect: Close button is accessible

### 11. Browser Compatibility

**Seed:** `tests/seed.spec.ts`

#### 11.1. Chrome browser sign-in flow

**File:** `tests/signin/browser-compatibility.spec.ts`

**Steps:**
  1. Open ITVX sign-in page in Chrome
    - expect: Page loads without issues
    - expect: All form elements function correctly
  2. Complete sign-in flow
    - expect: Flow completes successfully

#### 11.2. Firefox browser sign-in flow

**File:** `tests/signin/browser-compatibility.spec.ts`

**Steps:**
  1. Open ITVX sign-in page in Firefox
    - expect: Page loads without issues
    - expect: Form functions correctly

#### 11.3. Safari browser sign-in flow

**File:** `tests/signin/browser-compatibility.spec.ts`

**Steps:**
  1. Open ITVX sign-in page in Safari
    - expect: Page loads properly
    - expect: Email validation works

#### 11.4. Edge browser sign-in flow

**File:** `tests/signin/browser-compatibility.spec.ts`

**Steps:**
  1. Open ITVX sign-in page in Edge
    - expect: Page loads and form works correctly

### 12. Performance and Load Times

**Seed:** `tests/seed.spec.ts`

#### 12.1. Sign-in page load time

**File:** `tests/signin/performance.spec.ts`

**Steps:**
  1. Navigate to ITVX sign-in page and measure load time
    - expect: Page loads within 3 seconds
    - expect: All critical elements are visible within 2 seconds

#### 12.2. Form submission response time

**File:** `tests/signin/performance.spec.ts`

**Steps:**
  1. Enter email and submit form, measuring response time
    - expect: Server responds within 2 seconds
    - expect: No timeout errors occur

#### 12.3. Email validation real-time feedback

**File:** `tests/signin/performance.spec.ts`

**Steps:**
  1. Type email character by character
    - expect: Validation feedback is immediate (< 500ms)
    - expect: No lag or delay in UI response
