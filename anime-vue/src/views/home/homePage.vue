<template>
  <div class="logo-container">
    <img :src="homeBackgroudImage" alt="Logo" class="background-image">
  </div>
</template>

<script setup>
import homeBackgroudImage from '@/assets/images/德莉莎2.jpg';
</script>

<style scoped lang="less">
.logo-container {
  height: 100%; /* 尝试填充父元素的高度 */
  width: 100%; /* 填充父元素的宽度 */
  //overflow: hidden; /* 隐藏溢出的部分 */
  position: relative; /* 设置相对定位 */

  .background-image {
    position: absolute; /* 绝对定位 */
    top: 0;
    left: 0;
    width: 100%; /* 宽度填充容器 */
    height: 100%; /* 高度填充容器 */
    object-fit: cover; /* 保持图片的宽高比，并覆盖整个容器 */
  }
}
</style>
