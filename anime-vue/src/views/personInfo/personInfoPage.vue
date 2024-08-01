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
                  style="width: 220px" allowClear >
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
        <button @click="queryPerson">查询</button>
        <button @click="uploadPerson">上传</button>
        <button @click="addPerson">新增</button>
        <button @click="deletePerson">删除</button>
        <button @click="toHomePage">返回首页</button>
      </div>
    </div>

    <div>
      <vxe-table
          border
          style="height: 600px"
          :data="tableData"
          ref="tableRef">
        <vxe-column type="checkbox" width="60"></vxe-column>
        <vxe-column type="seq" width="70" title="序号"></vxe-column>
        <vxe-column field="name" title="姓名"></vxe-column>
        <vxe-column field="gender" title="性别"></vxe-column>
        <vxe-column field="ethnicity" title="种族"></vxe-column>
        <vxe-column field="position" title="职阶"></vxe-column>
        <vxe-column field="tags" title="标签"></vxe-column>
        <vxe-column field="star" title="星级"></vxe-column>
        <vxe-column field="organize" title="组织"></vxe-column>
        <vxe-column field="isStaff" title="是否干员"></vxe-column>
        <vxe-column  title="修改"></vxe-column>
        <vxe-column  title="查看图片"></vxe-column>
        <!-- 合并后的操作列 -->
        <vxe-column title="操作" min-width="200" fixed="right">
          <template #default="{ row }">
            <div>
              <a class="action-link" @click="updatePerson(row)">修改</a>   |
              <a class="action-link" @click="getPersonImage(row)">查看图片</a>
            </div>
          </template>
        </vxe-column>
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

const tableRef = ref()
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
const visible = ref(false);

onMounted(async () => {
  await init()
})

const init = async () => {
  await queryPostionDict()
  await queryTageDict()
  await queryPerson()

}

//查询人员
const queryPerson = async () => {
  await request('/api/person/getPagePerson', queryParams.value, 'POST').then(res => {
    let response = res
    console.log("response", response)
    if (response.code == 0) {
      if(response.data!=null){
        tableData.value = response.data.list
        total.value = response.data.total
      }else{
        tableData.value = []
        total.value = 0
      }
    } else {
      tableData.value = []
    }
  }).catch(err => {
    console.log(err)
  })
}

//增加人员
const addPerson = async () => {
  console.log('modelvisible.value',visible.value)
  visible.value=true
}

//上传人员
const uploadPerson = async () => {
  console.log('uploadPerson')
}


//修改人员
const updatePerson = async (row) => {
  console.log('updatePerson',row)
}

//查看人员图片
const getPersonImage=(row)=>{
  // 处理查看图片逻辑
  if (row.imageUrl) {
    // 这里可以打开图片预览对话框，或者执行其他操作
    console.log('查看图片', row.imageUrl);
  } else {
    console.log('无图片');
  }
}


//删除人员
const deletePerson = async () => {
  console.log('deletePerson')
  const $table = tableRef.value
  if ($table) {
    $table.clearCheckboxRow()
  }
}


// 查询干员职阶字典
const queryPostionDict = async () => {
  let param = {
    dictType: "postion_type"
  }
  await request('/api/dict/getDict', param, 'POST').then(res => {
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
    dictType: "tag_type"
  }
  await request('/api/dict/getDict', param, 'POST').then(res => {
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
  queryPerson();
}
//更换页码
const handleCurrentChange = (curr) => {
  currentPage.value = curr;
  queryParams.value.pageNum = curr
  queryPerson();
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

/* 使用 scoped 属性确保样式只应用于当前组件 */
.action-link {
  /* 你可以在这里添加更多的样式 */
  cursor: pointer; /* 当鼠标悬停时，光标变为小手形状 */
  /* 其他样式，如边距、填充、字体大小等 */
  margin-right: 10px; /* 例如，给链接之间添加一些间距 */
}
</style>
