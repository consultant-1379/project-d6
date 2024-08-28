/**
 * App1 is defined as
 * `<e-app-1>`
 *
 * Imperatively create application
 * @example
 * let app = new App1();
 *
 * Declaratively create application
 * @example
 * <e-app-1></e-app-1>
 *
 * @extends {App}
 */
 import { definition } from '@eui/component';
 import { App, html } from '@eui/app';
 import style from './app1.css';
 import 'my-component';
 
 
 @definition('e-app-1', {
   style,
   props: {
     response: { attribute: false },
     buttondisabled: { type: Boolean, default: true},
     buttonGet: {type:Boolean, default:true},
   },
 })
 export default class App1 extends App {
   // Uncomment this block to add initialization code
   // constructor() {
   //   super();
   //   // initialize
   // }
 
   /**
   * Render the <e-app-1> app. This function is called each time a
   * prop changes.
   */
    render() {
     return html`
 
     <e-my-component></e-my-component>
 
   `;
   }
 }
 
     
 
 /**
  * Register the component as e-app-1.
  * Registration can be done at a later time and with a different name
  * Uncomment the below line to register the App if used outside the container
  */
 // App1.register();
 