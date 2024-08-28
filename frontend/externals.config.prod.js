const externals = {
  apps: [{
    path: "app-1",
    entry: "App1"
  }],
  components: {
    default: [],
    shareable: [{
      path: "my-component",
      entry: "MyComponent"
    }]
  },
  panels: [],
  plugins: []
};
module.exports = externals;