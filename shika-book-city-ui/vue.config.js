const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave:false,
  
})
//请求代理到后端服务器
module.exports = {
  devServer: {
    port: 80,
    proxy: 'http://127.0.0.1:8088/'
  }}
  
  
