const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: ["vuetify"],
  lintOnSave: false,
  devServer: {
    proxy: {
      '/zippy': { 
        target: 'http://localhost:8090',
        changeOrigin: true,
      }
    }
  }
});
