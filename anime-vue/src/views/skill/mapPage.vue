
<template>
  <div>
    <div id="container" style="width: 100%; height: 800px;"></div> <!-- 建议直接在模板中设置样式 -->
  </div>
</template>

<script setup>
/* eslint-disable */
import AMapLoader from '@amap/amap-jsapi-loader';
import { onMounted, ref } from 'vue';

//高德地图密钥
const securityJsCode=ref('424072874f6e107a82f1238e230b1931')
const map = ref(null); // 使用ref来引用地图实例

onMounted(() => {
  initMap();
});

const initMap = async () => {
  AMapLoader.load({
    key: securityJsCode, // 请替换为您的API密钥
    version: "2.0",
    plugins: [] // 如果不需要加载任何插件，则留空
  }).then(AMap => {
    map.value = new AMap.Map('container', {
      viewMode: '3D',
      zoom: 5,
      center: [105.602725, 37.076636]
    });
  }).catch(e => {
    console.error(e); // 使用console.error以便在控制台中更容易区分错误
  });
};
</script>

<style scoped>
#container {
  padding: 0;
  margin: 0;
  width: 100%;
  height: 400px; /* 或者您想要的高度 */
}
</style>
