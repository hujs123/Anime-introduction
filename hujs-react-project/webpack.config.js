const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = {
    devServer: {
        before(app) {
            app.use(
                '/api', // 匹配所有以 /api 开头的请求路径
                createProxyMiddleware({
                    target: 'http://10.19.94.21:8080', // 目标服务器地址
                    changeOrigin: true, // 如果接口跨域，需要进行这个参数配置
                    pathRewrite: {
                        '^/api': 'api' // 路径重写，去除请求路径中的'/api'
                    }
                })
            );
        }
    }
};
