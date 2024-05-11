<template>
  <div>
    <div>
      <button @click="addData">增加</button>
      <button @click="deleteData">删除</button>
      <button @click="modifyData">修改</button>
      <button @click="queryData">查询</button>
      <button @click="toHomePage">返回首页</button>
    </div>
    <div>
<!--      -->
      <vxe-table
          class="self-vxe-table"
          ref="tableRef"
          height="700"
          border="full"
          :header-cell-style="headerCellStyle"
          :cell-style="cellStyle"
          header-align="center"
          align="center"
          row-id="id"
          :data="tableData"
          :radio-config="{highlight: true}"
          @cell-click="cellClickEvent"
          @radio-change="radioChangeEvent">
        <vxe-column type="radio"  width="50" title="" fixed="left"></vxe-column>
        <vxe-column field="id" title="ID" width="200" align="center" fixed="left"></vxe-column>
        <vxe-column field="num" title="序号" width="200" align="center"></vxe-column>
        <vxe-column field="name" title="姓名" width="200" align="center"></vxe-column>
        <vxe-column field="mark" title="备注" width="200"  align="center" show-overflow></vxe-column>
        <vxe-column title="操作" min-width="100" fixed="right">
          <template #default="{ row }">
            <div style="display: flex;justify-content: center;">
              <div class="djflbitembtn" @click="clearRadioRowEevnt(row)">详情</div>
            </div>
          </template>
        </vxe-column>
      </vxe-table>
    </div>
  </div>
</template>
<script setup>
import {ref,onMounted} from "vue";
import { useRouter } from 'vue-router'
import axios from "axios";

const router = useRouter()
const tableData=ref([])
// const selectRow = ref(null)
const tableRef = ref()

onMounted(() => {
  init()
});
const init=async () => {
  await queryData()
}

//获取表格数据
const addData = async () => {
  await axios.get('/hujs/datasource/add').then(res=>{
    let response=res.data
    tableData.value= response.data
    console.log('response',response)
  })
}
//获取表格数据
const deleteData = async () => {
  await axios.get('/hujs/datasource/delete').then(res=>{
    let response=res.data
    tableData.value= response.data
    console.log('response',response)
  })
}
//获取表格数据
const modifyData = async () => {
  await axios.get('/hujs/datasource/modify').then(res=>{
    let response=res.data
    tableData.value= response.data
    console.log('response',response)
  })
}
//获取表格数据
const queryData = async () => {
  await axios.get('/hujs/datasource/search').then(res=>{
    let response=res.data
    tableData.value= response.data
    console.log('response',response)
  }).catch(err=>{
    console.warn(err.message)
  })
}

//返回首页
const toHomePage = async () => {
  router.push('/') // 使用router实例的push方法进行导航
}

const cellClickEvent=()=>{
  console.log('cellClickEvent')
}

const radioChangeEvent=()=>{
  console.log('radioChangeEvent')
}

const clearRadioRowEevnt = (row) => {
  console.log(row)
}


// 头部样式
const headerCellStyle = () => {
  return {
    backgroundColor: '#F1F6FF',
    color: 'rgba(0, 0, 0, 0.85)',
    height: '40px',
    fontWeight: 500,
    fontSize: '18px'
  }
}
// 选中的数据样式
const cellStyle = () => {
  return {
    backgroundColor: '#d0dcf1',
    fontWeight: 500,
    fontSize: '18px',
    color: 'rgba(0, 0, 0, 0.85)'
  }
}

</script>
