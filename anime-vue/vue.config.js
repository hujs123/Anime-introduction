const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
      devServer: {
        proxy: {
          '/api': {
            target: 'http://10.19.94.21:8080',
            changeOrigin: true,
            pathRewrite: {
              '^/api': 'api'
            }
          }
        }
      }
    }
)
