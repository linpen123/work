<%@page pageEncoding="utf-8" contentType="text/html;utf-8" isELIgnored="false" %>
<script type="text/javascript">

    $(function(){
        $('#dg_log').datagrid({
            fit:true,
            fitColumns:true,
            pagination:true,
            pageList:[5,10,15,20],
            pageSize:5,
            url:'${pageContext.request.contextPath}/log/show',
            columns:[[
                {field:'id',title:'编号',width:100,},
                {field:'name',title:'姓名',width:100,},
                {field:'status',title:'状态',width:100,},
                {field:'setWhy',title:'事情',width:100},
                {field:'setTime',title:'时间',width:100,align:'right',
                    formatter:function(value){
                        var date=new Date(parseInt(value));
                        var y=date.getFullYear();
                        var m=date.getMonth()+1;
                        var d=date.getDate();
                        return y+'-'+m+'-'+d;
                    }}
            ]],
        });

    })

</script>

<table id="dg_log"></table>
<div id="dd_log" class="easyui-dialog" title="展示页面" style="width:400px;height:260px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
</div>
