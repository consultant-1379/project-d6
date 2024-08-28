import { TemplateComponent, definition } from '@eui/component';

export default class SystemProfile extends TemplateComponent {
  constructor() {
    super();
    this.addEventListener('click', this);
  }

  handleEvent() {
    this.bubble('system:panel', { panel: 'settings-panel' });
  }
}

const style = `
:host {
  display: block;
  cursor: pointer;
  opacity: 0.7;
}
:host(:hover) {
  opacity: 1.0;
}
.system-action {
  display: flex;
  gap: var(--space-small, 4px);
  --text: var(--white);
}
`;

const template = `
  <div class="system-action">
    <eui-icon name="profile"></eui-icon>
    <span>username</span>
  </div>
`;

definition('system-profile', {
  style,
  template,
})(SystemProfile);
