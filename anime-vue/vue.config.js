// vue.config.js
const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
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
          '/hujs': {
            target: 'https://mock.presstime.cn/mock/6523cdda2510f7001a40236f/',
            changeOrigin: true,
            pathRewrite: {
              '^/hujs': 'hujs'
            },

          }
        }
      }
    }
)
