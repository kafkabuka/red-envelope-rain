webpackJsonp([31],{WJsa:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n={data:function(){return{dataForm:{key:""},activityId:"",dataList:[],dataListLoading:!1,dataListSelections:[],addOrUpdateVisible:!1}},components:{AddOrUpdate:a("nTrf").default},activated:function(){this.getDataList()},methods:{getDataList:function(t){var e=this;this.activityId=t,this.dataListLoading=!0,this.$http({url:this.$http.adornUrl("/rain/activityrules/getActivityRules"),method:"get",params:this.$http.adornParams({activityId:t})}).then(function(t){var a=t.data;a&&0===a.code?e.dataList=a.activityRules:e.dataList=[],e.dataListLoading=!1})},sizeChangeHandle:function(t){this.getDataList()},currentChangeHandle:function(t){this.getDataList()},selectionChangeHandle:function(t){this.dataListSelections=t},addOrUpdateHandle:function(t){var e=this;console.log(t),this.addOrUpdateVisible=!0,this.$nextTick(function(){console.log("activityrules = id: "+t+" ,activityId: "+e.activityId),e.$refs.addOrUpdate.init(t,e.activityId)})},deleteHandle:function(t){var e=this,a=t?[t]:this.dataListSelections.map(function(t){return t.id});this.$confirm("确定对[id="+a.join(",")+"]进行["+(t?"删除":"批量删除")+"]操作?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){e.$http({url:e.$http.adornUrl("/rain/activityrules/delete"),method:"post",data:e.$http.adornData(a,!1)}).then(function(t){var a=t.data;a&&0===a.code?e.$message({message:"操作成功",type:"success",duration:1500,onClose:function(){e.getDataList(e.activityId)}}):e.$message.error(a.msg)})})}}},i={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"mod-config"},[a("el-form",{attrs:{inline:!0,model:t.dataForm},nativeOn:{keyup:function(e){if(!("button"in e)&&t._k(e.keyCode,"enter",13,e.key,"Enter"))return null;t.getDataList()}}},[a("el-form-item",[t.isAuth("rain:activityrules:save")?a("el-button",{attrs:{type:"primary"},on:{click:function(e){t.addOrUpdateHandle()}}},[t._v("新增")]):t._e(),t._v(" "),t.isAuth("rain:activityrules:delete")?a("el-button",{attrs:{type:"danger",disabled:t.dataListSelections.length<=0},on:{click:function(e){t.deleteHandle()}}},[t._v("批量删除")]):t._e()],1)],1),t._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.dataListLoading,expression:"dataListLoading"}],staticStyle:{width:"100%"},attrs:{data:t.dataList,border:""},on:{"selection-change":t.selectionChangeHandle}},[a("el-table-column",{attrs:{type:"selection","header-align":"center",align:"center",width:"50"}}),t._v(" "),a("el-table-column",{attrs:{type:"index","header-align":"center",align:"center",label:"序号"}}),t._v(" "),a("el-table-column",{attrs:{prop:"title","header-align":"center",align:"center",label:"活动名称"}}),t._v(" "),a("el-table-column",{attrs:{prop:"userLevelName","header-align":"center",align:"center",label:"会员等级"}}),t._v(" "),a("el-table-column",{attrs:{prop:"enterTimes","header-align":"center",align:"center",label:"可抽奖次数"},scopedSlots:t._u([{key:"default",fn:function(e){return[0===e.row.enterTimes?a("span",{staticStyle:{"margin-left":"10px"}},[t._v("不限制")]):a("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.enterTimes))])]}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"goalTimes","header-align":"center",align:"center",label:"最大中奖次数"},scopedSlots:t._u([{key:"default",fn:function(e){return[0===e.row.goalTimes?a("span",{staticStyle:{"margin-left":"10px"}},[t._v("不限制")]):a("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.goalTimes))])]}}])}),t._v(" "),a("el-table-column",{attrs:{fixed:"right","header-align":"center",align:"center",width:"150",label:"操作"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){t.addOrUpdateHandle(e.row.id)}}},[t._v("修改")]),t._v(" "),a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){t.deleteHandle(e.row.id)}}},[t._v("删除")])]}}])})],1),t._v(" "),t.addOrUpdateVisible?a("add-or-update",{ref:"addOrUpdate",on:{refreshDataList:t.getDataList}}):t._e()],1)},staticRenderFns:[]},l=a("VU/8")(n,i,!1,null,null,null);e.default=l.exports}});