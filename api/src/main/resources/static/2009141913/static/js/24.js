webpackJsonp([24],{bwrQ:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i={data:function(){return{dataForm:{key:"",hittime:[]},dataList:[],pageIndex:1,pageSize:10,totalPage:0,dataListLoading:!1,pickerOptions:{shortcuts:[{text:"最近一周",onClick:function(e){var t=new Date,a=new Date;a.setTime(a.getTime()-6048e5),e.$emit("pick",[a,t])}},{text:"最近一个月",onClick:function(e){var t=new Date,a=new Date;a.setTime(a.getTime()-2592e6),e.$emit("pick",[a,t])}},{text:"最近三个月",onClick:function(e){var t=new Date,a=new Date;a.setTime(a.getTime()-7776e6),e.$emit("pick",[a,t])}}]}}},activated:function(){this.getDataList()},methods:{getDataList:function(){var e=this;this.dataListLoading=!0,this.$http({url:this.$http.adornUrl("/rain/viewuserhit/list"),method:"get",params:this.$http.adornParams({page:this.pageIndex,limit:this.pageSize,key:this.dataForm.key,begin:void 0==this.dataForm.hittime||this.dataForm.hittime.length<=0?"":this.dataForm.hittime[0],end:void 0==this.dataForm.hittime||this.dataForm.hittime.length<=0?"":this.dataForm.hittime[1]})}).then(function(t){var a=t.data;a&&0===a.code?(e.dataList=a.page.list,e.totalPage=a.page.totalCount):(e.dataList=[],e.totalPage=0),e.dataListLoading=!1})},sizeChangeHandle:function(e){this.pageSize=e,this.pageIndex=1,this.getDataList()},currentChangeHandle:function(e){this.pageIndex=e,this.getDataList()}}},n={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"mod-config"},[a("el-form",{attrs:{inline:!0,model:e.dataForm},nativeOn:{keyup:function(t){if(!("button"in t)&&e._k(t.keyCode,"enter",13,t.key,"Enter"))return null;e.getDataList()}}},[a("el-form-item",[a("el-input",{attrs:{placeholder:"参数名",clearable:""},model:{value:e.dataForm.key,callback:function(t){e.$set(e.dataForm,"key",t)},expression:"dataForm.key"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"中奖时间"}},[a("el-date-picker",{attrs:{align:"right",type:"datetimerange","value-format":"yyyy-MM-dd HH:mm:ss","range-separator":"至","start-placeholder":"开始日期","end-placeholder":"结束日期","picker-options":e.pickerOptions},model:{value:e.dataForm.hittime,callback:function(t){e.$set(e.dataForm,"hittime",t)},expression:"dataForm.hittime"}})],1),e._v(" "),a("el-form-item",[a("el-button",{on:{click:function(t){e.getDataList()}}},[e._v("查询")])],1)],1),e._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.dataListLoading,expression:"dataListLoading"}],staticStyle:{width:"100%"},attrs:{data:e.dataList,border:""}},[a("el-table-column",{attrs:{prop:"title","header-align":"center",align:"center",label:"活动主题"}}),e._v(" "),a("el-table-column",{attrs:{prop:"type","header-align":"center",align:"center",label:"活动类型"}}),e._v(" "),a("el-table-column",{attrs:{prop:"uname","header-align":"center",align:"center",label:"用户名"}}),e._v(" "),a("el-table-column",{attrs:{prop:"realname","header-align":"center",align:"center",label:"真实姓名"}}),e._v(" "),a("el-table-column",{attrs:{prop:"idcard","header-align":"center",align:"center",label:"身份证号"}}),e._v(" "),a("el-table-column",{attrs:{prop:"phone","header-align":"center",align:"center",label:"手机号码"}}),e._v(" "),a("el-table-column",{attrs:{prop:"level","header-align":"center",align:"center",label:"用户等级"}}),e._v(" "),a("el-table-column",{attrs:{prop:"name","header-align":"center",align:"center",label:"奖品名称"}}),e._v(" "),a("el-table-column",{attrs:{prop:"price","header-align":"center",align:"center",label:"市场价"}}),e._v(" "),a("el-table-column",{attrs:{prop:"hittime","header-align":"center",align:"center",label:"中奖时间"}})],1),e._v(" "),a("el-pagination",{attrs:{"current-page":e.pageIndex,"page-sizes":[10,20,50,100],"page-size":e.pageSize,total:e.totalPage,layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":e.sizeChangeHandle,"current-change":e.currentChangeHandle}})],1)},staticRenderFns:[]},l=a("VU/8")(i,n,!1,null,null,null);t.default=l.exports}});