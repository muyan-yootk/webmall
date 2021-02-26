amountOldValue = 0 ;
$(function() {
    $("#addBtn").on("click",function(){
        ids = "" ; // 保存所有要删除数据的id
        $("#gid:checked").each(function() {
            ids += $(this).val() + ";" ;
        })
        if (ids == "") {	// 没有要移除的商品
            operateAlert(false,"","请先选择要购买的商品！") ;
        } else {
            // window.location = "pages/front/center/orders/orders_add_pre.action?ids=" + ids ;
        }
    }) ;
    $("#editBtn").on("click",function(){	// 进行数据的修改操作
        jsonData = "[" ; // 实现json数据的拼凑
        $("input[id^=amount-]").each(function() {	// 获取所有的数据输入框
            gid = $(this).attr("id").split("-") [1] ; // 获取商品编号
            amount = $(this).val() ; // 获取设置的数量
            tempData = "{\"gid\":" + gid + ",\"amount\":" + amount + "}," ;
            jsonData = jsonData + tempData ;
        }) ;
        jsonData = jsonData.substring(0,jsonData.length - 1) + "]" ;
        console.log(jsonData) ;
        // $.post("pages/front/center/shopcar/shopcar_batch.action",{data:jsonData},
        //     function(data){
        //         operateAlert(data.flag,"购物车商品信息修改成功！","购物车商品信息修改失败！") ;
        //     },"json") ;
    }) ;
    $("#selectAll").on("click",function(){
        checkboxSelectAll('gid',this.checked) ;
    }) ;
    calcAllPrice() ; // 在页面加载完成之后进行总价的计算
    $("#rmBtn").on("click",function(){	// 进行删除事件的绑定处理
        ids = "" ; // 保存所有要删除数据的id
        $("#gid:checked").each(function() {
            ids += $(this).val() + ";" ;
            //console.log("要移除购物车的商品ID：" + $(this).val()) ;
        })
        if (ids == "") {	// 没有要移除的商品
            operateAlert(false,"","请先选择要移除的商品！") ;
        } else {	// 现在有了要删除的数据，此时应该发送ajax异步请求进行删除调用
            // $.post("pages/front/center/shopcar/shopcar_remove.action",
            //     {"ids" : ids} , function(data){
            //         if (data.flag) {
            //             $("#gid:checked").each(function() {
            //                 $("#goods-" + $(this).val()).remove() ;
            //             })
            //             calcAllPrice() ; // 在页面加载完成之后进行总价的计算
            //         }
            //         operateAlert(data.flag,"购物车商品移除成功！","购物车商品移除失败！") ;
            //     },"json") ;
        }
    }) ;
    $("input[id^=amount-]").each(function(){
        $(this).on("focus",function(){	// 获取焦点
            amountOldValue = $(this).val() ; // 获取原始数据
        }) ;
        $(this).on("blur",function(){	// 获取焦点
            val = $(this).val() ;
            if (!/^\d+$/.test(val)) {	// 不是数字
                $(this).val(amountOldValue) ;
                operateAlert(false,"","商品数量只允许输入数字！") ;
            }
            calcAllPrice() ; // 在页面加载完成之后进行总价的计算
        }) ;
    }) ;
    $("button[id^=add-]").each(function(){	// 进行事件的绑定处理
        $(this).on("click",function(){
            gid = $(this).attr("id").split("-")[1] ; // 获取gid的数据
            amount = parseInt($("#amount-" + gid).val()) ; // 获取原始的amount数据
            $("#amount-" + gid).val(amount + 1) ;
            calcAllPrice() ; // 在页面加载完成之后进行总价的计算
        }) ;
    }) ;
    $("button[id^=sub-]").each(function(){	// 进行事件的绑定处理
        $(this).on("click",function(){
            gid = $(this).attr("id").split("-")[1] ; // 获取gid的数据
            amount = parseInt($("#amount-" + gid).val()) ; // 获取原始的amount数据
            if (amount <= 0) { // 准备进行删除了，并且不允许修改
                $("#amount-" + gid).val(amount) ;
            } else {
                $("#amount-" + gid).val(amount - 1) ;
            }
        }) ;
    }) ;
    $("button[id^=updateBtn]").each(function() {
        $(this).on("click",function(){
            update_gid = $(this).attr("id").split("-")[1] ; // 获取gid的数据
            amount = parseInt($("#amount-" + update_gid).val()) ; // 获取原始的amount数据
            if (amount != 0) {
                // $.post("pages/front/center/shopcar/shopcar_edit.action",
                //     {"gid" : update_gid , "amount" : amount} , function(data){
                //         operateAlert(data.flag,"购物车商品信息修改成功！","购物车商品信息修改失败！") ;
                //     },"json") ;
            } else {	// 现在已经没有选择
                // $.post("pages/front/center/shopcar/shopcar_remove.action",
                //     {"ids" : update_gid} , function(data){
                //         console.log(update_gid) ;
                //         $("#goods-" + update_gid).remove() ;
                //         operateAlert(data.flag,"购物车商品移除成功！","购物车商品移除失败！") ;
                //     },"json") ;
            }
            calcAllPrice() ; // 在页面加载完成之后进行总价的计算
        }) ;
    })
})

function calcAllPrice() {// 计算总价	
    allPrice = 0.0 ; // 保存计算后的总价
    $("input[id^=amount-]").each(function(){	// 获取每一个数量
        gid = $(this).attr("id").split("-")[1] ; // 获取商品ID，目的是获取商品的单价
        amount = parseInt($(this).val()) ; // 将获取到的数量进行转型
        price = parseFloat($("#price-" + gid).text()) ;
        console.log("amount = " + amount + "、gid = " + gid + "、price = " + price) ;
        allPrice += amount * price ; // 进行总价的累积
    }) ;
    $("#allPrice").text(round(allPrice,2)) ;
}