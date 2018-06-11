<%@page pageEncoding="utf-8" contentType="text/html;utf-8" isELIgnored="false" %>
<script type="text/javascript">
    var toolbar=[{
        iconCls: 'icon-add',
        text:'添加',
        handler: function(){
            $("#dd").dialog("open");
        }
    },'-',{
        iconCls: 'icon-remove',
        text:'删除',
        handler: function(){
            var row=$("#dg").edatagrid("getSelected");
            if(row==null){
                $.messager.alert('警告','请选中你要得行');
            }else{
                console.log(row.id);
                $('#dg').edatagrid('destroyRow');
            }
        }
    },'-',{
        iconCls: 'icon-edit',
        text:'修改',
        handler: function(){
            var row=$("#dg").edatagrid("getSelected");
            if(row==null){
                $.messager.alert('警告','请选中你要得行');
            }else{
                $("#dg").edatagrid("editRow", $("#dg").edatagrid("getRowIndex", row))
            }
        }
    },'-',{
        iconCls: 'icon-save',
        text:'保存',
        handler: function(){
            $('#dg').edatagrid('saveRow');
        }
    },'-',{
        iconCls: 'icon-search',
        text:'自定义按钮',
        handler: function(){
            $("#poi_dialog").dialog("open")
        }
    }];
    $("#btn").click(function(){
        var titles=$("#pic_select").combotree("getText");
        var value=$("#pic_select").combotree("getValues");
        var c="";
        $.each(value,function(index,title){
            if(index!=value.length-1){
                c+=title+",";
            }else{
                c+=title;
            }
        })
        console.log(titles);
        console.log(c)
        $("#poi_ff").form("submit",{
            queryParams: {
                "titles":titles,
                "fileds":c
            },
            url:"${pageContext.request.contextPath}/importUser"
        })

    })



    $(function(){
        $('#dg').edatagrid({
            fit:true,
            fitColumns:true,
            pagination:true,
            pageList:[5,10,15,20],
            pageSize:5,
            url:'${pageContext.request.contextPath}/showall',
            destroyUrl:'${pageContext.request.contextPath}/delete',
            updateUrl:'${pageContext.request.contextPath}/update',
            columns:[[
                {field:'title',title:'标题',width:100,
                    editor:{
                        type:'text',
                        options:{required:true}
                    }},
                {field:'status',title:'状态',width:100,
                    editor:{
                        type: 'text',
                        options: {required: true}
                    }},
                {field:'imgpath',title:'图片',width:100},
                {field:'date',title:'日期',width:100,align:'right',
                    formatter:function(value){
                        var date=new Date(parseInt(value));
                        var y=date.getFullYear();
                        var m=date.getMonth()+1;
                        var d=date.getDate();
                        return y+'-'+m+'-'+d;
                    }}
            ]],
            toolbar:toolbar,
            view: detailview,
            detailFormatter: function(rowIndex, rowData){
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="' + rowData.imgpath + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>Attribute: ' + rowData.des + '</p>' +
                    '<p>Status: ' + rowData.status + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }

        });

    })

</script>

<table id="dg"></table>
<div id="dd" class="easyui-dialog" title="添加页面" style="width:400px;height:260px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
						    text:'取消',
                            iconCls:'icon-redo',
                            handler:function(){
                                $('#ff').form('clear');
                        }
                        },{
                            text:'保存',
                            iconCls:'icon-ok',
                            handler:function(){
                                $('#ff').form('submit',{
                                    url:'${pageContext.request.contextPath}/insertBanner'
                                });
                            }

                        }]">
    <form id="ff" method="post" enctype="multipart/form-data">
        <div style="text-align: center;padding-bottom:10px">
            <label for="title">标题:</label>
            <input id="title" class="easyui-validatebox" type="text" name="title" data-options="required:true" />
        </div>
        <div style="text-align: center;padding-bottom:15px">
            <label for="des">描述:</label>
            <input id="des" class="easyui-validatebox" type="text" name="des" data-options="required:true" />
        </div>
        <div style="text-align: center;padding-bottom:15px">
            <label for="status">状态:</label>
            <select id="status" class="easyui-combobox" name="status" style="width:200px;">
                <option value="y">在线</option>
                <option value="n">下线</option>
            </select>
        </div>
       <div style="text-align: center;padding-bottom:15px">
            <label for="date">日期:</label>
            <input  id="date" name="date" type= "text" class= "easyui-datebox" required ="required"> </input>
        </div>
        <input class="easyui-filebox" name="img" style="width:300px">

    </form>
</div>

<div id="poi_dialog" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <form id="poi_ff" method="post">
        <select id="pic_select" class="easyui-combotree" style="width:200px;"
                data-options="url:'${pageContext.request.contextPath}/json/comboTree.json',required:true,method:'get',checkbox:true,onlyLeafCheck:true,multiple:true"></select>
        <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确定导出</a>
    </form>
</div>

