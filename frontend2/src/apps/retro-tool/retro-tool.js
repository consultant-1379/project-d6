/**
 * RetroTool is defined as
 * `<e-retro-tool>`
 *
 * Imperatively create application
 * @example
 * let app = new RetroTool();
 *
 * Declaratively create application
 * @example
 * <e-retro-tool></e-retro-tool>
 *
 * @extends {App}
 */
import { App, html, definition } from '@eui/app';
import style from './retro-tool.css';
import {Button,Textarea } from '@eui/base';


export default class RetroTool extends App {
  // Uncomment this block to add initialization code
  // constructor() {
  //   super();
  //   // initialize
  // }

  static get components() {
    return {
      'eui-button': Button,
      'eui-textarea': Textarea
    };
  }

  didConnect() {
    this.bubble('app:title', { displayName: 'retro-tool' });
    this.bubble('app:subtitle', { subtitle: '' });
  }

  /**
   * Render the <e-retro-tool> app. This function is called each time a
   * prop changes.
   */
  render() {
    return html`
    <h1>Your app markup goes here</h1>
    <eui-button primary>My Button</eui-button>

    <div slot="content">
    This text appears inside the content area of the flyout panel. Anything can be added here.
    </div>
    <eui-textarea name="item" cols="20"></eui-textarea>
    `;
  }
}

definition('e-retro-tool', {
  style,
})(RetroTool);

RetroTool.register();
