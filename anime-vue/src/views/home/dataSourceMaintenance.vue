<template>
  <div style="background-color:#f1f1f1;width: 100%;height: 100% ">
    <div style="margin: 10px 10px;display: flex; justify-content: space-between;background-color: #e6e6e6">
      <div style="display: flex; align-items: center;flex-basis: 50%">
        <img style="margin: 10px 10px;width: 20px" src="../../assets/images/case/tree_icon@2x.png" alt="数据源图标"/>
        <span style="width: 100px">数据来源维护</span>
      </div>
      <div class="self-btn" style="display: flex; justify-content: flex-end; gap: 10px;">
        <button class="self-btn-primary" @click="refreshData" icon="reload">刷新</button>
        <button class="self-btn-primary" :disabled="!disabled1" @click="addInfo" icon="plus">新增</button>
        <button class="self-btn-primary" :disabled="!disabled1 || disabled2" @click="editInfo" icon="edit">修改</button>
        <button class="self-btn-primary" :disabled="!disabled1 || disabled2" @click="deleteInfo" icon="delete">删除
        </button>
      </div>
    </div>

    <div>
      <div style="display: flex; margin: 10px 10px;background-color: #e6e6e6">
        <div style="display: flex; align-items: center;flex-basis: 60%">
          <div>
            <div   style="display: flex; align-items: center;">
              <img style="margin: 10px 10px;width: 20px" :src="require('@/assets/images/case/detail_icon@2x.png')"/>
              <span>数据来源列表</span>
            </div>
            <div>
              <a-table
                  style="margin: 10px 10px"
                  class="self-ant-table"
                  :data-source="data"
                  :columns="columns"
                  :loading="loading"
                  :pagination="pagination"
                  :rowClassName="rowStyle"
                  :rowSelection="{ selectedRowKeys: selectedRowKeysArray, onChange: onSelectChange, type: 'radio' }"
                  row-key="id"
              >
              </a-table>
<!--              :customHeaderRow="customHeaderRow"-->
<!--              :customRow="customRow"-->
            </div>
          </div>
        </div>
        <div style=" justify-content: center; align-items: center; height: 100%;display: flex; flex-direction: column; ">
          <div style="display: flex; flex-direction: column; margin: 20px;">
            <div style="display: flex; justify-content:right; align-items: center; margin-bottom: 5px;">
              <span>代码：</span>
              <input id="myInput" type="text" maxlength="20" style="width: 200px;" placeholder="输入内容...">
              <span id="charCount" class="char-count"></span>
            </div>
            <div style="display: flex; justify-content: right; align-items: center; margin-bottom: 5px;">
              <span>名称：</span>
              <input  type="text" maxlength="20" style="width: 200px;" placeholder="输入内容...">
            </div>
            <div style="display: flex; justify-content: right; align-items: center; margin-bottom: 5px;">
              <span>统计类别：</span>
              <select style="width: 205px;height:24px">
                <option v-for="(item) in selectdata" :key="item.id" :value="item.type">
                  {{ item.name }}
                </option>
              </select>
            </div>
            <div style="display: flex; justify-content: right; align-items: flex-start; margin-bottom: 5px;">
              <span>备注：</span>
              <textarea type="text" maxlength="100" style="width: 200px;height: 200px"></textarea>
            </div>
            <div class="self-btn" style="display: flex; justify-content: flex-end; margin-bottom: 5px;">
              <div>
                <button class="self-btn-primary" style="margin: 0px 70px 0px 0px">保存</button>
              </div>
              <div>
                <button class="self-btn-primary" style="margin: 0px 20px 0px 0px">取消</button>
              </div>

            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {computed, ref, onMounted} from 'vue'
import axios from "axios";

const disabled1 = ref(false); // 假设的 disabled1 状态
const disabled2 = ref(false); // 假设的 disabled2 状态
const data = ref([]); //表格数据
const selectdata = ref([]); //下拉框数据
const loading = ref(false)
const selectedRowKeysArray = ref([]); // 用来保存当前选中的行的key


const total = ref(0)
const current = ref(1)
const pageSize = ref(10)
const pagination = computed(() => ({
  total: total.value,
  current: current.value,
  pageSize: pageSize.value,
  showSizeChanger: true,
  showQuickJumper: true,
  // showTotal: (total: number) => `总 ${total} 条`
}))

onMounted(() => {
  init()
});

const init = async () => {
  //获取表格数据
  await getDataList()
  //获取下拉框类别
  await getSelectList()
}

const rowStyle=()=>{
  return 'newRowStyle' //返回到行样式
}
const columns = [
  { title: '序号', dataIndex: 'id', width:'70px',align:'center',key: 'id',},
  { title: '代码', dataIndex: 'num', width:'100px',key: 'num' },
  { title: '名称', dataIndex: 'name', width:'200px',ellipsis: true,key: 'name' },
  { title: '备注', dataIndex: 'mark', key: 'mark' },
];

const onSelectChange=(selectedRowKeys, selectedRows)=> {
  console.log('selectedRowKeys',selectedRowKeys[0])
  console.log('selectedRows',selectedRows[0])
  selectedRowKeysArray.value = selectedRowKeys;
  console.log('selectedRowKeysArray.value',selectedRowKeysArray.value)
}

const getDataList = async () => {
  loading.value = true
  await axios.get('https://mock.presstime.cn/mock/6523cdda2510f7001a40236f/hujs/datasource/search').then(res => {
    let response = res.data
    console.log('调用失败', response)
    if (response.code == 0) {
      console.log('调用成功')
      data.value = response.data
    } else {
      data.value = []
      console.log('调用失败')
    }
  }).finally(() => {
    console.log(data.value)
    loading.value = false
  })
}

const getSelectList=async () => {
  await axios.get('https://mock.presstime.cn/mock/6523cdda2510f7001a40236f/hujs/datasource/getSelectList').then(res => {
    let response = res.data
    console.log('调用失败', response)
    if (response.code == 0) {
      console.log('调用成功')
      selectdata.value = response.data
    } else {
      selectdata.value = []
      console.log('调用失败')
    }
  }).finally(() => {
    console.log(selectdata.value)
  })
}
// 方法
const refreshData = () => {
  init();
  // 刷新数据的逻辑
  console.log('刷新数据');
};

const addInfo = () => {
  // 新增数据的逻辑
  console.log('新增数据');
};

const editInfo = () => {
  // 修改数据的逻辑
  console.log('修改数据');
};

const deleteInfo = () => {
  // 删除数据的逻辑
  console.log('删除数据');
};
</script>

<style scoped>
.newRowStyle {
  background-color: orange;
  line-height: 20px;
}
</style>

