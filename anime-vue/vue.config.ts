const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
      devServer: {
        proxy: {
          '/api': {
            // target: 'http://10.19.94.21:8081',
            target: 'http://localhost:8080',
            changeOrigin: true,
            pathRewrite: {
              '^/api': 'api'
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
