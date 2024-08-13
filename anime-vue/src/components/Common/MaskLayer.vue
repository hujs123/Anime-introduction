<template>
  <div>
    <!-- 遮罩层，默认不显示 -->
    <div class="overlay" v-if="isModalVisible"></div>
  </div>
</template>
<script setup>
import {defineProps, ref, watch} from 'vue';

const props = defineProps({
  visibleLayer: Boolean
});

const isModalVisible = ref(false)

// 监听 prop 的变化以重新初始化 menuItems
watch(() => props.visibleLayer, (newValue) => {
  isModalVisible.value = newValue
  console.log('是否显示',newValue)
});




</script>
<style scopedL>
.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5); /* 半透明黑色 */
  z-index: 1000; /* 确保遮罩层在背景内容之上，但在弹窗之下 */
  pointer-events: auto; /* 如果需要遮罩层也能响应点击事件 */
}

.modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 1001; /* 确保弹窗在遮罩层之上 */
  /* 其他弹窗样式 */
}
</style>
