webpackJsonp([3,31,32,33],{"9N8m":function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=a("OOsr"),n=a("urry"),l=a("WJsa"),r={data:function(){return{dialogTableVisible:!1,queryVo:{title:"",status:0},activityPrizes:[],activityId:"",dataList:[],pageIndex:1,pageSize:10,totalPage:0,dataListLoading:!1,ruleDialogTableVisible:!1,dataListSelections:[],addOrUpdateVisible:!1,activityPrizesVisible:!1,activityRulesVisible:!1}},components:{AddOrUpdate:i.default,ActivityPrizes:n.default,ActivityRules:l.default},activated:function(){this.getDataList()},methods:{handleClose:function(t){this.$confirm("确认关闭？").then(function(e){t()}).catch(function(t){})},getDataList:function(){var t=this;this.dataListLoading=!0,this.$http({url:this.$http.adornUrl("/rain/activity/list"),method:"post",params:this.$http.adornParams({title:this.queryVo.title,status:this.queryVo.status,page:this.pageIndex,size:this.pageSize})}).then(function(e){var a=e.data;a&&0===a.code?(console.log(a.page),t.dataList=a.page.records,t.totalPage=a.page.records.total,t.pageIndex=a.page.records.pages,t.pageSize=a.page.records.size):(t.dataList=[],t.totalPage=0),t.dataListLoading=!1})},setActivityPrize:function(t){var e=this;this.activityId=t,this.dialogTableVisible=!0,this.activityPrizesVisible=!0,this.$nextTick(function(){console.log(e.$refs),e.$refs.activityPrizes.getDataList(e.activityId)})},setActivityRule:function(t){var e=this;this.activityId=t,this.ruleDialogTableVisible=!0,this.activityRulesVisible=!0,console.log(this.$refs),this.$nextTick(function(){console.log(t),e.$refs.activityRules.getDataList(e.activityId)})},sizeChangeHandle:function(t){this.pageSize=t,this.pageIndex=1,this.getDataList()},currentChangeHandle:function(t){this.pageIndex=t,this.getDataList()},selectionChangeHandle:function(t){this.dataListSelections=t},addOrUpdateHandle:function(t){var e=this;console.log("活动 新增 / 修改"),this.addOrUpdateVisible=!0,this.$nextTick(function(){e.$refs.addOrUpdate.init(t)})},deleteHandle:function(t){var e=this,a=t?[t]:this.dataListSelections.map(function(t){return t.id});this.$confirm("确定对[id="+a.join(",")+"]进行["+(t?"删除":"批量删除")+"]操作?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){e.$http({url:e.$http.adornUrl("/rain/activity/delete"),method:"post",data:e.$http.adornData(a,!1)}).then(function(t){var a=t.data;a&&0===a.code?e.$message({message:"操作成功",type:"success",duration:1500,onClose:function(){e.getDataList()}}):e.$message.error(a.msg)})})}}},s={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"mod-config"},[a("el-form",{attrs:{inline:!0,model:t.queryVo},nativeOn:{keyup:function(e){if(!("button"in e)&&t._k(e.keyCode,"enter",13,e.key,"Enter"))return null;t.getDataList()}}},[a("el-form-item",[a("el-input",{attrs:{placeholder:"参数名",clearable:""},model:{value:t.queryVo.title,callback:function(e){t.$set(t.queryVo,"title",e)},expression:"queryVo.title"}})],1),t._v(" "),a("el-form-item",[a("el-button",{on:{click:function(e){t.getDataList()}}},[t._v("查询")]),t._v(" "),t.isAuth("rain:activity:save")?a("el-button",{attrs:{type:"primary"},on:{click:function(e){t.addOrUpdateHandle()}}},[t._v("新增")]):t._e(),t._v(" "),t.isAuth("rain:activity:delete")?a("el-button",{attrs:{type:"danger",disabled:t.dataListSelections.length<=0},on:{click:function(e){t.deleteHandle()}}},[t._v("批量删除")]):t._e()],1)],1),t._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.dataListLoading,expression:"dataListLoading"}],staticStyle:{width:"100%"},attrs:{data:t.dataList,border:""},on:{"selection-change":t.selectionChangeHandle}},[a("el-table-column",{attrs:{type:"selection","header-align":"center",align:"center",width:"50"}}),t._v(" "),a("el-table-column",{attrs:{type:"index","header-align":"center",align:"center",label:"序号"}}),t._v(" "),a("el-table-column",{attrs:{prop:"title","header-align":"center",align:"center",label:"活动主题"}}),t._v(" "),a("el-table-column",{attrs:{prop:"pic","header-align":"center",align:"center",label:"活动宣传图"}}),t._v(" "),a("el-table-column",{attrs:{prop:"info","header-align":"center",align:"center",label:"活动简介"}}),t._v(" "),a("el-table-column",{attrs:{prop:"starttime","header-align":"center",align:"center",label:"开始时间"}}),t._v(" "),a("el-table-column",{attrs:{prop:"endtime","header-align":"center",align:"center",label:"结束时间"}}),t._v(" "),a("el-table-column",{attrs:{prop:"typeName","header-align":"center",align:"center",label:"类型"}}),t._v(" "),a("el-table-column",{attrs:{fixed:"right","header-align":"center",align:"center",width:"150",label:"操作"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){t.setActivityPrize(e.row.id)}}},[t._v("奖品配置")]),t._v(" "),a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){t.setActivityRule(e.row.id)}}},[t._v("策略配置")])]}}])})],1),t._v(" "),a("el-pagination",{attrs:{"current-page":t.pageIndex,"page-sizes":[10,20,50,100],"page-size":t.pageSize,total:t.totalPage,layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":t.sizeChangeHandle,"current-change":t.currentChangeHandle}}),t._v(" "),t.addOrUpdateVisible?a("add-or-update",{ref:"addOrUpdate",on:{getDataList:t.getDataList}}):t._e(),t._v(" "),a("el-dialog",{attrs:{modal:!1,title:"奖品信息",visible:t.dialogTableVisible},on:{"update:visible":function(e){t.dialogTableVisible=e}}},[t.activityPrizesVisible?a("activity-prizes",{ref:"activityPrizes"}):t._e()],1),t._v(" "),a("el-dialog",{attrs:{modal:!1,title:"策略配置",visible:t.ruleDialogTableVisible},on:{"update:visible":function(e){t.ruleDialogTableVisible=e}}},[t.activityRulesVisible?a("activity-rules",{ref:"activityRules"}):t._e()],1)],1)},staticRenderFns:[]},o=a("VU/8")(r,s,!1,null,null,null);e.default=o.exports},OOsr:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i={data:function(){return{visible:!1,activityType:[],dataForm:{id:"",title:"",pic:"",info:"",starttime:"",endtime:"",type:"",status:""},formData:{time:[]},dataRule:{title:[{required:!0,message:"活动主题不能为空",trigger:"blur"}],pic:[{required:!0,message:"活动宣传图不能为空",trigger:"blur"}],info:[{required:!0,message:"活动简介不能为空",trigger:"blur"}]}}},methods:{init:function(t){var e=this;this.findActivityType(),this.dataForm.id=t||0,this.visible=!0,this.$nextTick(function(){e.$refs.dataForm.resetFields(),e.dataForm.id&&e.$http({url:e.$http.adornUrl("/rain/activity/info/"+e.dataForm.id),method:"get",params:e.$http.adornParams()}).then(function(t){var a=t.data;a&&0===a.code&&(e.dataForm.title=a.activity.title,e.dataForm.pic=a.activity.pic,e.dataForm.info=a.activity.info,e.dataForm.starttime=a.activity.starttime,e.dataForm.endtime=a.activity.endtime,e.dataForm.type=a.activity.type,e.dataForm.status=a.activity.status)})})},findActivityType:function(){var t=this;this.$http({url:this.$http.adornUrl("/rain/sysdict/findQuery"),method:"get",params:this.$http.adornParams({dict_type:"rain_activity_type"})}).then(function(e){var a=e.data;a&&0===a.code?(t.activityType=a.dict,console.log(t.activityType)):t.activityType=[],t.dataListLoading=!1})},dataFormSubmit:function(){var t=this;this.$refs.dataForm.validate(function(e){e?(t.dataForm.starttime=t.formData.time[0],t.dataForm.endtime=t.formData.time[1],console.log(t.dataForm),t.$http({url:t.$http.adornUrl("/rain/activity/"+(t.dataForm.id?"update":"save")),method:"post",data:t.$http.adornData({id:t.dataForm.id||void 0,title:t.dataForm.title,pic:t.dataForm.pic,info:t.dataForm.info,starttime:t.dataForm.starttime,endtime:t.dataForm.endtime,type:t.dataForm.type})}).then(function(e){var a=e.data;a&&0===a.code?t.$message({message:"操作成功",type:"success",duration:1500,onClose:function(){t.visible=!1,t.$emit("getDataList")}}):t.$message.error(a.msg)})):console.log("else")})}}},n={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("el-dialog",{attrs:{title:t.dataForm.id?"修改":"新增","close-on-click-modal":!1,visible:t.visible},on:{"update:visible":function(e){t.visible=e}}},[a("el-form",{ref:"dataForm",attrs:{model:t.dataForm,rules:t.dataRule,"label-width":"80px"},nativeOn:{keyup:function(e){if(!("button"in e)&&t._k(e.keyCode,"enter",13,e.key,"Enter"))return null;t.dataFormSubmit()}}},[a("el-form-item",{attrs:{label:"主题",prop:"title"}},[a("el-input",{attrs:{placeholder:"活动主题"},model:{value:t.dataForm.title,callback:function(e){t.$set(t.dataForm,"title",e)},expression:"dataForm.title"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"宣传图",prop:"pic"}},[a("el-input",{attrs:{placeholder:"活动宣传图"},model:{value:t.dataForm.pic,callback:function(e){t.$set(t.dataForm,"pic",e)},expression:"dataForm.pic"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"简介",prop:"info"}},[a("el-input",{attrs:{placeholder:"活动简介"},model:{value:t.dataForm.info,callback:function(e){t.$set(t.dataForm,"info",e)},expression:"dataForm.info"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"活动时间",prop:"starttime"}},[a("el-date-picker",{attrs:{"value-format":"yyyy-MM-dd HH:mm:ss",type:"datetimerange","range-separator":"至","start-placeholder":"开始日期","end-placeholder":"结束日期"},model:{value:t.formData.time,callback:function(e){t.$set(t.formData,"time",e)},expression:"formData.time"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"类型",prop:"type"}},[a("el-select",{attrs:{placeholder:"请选择活动区域"},model:{value:t.dataForm.type,callback:function(e){t.$set(t.dataForm,"type",e)},expression:"dataForm.type"}},t._l(t.activityType,function(e){return a("el-option",{key:e.dictKey,attrs:{label:e.dictValue,value:e.dictKey}},[t._v(t._s(e.dictValue))])}))],1)],1),t._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){t.visible=!1}}},[t._v("取消")]),t._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(e){t.dataFormSubmit()}}},[t._v("确定")])],1)],1)},staticRenderFns:[]},l=a("VU/8")(i,n,!1,null,null,null);e.default=l.exports},WJsa:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i={data:function(){return{dataForm:{key:""},activityId:"",dataList:[],dataListLoading:!1,dataListSelections:[],addOrUpdateVisible:!1}},components:{AddOrUpdate:a("nTrf").default},activated:function(){this.getDataList()},methods:{getDataList:function(t){var e=this;this.activityId=t,this.dataListLoading=!0,this.$http({url:this.$http.adornUrl("/rain/activityrules/getActivityRules"),method:"get",params:this.$http.adornParams({activityId:t})}).then(function(t){var a=t.data;a&&0===a.code?e.dataList=a.activityRules:e.dataList=[],e.dataListLoading=!1})},sizeChangeHandle:function(t){this.getDataList()},currentChangeHandle:function(t){this.getDataList()},selectionChangeHandle:function(t){this.dataListSelections=t},addOrUpdateHandle:function(t){var e=this;console.log(t),this.addOrUpdateVisible=!0,this.$nextTick(function(){console.log("activityrules = id: "+t+" ,activityId: "+e.activityId),e.$refs.addOrUpdate.init(t,e.activityId)})},deleteHandle:function(t){var e=this,a=t?[t]:this.dataListSelections.map(function(t){return t.id});this.$confirm("确定对[id="+a.join(",")+"]进行["+(t?"删除":"批量删除")+"]操作?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){e.$http({url:e.$http.adornUrl("/rain/activityrules/delete"),method:"post",data:e.$http.adornData(a,!1)}).then(function(t){var a=t.data;a&&0===a.code?e.$message({message:"操作成功",type:"success",duration:1500,onClose:function(){e.getDataList(e.activityId)}}):e.$message.error(a.msg)})})}}},n={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"mod-config"},[a("el-form",{attrs:{inline:!0,model:t.dataForm},nativeOn:{keyup:function(e){if(!("button"in e)&&t._k(e.keyCode,"enter",13,e.key,"Enter"))return null;t.getDataList()}}},[a("el-form-item",[t.isAuth("rain:activityrules:save")?a("el-button",{attrs:{type:"primary"},on:{click:function(e){t.addOrUpdateHandle()}}},[t._v("新增")]):t._e(),t._v(" "),t.isAuth("rain:activityrules:delete")?a("el-button",{attrs:{type:"danger",disabled:t.dataListSelections.length<=0},on:{click:function(e){t.deleteHandle()}}},[t._v("批量删除")]):t._e()],1)],1),t._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.dataListLoading,expression:"dataListLoading"}],staticStyle:{width:"100%"},attrs:{data:t.dataList,border:""},on:{"selection-change":t.selectionChangeHandle}},[a("el-table-column",{attrs:{type:"selection","header-align":"center",align:"center",width:"50"}}),t._v(" "),a("el-table-column",{attrs:{type:"index","header-align":"center",align:"center",label:"序号"}}),t._v(" "),a("el-table-column",{attrs:{prop:"title","header-align":"center",align:"center",label:"活动名称"}}),t._v(" "),a("el-table-column",{attrs:{prop:"userLevelName","header-align":"center",align:"center",label:"会员等级"}}),t._v(" "),a("el-table-column",{attrs:{prop:"enterTimes","header-align":"center",align:"center",label:"可抽奖次数"},scopedSlots:t._u([{key:"default",fn:function(e){return[0===e.row.enterTimes?a("span",{staticStyle:{"margin-left":"10px"}},[t._v("不限制")]):a("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.enterTimes))])]}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"goalTimes","header-align":"center",align:"center",label:"最大中奖次数"},scopedSlots:t._u([{key:"default",fn:function(e){return[0===e.row.goalTimes?a("span",{staticStyle:{"margin-left":"10px"}},[t._v("不限制")]):a("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.goalTimes))])]}}])}),t._v(" "),a("el-table-column",{attrs:{fixed:"right","header-align":"center",align:"center",width:"150",label:"操作"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){t.addOrUpdateHandle(e.row.id)}}},[t._v("修改")]),t._v(" "),a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){t.deleteHandle(e.row.id)}}},[t._v("删除")])]}}])})],1),t._v(" "),t.addOrUpdateVisible?a("add-or-update",{ref:"addOrUpdate",on:{refreshDataList:t.getDataList}}):t._e()],1)},staticRenderFns:[]},l=a("VU/8")(i,n,!1,null,null,null);e.default=l.exports},urry:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i={data:function(){return{dataForm:{key:""},activityId:"",dataList:[],dataListLoading:!1,dataListSelections:[],addOrUpdateVisible:!1}},components:{AddOrUpdate:a("Uzb9").default},activated:function(){this.getDataList()},methods:{getDataList:function(t){var e=this;this.activityId=t,console.log("activityprizes::"+t),this.dataListLoading=!0,this.$http({url:this.$http.adornUrl("/rain/activityprizes/getActivityPrizes"),method:"get",params:this.$http.adornParams({activityId:t})}).then(function(t){var a=t.data;a&&0===a.code?e.dataList=a.activityPrizes:e.dataList=[],e.dataListLoading=!1})},sizeChangeHandle:function(t){this.pageSize=t,this.pageIndex=1,this.getDataList()},currentChangeHandle:function(t){this.pageIndex=t,this.getDataList()},selectionChangeHandle:function(t){this.dataListSelections=t},addOrUpdateHandle:function(t){var e=this;this.addOrUpdateVisible=!0,this.$nextTick(function(){e.$refs.addOrUpdate.init(t,e.activityId)})},deleteHandle:function(t){var e=this,a=t?[t]:this.dataListSelections.map(function(t){return t.id});this.$confirm("确定对[id="+a.join(",")+"]进行["+(t?"删除":"批量删除")+"]操作?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){e.$http({url:e.$http.adornUrl("/rain/activityprizes/delete"),method:"post",data:e.$http.adornData(a,!1)}).then(function(t){var a=t.data;a&&0===a.code?e.$message({message:"操作成功",type:"success",duration:1500,onClose:function(){e.getDataList(e.activityId)}}):e.$message.error(a.msg)})})}}},n={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("el-form",{attrs:{inline:!0,model:t.dataForm},nativeOn:{keyup:function(e){if(!("button"in e)&&t._k(e.keyCode,"enter",13,e.key,"Enter"))return null;t.getDataList()}}},[a("el-form-item",[t.isAuth("rain:activityprizes:save")?a("el-button",{attrs:{type:"primary"},on:{click:function(e){t.addOrUpdateHandle()}}},[t._v("新增")]):t._e(),t._v(" "),t.isAuth("rain:activityprizes:delete")?a("el-button",{attrs:{type:"danger",disabled:t.dataListSelections.length<=0},on:{click:function(e){t.deleteHandle()}}},[t._v("批量删除")]):t._e()],1)],1),t._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.dataListLoading,expression:"dataListLoading"}],staticStyle:{width:"100%"},attrs:{data:t.dataList,border:"",height:"318"},on:{"selection-change":t.selectionChangeHandle}},[a("el-table-column",{attrs:{type:"selection","header-align":"center",align:"center",width:"50"}}),t._v(" "),a("el-table-column",{attrs:{type:"index","header-align":"center",align:"center",label:"序号"}}),t._v(" "),a("el-table-column",{attrs:{prop:"title","header-align":"center",align:"center",label:"活动"}}),t._v(" "),a("el-table-column",{attrs:{prop:"name","header-align":"center",align:"center",label:"奖品"}}),t._v(" "),a("el-table-column",{attrs:{prop:"amount","header-align":"center",align:"center",label:"数量"}}),t._v(" "),a("el-table-column",{attrs:{fixed:"right","header-align":"center",align:"center",width:"150",label:"操作"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){t.addOrUpdateHandle(e.row.id)}}},[t._v("修改")]),t._v(" "),a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){t.deleteHandle(e.row.id)}}},[t._v("删除")])]}}])})],1),t._v(" "),t.addOrUpdateVisible?a("add-or-update",{ref:"addOrUpdate",on:{refreshDataList:t.getDataList}}):t._e()],1)},staticRenderFns:[]},l=a("VU/8")(i,n,!1,null,null,null);e.default=l.exports}});