const Page = require('./core/page');
const Trigger = require('./events/trigger');
const { toMatchImageSnapshot } = require('jest-image-snapshot');
const { MAX_TRIGGER_EVENTS_TEST_TIMEOUT } = require('./core/constants'); // core constants (Timeouts vars imported)

expect.extend({ toMatchImageSnapshot });

const triggerTest = () => {
  beforeEach(() => {
    jest.setTimeout(MAX_TRIGGER_EVENTS_TEST_TIMEOUT);
  });

  test('Trigger disconnection: Meteor.disconnect()/.status()', async () => {
    const test = new Trigger();
    let response;
    let screenshot;
    try {
      const testName = 'triggerMeteorDisconnect';
      await test.logger('begin of ', testName);
      await test.init(Page.getArgs(), undefined, undefined, undefined, testName);
      response = await test.triggerMeteorDisconnect(testName);
      screenshot = await test.page.screenshot();
    } catch (e) {
      await test.logger(e);
    } finally {
      await test.close();
    }
    expect(response).toBe(true);
    await Page.checkRegression(0.1, screenshot);
  });

  test('Trigger disconnection: SHUTTING DOWN NETWORK SERVICE', async () => {
    const test = new Trigger();
    let response;
    let screenshot;
    try {
      const testName = 'triggerNetworkServiceDisconnection';
      await test.logger('begin of ', testName);
      await test.init(Page.getArgs(), undefined, undefined, undefined, testName);
      response = await test.triggerNetworkServiceDisconnection(testName);
      screenshot = await test.page.screenshot();
    } catch (e) {
      await test.logger(e);
    } finally {
      await test.close();
    }
    expect(response).toBe(true);
    await Page.checkRegression(0.1, screenshot);
  });
};
module.exports = exports = triggerTest;
