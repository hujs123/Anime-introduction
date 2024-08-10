// vue.config.js
const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  //让构建后的资源使用相对路径引用，而不是绝对路径。
  publicPath: './',
  transpileDependencies: true,
      devServer: {
        port: 8082,
        proxy: {
          '/api': {
            target: 'http://localhost:8081',
            changeOrigin: true,
            pathRewrite: {
              '^/api': '/api'
            }
          },
          '/mock': {
            target: 'https://mock.presstime.cn/mock/6523cdda2510f7001a40236f/',
            changeOrigin: true,
            pathRewrite: {
              '^/mock': 'mock'
            },

          }
        }
      }
    }
)
