const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
      devServer: {
        proxy: {
          '/api': {
            target: 'https://mock.presstime.cn/mock/6523cdda2510f7001a40236f/hujs',
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
