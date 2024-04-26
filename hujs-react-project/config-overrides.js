// config-overrides.js
const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function override(config, env) {
    // 扩展或修改webpack配置...
    config.devServer.before = function(app) {
        app.use(
            '/api',
            createProxyMiddleware({
                target: 'http://10.19.94.21:8080',
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            })
        );
    };
    return config;
};
