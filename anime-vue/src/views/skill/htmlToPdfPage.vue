<!--html转pdf(a4与a5)-->
<template>
  <div>
    <div>{{ message }}</div>
    <button @click="printmzjf()">打印</button>
    <button @click="toHomePage">返回首页</button>
    <div>
      <div ref="printref" class="htmlToPdf-border">
        <div style="text-align: center;font-size: 30px;font-weight: 500;">
          罗德岛人员信息
        </div>
        <div>
          <div class="mzjf-data-msg">
            <div class="mzjf-data-item" style="margin-left: 60px;">名称</div>
            <div class="mzjf-data-item">种族</div>
            <div class="mzjf-data-item">性别</div>
            <div class="mzjf-data-item">职位</div>
            <div class="mzjf-data-item">是否干员</div>
            <div class="mzjf-data-item">星级</div>
          </div>
          <div>
            <span :style="{'border-top': '3px solid #000', 'margin': '5px', display: 'block'}"></span>
          </div>
          <div v-for="(item, index) in personList" :key="index">
            <div class="mzjf-data-msg" style="margin: 5px 0px">
              <div class="mzjf-data-item" style="margin-left: 60px;">{{ item.name }}</div>
              <div class="mzjf-data-item" >{{ item.ethnic }}</div>
              <div class="mzjf-data-item" >{{ item.gender }}</div>
              <div class="mzjf-data-item" >{{ item.postion }}</div>
              <div class="mzjf-data-item" >{{ item.isStaff }}</div>
              <div class="mzjf-data-item" >{{ item.star }}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import htmlTopdf from "@/utils/htmlTopdf"
import {ref} from "vue";
import dayjs from 'dayjs'
import {useRouter} from 'vue-router'


const router = useRouter() // 使用useRouter hook代替直接导入router实例
const message = ref("html转pdf");
const printref = ref()
const personList = ref([
  {name: '凯尔西', ethnic: '菲林', gender: '女', postion: '医疗干员',isStaff:'是',star:'6'},
  {name: '阿米娅', ethnic: '卡斯特', gender: '女', postion: '领袖',isStaff:'是',star:'5'},
  {name: '嘉维尔', ethnic: '阿达克利斯', gender: '女', postion: '医疗干员',isStaff:'是',star:'4'},
  {name: '华法琳', ethnic: '萨卡兹', gender: '女', postion: '医疗干员',isStaff:'是',star:'5'},
])

//打印
const printmzjf = async () => {
  console.log(printref.value)
  //参数（页面html，文件名，纸张方向，纸张大小）
  await htmlTopdf.getPdf(printref.value, dayjs().format('YYYY-MM-DD'), '0', '5').then(async res => {
    console.log('打印成功',res)
  })
}

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
