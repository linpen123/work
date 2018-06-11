<%@page pageEncoding="utf-8" contentType="text/html;utf-8" %>
<script type="text/javascript">
    var toolbar=[{
        text:"专辑详情",
        iconCls: 'icon-edit',
        handler: function(){
            //获取选中行对象
            var row=$("#album").treegrid("getSelected");
            //判断选中行是否为空
            if(row==null){
                $.messager.alert('警告','抱歉 你还未选中行！！！');
            }else{
                if(row.time==null){
                    //打开专辑详情的dialog
                    $("#album_dd").dialog("open");
                    //填充数据内容
                    $('#album_ff').form('load',row);
                    $('#img').prop("src",row.img);
                }else{
                    $.messager.alert('警告','请选择你要得专辑！！');
                }
            }
        }
    },'-',{
        text:'添加专辑',
        iconCls: 'icon-help',
        handler: function(){
            //打开专辑详情的dialog
            $("#album_d2").dialog("open");
        }
    },'-',{
        text:'添加章节',
        iconCls: 'icon-help',
        handler: function(){
            //获取专辑对象
            var row=$("#album").treegrid("getSelected");
            if(row==null){
                $.messager.alert('警告','请选择你要得专辑！！');
            }else{
                if(row.time==null){
                    $("#chaptor_dd").dialog("open");
                    $('#album_aid').textbox('setValue',row.id);
                }
            }
        }
    },'-',{
        text:'下载音频',
        iconCls: 'icon-help',
        handler: function(){
            var row=$("#album").treegrid("getSelected");
            location.href="${pageContext.request.contextPath}/down.do?url=" + row.urlpath + "&oldname=" + row.title;
        }
    }]
    $(function() {
        $('#album').treegrid({
            url: '${pageContext.request.contextPath}/showAll',
            idField: 'id',
            treeField: 'title',
            fit: true,
            toolbar:toolbar,
            fitColumns: true,
            pagination:true,
            pageSize:5,
            pageList:[5,10,15,20,30],
            columns: [[
                {field: 'title', title: '名称', width: 60, align: 'right'},
                {field: 'size', title: '大小', width: 80},
                {field: 'time', title: '时长', width: 80},
                {field: 'urlpath', title: '路径', width: 80}

            ]]
        });
    })
</script>
<%--展示专辑的详情--%>
<table id="album" style="width:600px;height:400px"></table>
<div id="album_dd" class="easyui-dialog" title="My Dialog" style="width:400px;height:450px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <form id="album_ff" method="post">
        <div style="text-align: center;padding-bottom:10px">
            <label for="img">图片:</label>
            <img  id="img" src="">
        </div>
        <div style="text-align: center;padding-bottom:15px">
            <label for="title">标题:</label>
            <input id="title" class="easyui-validatebox" type="text" name="title" disabled="disabled"/>
        </div>
        <div style="text-align: center;padding-bottom:15px">
            <label for="author">作者:</label>
            <input id="author" class="easyui-validatebox" type="text" name="author" disabled="disabled"/>
        </div>
        <div style="text-align: center;padding-bottom:15px">
            <label for="broadCast">播音:</label>
            <input id="broadCast" class="easyui-validatebox" type="text" name="broadCast" disabled="disabled"/>
        </div>
        <div style="text-align: center;padding-bottom:15px">
            <label for="score">评分:</label>
            <input id="score" class="easyui-validatebox" type="text" name="score" disabled="disabled"/>
        </div>
        <div style="text-align: center;padding-bottom:15px">
            <label for="count">集数:</label>
            <input id="count" class="easyui-validatebox" type="text" name="count" disabled="disabled"/>
        </div>
        <div style="text-align: center;padding-bottom:15px">
            <label for="brief">简介:</label>
            <input id="brief" class="easyui-validatebox" type="text" name="brief" disabled="disabled"/>
        </div>

    </form>
</div>
<%--添加专辑--%>
<div id="album_d2" class="easyui-dialog" title="My Dialog" style="width:400px;height:450px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,
            buttons:[{
				text:'保存',
				handler:function(){
                    $('#album_f2').form('submit', {
                        url:'${pageContext.request.contextPath}/addalbum'
                    })
				}
			},{
				text:'关闭',
				handler:function(){
				    $('#album_d2').dialog('close');
				}
			}]">
    <form id="album_f2" method="post" enctype="multipart/form-data">
        <div style="text-align: center;padding-bottom:10px">
            <label for="title1">标题:</label>
            <input id="title1" class="easyui-validatebox" type="text" name="title" />
        </div>
        <div style="text-align: center;padding-bottom:15px">
            <label for="author1">作者:</label>
            <input id="author1" class="easyui-validatebox" type="text" name="author"/>
        </div>
        <div style="text-align: center;padding-bottom:15px">
            <label for="broadCast1">播音:</label>
            <input id="broadCast1" class="easyui-validatebox" type="text" name="broadCast"/>
        </div>
        <div style="text-align: center;padding-bottom:15px">
            <label for="score1">评分:</label>
            <input id="score1" class="easyui-validatebox" type="text" name="score"/>
        </div>
        <div style="text-align: center;padding-bottom:15px">
            <label for="count1">集数:</label>
            <input id="count1" class="easyui-validatebox" type="text" name="count"/>
        </div>
        <div style="text-align: center;padding-bottom:15px">
            <label for="brief1">简介:</label>55
            <input id="brief1" class="easyui-validatebox" type="text" name="brief"/>
        </div>
        <input class="easyui-filebox" name="img" style="width:300px">
    </form>
</div>
<%--添加章节--%>
<div id="chaptor_dd" class="easyui-dialog" title="My Dialog" style="width:400px;height:350px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,
            buttons:[{
				text:'保存',
				handler:function(){
                    $('#chaptor_ff').form('submit', {
                        url:'${pageContext.request.contextPath}/addchaptor'
                    })
				}
			},{
				text:'关闭',
				handler:function(){
				    $('#chaptor_dd').dialog('close');
				}
			}]">
    <form id="chaptor_ff" method="post" enctype="multipart/form-data">
        <div style="text-align: center;padding-bottom:10px">
            <label for="title2">标题:</label>
            <input id="title2"  class="easyui-validatebox" type="text" name="title" />
        </div>
        <div style="text-align: center;padding-bottom:15px">
            <label for="size">大小:</label>
            <input id="size" class="easyui-validatebox" type="text" name="size"/>
        </div>
        <div style="text-align: center;padding-bottom:15px">
            <label for="time2">时长:</label>
            <input id="time2" class="easyui-validatebox" type="text" name="time"/>
        </div>
        <div style="text-align: center;padding-bottom:15px">
            <label for="album_aid">所属:</label>
            <input id="album_aid" class="easyui-textbox" type="text" name="aid"/>
        </div>

        <input class="easyui-filebox" name="audio" style="width:300px">
    </form>
</div>


<div id="album_paly" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">

    <audio id="audio" src="" controls="controls" autoplay="autoplay"></audio>

</div>
