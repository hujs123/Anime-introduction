<template>
  <div>
    <a-form :model="queryParams" ref="queryForm" layout="inline" label-width="68px">
      <a-form-item label="姓名">
        <a-input
            v-model:value="queryParams.name"
            placeholder="请输入人员姓名"
            clearable
            size="small"
            style="width: 220px"/>
      </a-form-item>
      <a-form-item label="性别">
        <a-input
            v-model:value="queryParams.gender"
            placeholder="请输入人员性别"
            clearable
            size="small"
            style="width: 220px"/>
      </a-form-item>
      <a-form-item label="种族">
        <a-input
            v-model:value="queryParams.ethnicity"
            placeholder="请输入人员种族"
            clearable
            size="small"
            style="width: 220px"/>
      </a-form-item>
      <a-form-item label="职阶">
        <a-select v-model:value="queryParams.position" placeholder="请选择人员职阶"
                  style="width: 220px">
          <a-select-option v-for="item of positionTypeList" :key="item.dictKey" :value="item.dictKey">
            {{ item.dictValue }}
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="标签">
        <a-select v-model:value="queryParams.tags" placeholder="请输入人员标签" mode="multiple"
                  style="width: 220px">
          <a-select-option v-for="item of tagTypeList" :key="item.dictKey" :value="item.dictKey">
            {{ item.dictValue }}
          </a-select-option>
        </a-select>
      </a-form-item>
    </a-form>

    <div>
      <div>
        <button @click="queryStaff">查询干员</button>
        <button @click="queryStaff">上传干员</button>
        <button @click="test">确认</button>
        <button @click="toHomePage">返回首页</button>
      </div>
    </div>

    <div>
      <vxe-table
          style="height: 600px"
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
      <a-pagination
          :current="currentPage"
          :page-size="pageSize"
          :page-size-options="pageSizeOption"
          :total="total"
          @change="handleCurrentChange"
          @showSizeChange="handleSizeChange"
          show-size-changer
          show-quick-jumper
          :show-total="getPageTotalTitle"
      >
      </a-pagination>
    </div>
  </div>
</template>
<script setup>
import {onMounted, ref} from "vue";
import {useRouter} from 'vue-router'
import {request} from "@/utils/request";

const router = useRouter()
const tableData = ref([])
const queryParams = ref({
  name: null,
  gender: null,
  ethnicity: null,
  position: null,
  tags: [],
  isStaff: null,
  pageNum: 1,
  pageSize: 10
})
//职阶类型列表
const positionTypeList = ref([])
//标签类型列表
const tagTypeList = ref([])
//分页信息
const currentPage = ref(1)
const pageSize = ref(10)
const total = ref(0)
const pageSizeOption = ref([10, 20, 50, 100])


onMounted(async () => {
  await init()
})

const init = async () => {
  await queryPostionDict()
  await queryTageDict()
  await queryStaff()

}
const test=()=>{
  console.log('tags',queryParams.value.tags)
}
//查询人员
const queryStaff = async () => {
  await request('/api/person/getPagePerson', queryParams.value, 'POST').then(res => {
    let response = res
    console.log("response", response)
    if (response.code == 0) {
      tableData.value = response.data.list
      total.value = response.data.total
    } else {
      tableData.value = []
    }
  }).catch(err => {
    console.log(err)
  })
}

// 查询干员职阶字典
const queryPostionDict = async () => {
  let param = {
    dictType: "tag_type"
  }
  await request('/api/dict/getDictPostion', param, 'POST').then(res => {
    let response = res
    console.log("response", response)
    if (response.code == 0) {
      positionTypeList.value = response.data
    } else {
      tableData.value = []
    }
  }).catch(err => {
    console.log(err)
  })
}
// 查询干员tag字典
const queryTageDict = async () => {
  let param = {
    dictType: "postion_type"
  }
  await request('/api/dict/getDictPostion', param, 'POST').then(res => {
    let response = res
    console.log("response", response)
    if (response.code == 0) {
      tagTypeList.value = response.data
    } else {
      tableData.value = []
    }
  }).catch(err => {
    console.log(err)
  })
}
//更改数据量
const handleSizeChange = (curr, size) => {
  pageSize.value = size;
  queryParams.value.pageSize = size;
  currentPage.value = 1;
  queryParams.value.pageNum = 1;
  queryStaff();
}
//更换页码
const handleCurrentChange = (curr) => {
  currentPage.value = curr;
  queryParams.value.pageNum = curr
  queryStaff();
}

const getPageTotalTitle = (total) => {
  return "共" + total + "条记录"
}
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
