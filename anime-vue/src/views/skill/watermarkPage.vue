<!--水印-->
<template>
  <div>
    <div>{{ message }}</div>
    <button @click="toHomePage">返回首页</button>


  </div>
</template>
<script setup>
import {getmark} from '@/utils/watermark'
import {onMounted, ref,onBeforeUnmount} from "vue";
import {useRouter} from 'vue-router'

const message = ref("水印");
const init = () => {
  //添加水印，参数：（水印之间的高度，水印之间的宽度，文字大小，旋转角度，水印文字，）
  getmark().watermark(550,300,'45px','','水印')
}
onMounted(() => {
  init()
});

onBeforeUnmount(() => {
  //离开界面时清除水印
  getmark().clearmark()
});
const router = useRouter() // 使用useRouter hook代替直接导入router实例
const toHomePage = async () => {
  router.push('/') // 使用router实例的push方法进行导航
}
</script>

<style scoped lang="less">
.htmlToPdf-border{
  background: #FFFFFF;
  width: 800px;
  height: 500px;
  border: 1px solid #CBCBCB;
  box-shadow: 0 0 20px 0 #00000033;
  border-radius: 4px;
  align-items: center;
}
.mzjf-data-msg {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
}

.mzjf-data-item {
  font-size: 16px;
  margin-left: 2%;
  width: 13%;
}
</style>
