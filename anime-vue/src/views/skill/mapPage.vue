<template>
  <div>
    <div class="info" id="text">
      请用鼠标在地图上操作试试
    </div>
    <div class="input-card" style="width:16rem;">
      <h4>地图点击相关事件</h4>
      <div>
        <div class="input-item">
          <button id="clickOn" class="btn" style="margin-right:1rem;">绑定事件</button>
          <button id="clickOff" class="btn">解绑事件</button>
        </div>
      </div>
    </div>
    <div id="container" style="width: 100%; height: 800px;"></div>


  </div>
</template>

<script setup>
import AMapLoader from '@amap/amap-jsapi-loader';
import { onMounted, onBeforeUnmount, ref } from 'vue';

// 高德地图密钥
const securityJsCode = ref('1431465eaaaa37c4da74c5acb568e335');
const mapInstance = ref(null); // 使用ref来引用地图实例

onMounted(() => {
  initMap();
  // // 给按钮绑定事件
  document.getElementById("clickOn").onclick = clickOn;
  document.getElementById("clickOff").onclick = clickOff;
});

onBeforeUnmount(() => {
  if (mapInstance.value && typeof mapInstance.value.destroy === 'function') {
    // 调用destroy方法（如果AMap.Map实例有此方法）
    mapInstance.value.destroy();
    mapInstance.value = null;
  }
});

const initMap = async () => {
  if (!securityJsCode.value) {
    console.error('API 密钥未设置');
    return;
  }
  console.log('使用的 API 密钥:', securityJsCode.value);
  AMapLoader.load({
    key: securityJsCode.value,
    version: "2.0",
    plugins: [
      'AMap.ElasticMarker',
      'AMap.Scale',
      'AMap.ToolBar',
      'AMap.HawkEye',
      'AMap.MapType',
      'AMap.Geolocation'
    ]
  }).then(AMap => {
    console.log('地图加载成功');
    // 使用外部的mapInstance来引用地图实例
    mapInstance.value = new AMap.Map('container', {
      viewMode: '3D',
      zoom: 5,
      center: [105.602725, 37.076636]
    });
    // 添加控件
    mapInstance.value.addControl(new AMap.ElasticMarker());
    mapInstance.value.addControl(new AMap.Scale());
    mapInstance.value.addControl(new AMap.ToolBar());
    mapInstance.value.addControl(new AMap.HawkEye());
    mapInstance.value.addControl(new AMap.MapType());
    mapInstance.value.addControl(new AMap.Geolocation());
  }).catch(e => {
    console.error('地图加载失败:', e);
  });
};

const showInfoClick=(e)=>{
  console.log('单击')
  var text = '您在 [ '+e.lnglat.getLng()+','+e.lnglat.getLat()+' ] 的位置单击了地图！'
  document.querySelector("#text").innerText = text;
}
const showInfoDbClick=(e)=>{
  console.log('双击')
  var text = '您在 [ '+e.lnglat.getLng()+','+e.lnglat.getLat()+' ] 的位置双击了地图！'
  document.querySelector("#text").innerText = text;
}
const showInfoMove=()=>{
  console.log('移动')
  var text = '您移动了您的鼠标！'
  document.querySelector("#text").innerText = text;
}

// 事件绑定
const clickOn = () => {
  console.log('绑定事件!');
  mapInstance.value.on('click', showInfoClick);
  mapInstance.value.on('dblclick', showInfoDbClick);
  mapInstance.value.on('mousemove', showInfoMove);
};
// 解绑事件
const clickOff = () => {
  console.log('解除事件绑定!');
  mapInstance.value.off('click', showInfoClick);
  mapInstance.value.off('dblclick', showInfoDbClick);
  mapInstance.value.off('mousemove', showInfoMove);
};
</script>

<style scoped>
/* input-card 样式 */
.input-card {
  background-color: #f9f9f9; /* 浅灰色背景 */
  border: 1px solid #ccc; /* 灰色边框 */
  border-radius: 8px; /* 圆角边框 */
  padding: 20px; /* 内边距 */
  margin-top: 20px; /* 顶部外边距 */
  box-shadow: 0 2px 4px rgba(0,0,0,0.1); /* 阴影效果 */
}

/* input-item 样式，虽然这里它被用作按钮的容器 */
.input-item {
  display: flex; /* 使用flex布局使按钮并排显示 */
  justify-content: space-between; /* 两端对齐 */
}

/* btn 样式 */
.btn {
  padding: 10px 20px; /* 内边距 */
  font-size: 12px; /* 字体大小 */
  color: #fff; /* 字体颜色 */
  background-color: #007bff; /* 蓝色背景 */
  border: none; /* 无边框 */
  border-radius: 4px; /* 圆角 */
  cursor: pointer; /* 鼠标悬停时显示手指指针 */
  transition: background-color 0.3s; /* 背景颜色过渡效果 */
}

/* 按钮悬停时的样式 */
.btn:hover {
  background-color: #0056b3; /* 更深的蓝色 */
}
</style>
