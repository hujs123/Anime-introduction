<template>
  <div>
    <a-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <a-form-item label="姓名">
        <a-input
            v-model="queryParams.name"
            placeholder="请输入人员姓名"
            clearable
            size="small"
            style="width: 220px"/>
      </a-form-item>
      <a-form-item label="性别">
        <a-input
            v-model="queryParams.gender"
            placeholder="请输入人员性别"
            clearable
            size="small"
            style="width: 220px"/>
      </a-form-item>
      <a-form-item label="种族">
        <a-input
            v-model="queryParams.ethnicity"
            placeholder="请输入人员种族"
            clearable
            size="small"
            style="width: 220px"/>
      </a-form-item>
      <a-form-item label="职阶">
        <a-select v-model="queryParams.position" placeholder="请选择人员职阶" clearable size="small "
                  style="width: 220px">
          <a-select-option v-for="(item,index) of rankTypeList" :key="index" :value="item.dictLabel">
            {{ item.dictLabel }}
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="标签">
        <a-input
            v-model="queryParams.tag"
            placeholder="请输入人员标签"
            clearable
            size="small"
            style="width: 220px"/>
      </a-form-item>
    </a-form>

    <div>
      <vxe-table
          :data="tableData">
        <vxe-column type="seq" width="70" title="序号"></vxe-column>
        <vxe-column field="name" title="姓名"></vxe-column>
        <vxe-column field="gender" title="性别"></vxe-column>
        <vxe-column field="ethnicity" title="种族"></vxe-column>
        <vxe-column field="position" title="职阶"></vxe-column>
        <vxe-column field="tags" title="标签"></vxe-column>
        <vxe-column field="star" title="星级"></vxe-column>
        <vxe-column field="organize" title="组织"></vxe-column>
        <vxe-column field="isStaff" title="是否干员"></vxe-column>
      </vxe-table>
    </div>

  </div>

  <div>
    <div>
      <button @click="queryStaff">查询干员</button>
<!--      <button @click="queryDict">查询干员</button>-->
      <button @click="toHomePage">返回首页</button>
    </div>
  </div>
</template>
<script setup>
import {onMounted, ref} from "vue";
import {useRouter} from 'vue-router'
import {request} from "@/utils/request";

const router = useRouter()
const tableData = ref([])
const queryParams = ref({})
const rankTypeList = ref({})
//分页信息
// const pageNum=ref()


onMounted(() => {
  init()
})

const init = async () => {
  queryParams.value = {
    name: "",
    gender: "",
    ethnicity: "",
    position: "",
    tags: "",
    isStaff: "",
    pageNum: 1,
    pageSize: 10
  }
  // await queryStaff()
  // await queryDict()
}

//查询人员
const queryStaff = async () => {
  await request('/api/person/getPagePerson', queryParams.value, 'POST').then(res => {
    let response = res
    console.log("response", response)
    if (response.code == 0) {
      tableData.value = response.data.list
    } else {
      tableData.value = []
    }
  }).catch(err => {
    console.log(err)
  })
}

//查询字典
// const queryDict = async () => {
//   // let type="postion_type"
//   await request('/api/dict/getDictPostion',{dictType:"postion_type"}, 'POST').then(res => {
//     let response = res
//     console.log("response", response)
//     if (response.code == 0) {
//       tableData.value = response.data
//     } else {
//       tableData.value = []
//     }
//   }).catch(err => {
//     console.log(err)
//   })
// }

//返回首页
const toHomePage = async () => {
  router.push('/') // 使用router实例的push方法进行导航
}


</script>
<style scoped lang="less">
.self-home {
  width: 100%;
  height: 100%;
  overflow: auto;
}

.self-home-inner {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  align-items: flex-start;

  .self-home-item {
    width: 100px;
    font-size: 16px;
    padding: 10px 12px;
    margin: 0 10px 10px 0;
    border: 1px solid #999999;
    text-align: center;
  }
}
</style>
