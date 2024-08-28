/**
 * Integration tests for <e-retro-tool>
 */
import { expect, fixture } from '@open-wc/testing';
import '../../../src/apps/retro-tool/retro-tool.js';

describe('RetroTool Application Tests', () => {
  describe('Basic application setup', () => {
    it('should create a new <e-retro-tool>', async () => {
      const element = await fixture('<e-retro-tool></e-retro-tool>');
      const headingTag = element.shadowRoot.querySelector('h1');

      expect(
        headingTag.textContent,
        '"Your app markup goes here" was not found',
      ).to.equal('Your app markup goes here');
    });
  });
});
