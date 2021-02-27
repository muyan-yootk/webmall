$(function(){   // 在页面加载的时候进行具体验证的实现
    $(goodsForm).validate({    // 利用JSON结构进行验证规则的定义
        debug : true , // 此配置描述的是当前的表单不允许直接提交
        submitHandler: function(form) { //  针对于表单的提交进行的处理
            form.submit() ; // 手工提交表单
        },
        highlight: function(element,errorClass) { // 进行高亮显示的配置
            elementId = element.id ; // 获取组件的id内容
            if (elementId.indexOf(".")) {
                elementId = elementId.replace(".","\\.") ; // 进行“.”的替换
            }
            divId = elementId + "Div" ; // 设置层的元素的id名称
            $("#" + divId).attr("class","form-group has-error") ; // 设置错误信息的样式
        },
        unhighlight : function(element,errorClass) {
            elementId = element.id ; // 获取组件的id内容
            if (elementId.indexOf(".")) {
                elementId = elementId.replace(".","\\.") ; // 进行“.”的替换
            }
            divId = elementId + "Div" ; // 设置层的元素的id名称
            $("#" + divId).attr("class","form-group has-success") ; // 设置错误信息的样式
        } ,
        errorPlacement : function(error,element) {
            elementId = $(element).attr("id") ; // 获取元素的id
            if (elementId.indexOf(".")) {
                elementId = elementId.replace(".","\\.") ; // 进行“.”的替换
            }
            msgId = elementId + "Msg" ; // 获取错误文本的显示元素
            $("#" + msgId).empty() ; // 清空已有内容
            $(error).attr("class","text-danger") ;
            $("#" + msgId).append(error) ; // 追加错误信息
        } ,
        success : function(error,element) {  // 操作成功
            elementId = $(element).attr("id") ; // 获取元素的id
            if (elementId.indexOf(".")) {
                elementId = elementId.replace(".","\\.") ; // 进行“.”的替换
            }
            msgId = elementId + "Msg" ; // 获取错误文本的显示元素
            $("#" + msgId).empty() ; // 清空已有内容
            $("#" + msgId).append("<span class='h2'><span class='text-success glyphicon glyphicon-ok'/></span>") ; // 追加错误信息
        } ,
        rules: {        // 定义所有要使用的验证规则
            "name" : {  // 要验证表单的id名称
                required: true   // 该内容不允许为空
            } ,
            "price": {
                required : true , // 该自动内容不允许为空
                number: true // 使用标准的邮箱格式
            }
        }
    }) ;// 验证函数
})