<template>
  <div id="mapContainer" style="width: 100%; height: 400px;"></div>
</template>

<script setup>
import { onMounted } from 'vue';
// import AMap from "core-js/internals/map-helpers";

onMounted(() => {
  initMap();
});

const initMap = () => {
  // const map = new AMap.Map('mapContainer', {
  //   resizeEnable: true,
  //   zoom: 4, // 初始地图层级
  //   center: [116.397428, 39.90923] // 初始地图中心点
  // });
  // 可以添加更多地图功能，如标记点、线条、多边形等
};
</script>
