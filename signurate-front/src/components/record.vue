<template>
  <el-row>
    <el-row>
      <el-col :span="24">
        <h1>列表</h1>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="6"></el-col>
      <el-col :span="6"></el-col>
      <el-col :span="6"></el-col>
      <el-col :span="4">

        <span class="demonstration">月</span>
        <el-date-picker
          v-model="selectMonth"
          type="month"
          format="yyyy-MM"
          value-format="yyyy-MM-dd"
          placeholder="选择月">
        </el-date-picker>

      </el-col>
      <el-col :span="2">
        <el-button type="primary" v-on:click="handleSearch">查询</el-button>
      </el-col>
    </el-row>
    <el-row style="height: 5px"></el-row>
    <el-row>
      <!-- 列表 -->
      <el-table v-loading="loading" ref="multipleTable" :data="tableData"
                @selection-change="handleSelectionChange"
                tooltip-effect="dark" border
                style="width: 100%" class="el-table-type1">
        <el-table-column prop="id" fixed="left" type="selection"></el-table-column>
        <el-table-column :show-overflow-tooltip="true" prop="accountId" label="打卡日期" align="center">
          <template slot-scope="scope">
            <span>{{scope.row.workTime|filterDate}}</span>
          </template>
        </el-table-column>
        <el-table-column :show-overflow-tooltip="true" prop="accountId" label="上班时间" align="center">
          <template slot-scope="scope">
            <span>{{scope.row.workTime|filterTime}}</span>
          </template>
        </el-table-column>
        <el-table-column :show-overflow-tooltip="true" prop="bb" label="下班时间" align="center">
          <template slot-scope="scope">
            <span>{{scope.row.leaveTime|filterTime}}</span>
          </template>
        </el-table-column>


      </el-table>
      <!--分页-->
      <page-pagination :pager="pager" @change="getList"/>
      <!--分页-->
    </el-row>
  </el-row>
</template>
<script>
  export default {
    name: 'records',
    data() {
      return {
        tableData: [],
        loading: false,
        selectMonth: '',
        // 分页
        pager: {
          total: 0,
          page: 1,
          size: 10
        },
        // 选中项数据
        multipleSelection: [],
        SERVER_URL: 'http://localhost:8102/api',
      }
    },
    created() {
      this.getList();
    },
    methods: {
      handleSelectionChange: {},
      getList: function () {
        let _this = this;
        this.$axios.get(_this.SERVER_URL + '/sign/records',{params:{month:this.selectMonth}}).then(function (response) {
          _this.pager.total = parseInt(response.headers["x-total-count"]);
          _this.tableData = response.data || []
        }, function (res) {
          _this.$alert(res, '错误');
        })
      },
      handleSearch:function () {
        this.getList();
      },
      getParams:function () {
        return {}
      }
    },
    filters: {
      filterDate:function(value){
        if(value==undefined||value==null)
          return "";
        return value.substr(0,10);
      },
      filterTime:function(value){
        if(value==undefined||value==null)
          return "";
        let time =value.substr(11,21);
        let hour = time.substr(3,5);
        let suffix=' AM';
        if(parseInt(hour)>12){
          suffix=' PM';
        }
        return time+suffix;
      }
    }
  }
</script>
