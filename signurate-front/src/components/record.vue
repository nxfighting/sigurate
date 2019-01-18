<template>
 <el-row>
   <el-row>
     <el-col :span="24">
       <h1>列表</h1>
     </el-col>
   </el-row>
   <el-row>
     <!-- 列表 -->
     <el-table  v-loading="loading" ref="multipleTable" :data="tableData"
                @selection-change="handleSelectionChange"
                tooltip-effect="dark" border
                style="width: 100%" class="el-table-type1">
       <el-table-column prop="id" fixed="left" type="selection"></el-table-column>
       <el-table-column :show-overflow-tooltip="true" prop="accountId" label="开始时间" align="center">
         <template slot-scope="scope">
           <span>{{scope.row.workTime}}</span>
         </template>
       </el-table-column>
       <el-table-column :show-overflow-tooltip="true" prop="bb" label="结束时间" align="center">
         <template slot-scope="scope">
           <span>{{scope.row.leaveTime}}</span>
         </template>
       </el-table-column>


     </el-table>
     <!--分页-->
     <page-pagination :pager="pager" @change="getList" />
     <!--分页-->
   </el-row>
 </el-row>
</template>
<script>
  export default {
    name:'records',
    data() {
      return {
        tableData: [],
        loading: false,
        // 分页
        pager: {
          total: 0,
          page: 1,
          size: 10
        },
        // 选中项数据
        multipleSelection: [],
        SERVER_URL:'http://localhost:8102/api',
      }
    },
    created(){
      this.getList();
    },
    methods :{
      handleSelectionChange:{

      },
      getList:function () {
        let _this =this;
        this.$axios.get(_this.SERVER_URL+'/sign/records').then(function(response){
          _this.pager.total = parseInt(response.headers["x-total-count"]);
          _this.tableData = response.data || []
        },function(res){
          _this.$alert(res,'错误');
        })
      }
    },
    filters : {

    }
  }
</script>
