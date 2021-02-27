$(function() {
    $(selectall).on("click", function() {
        $("input[id='gid']").each(function(){
            $(this).prop("checked",$(selectall).prop("checked")) ;
        }) ;
    })
    $(deleteBut).on("click", function() {
        data = "" ; // 保存要删除的商品编号
        gidArray = new Array() ; // 保存要删除 gid编号
        foot = 0 ;
        $(":checkbox[id='gid']").each(function(){
            if ($(this).prop("checked")) {
                data += $(this).val() + ";";
                gidArray[foot ++] = $(this).val() ;
            }
        }) ;
        console.log(data)
        if (data == "") {   // 此时没有选中任何的内容
            operateAlert(false,"","请先选择要删除的商品信息！") ;
        } else {
            $.get("pages/back/admin/goods/delete.action",{"data":data},function(data){
                operateAlert(data.trim() == "true","商品信息删除成功！","商品信息删除失败！") ;
                if (data.trim() == "true") {    // 删除对应的表格行的信息
                    for (x = 0 ; x < gidArray.length ; x ++) {
                        $("#goods-" + gidArray[x]).fadeOut(1000,function() {
                            $("#goods-" + gidArray[x]).remove() ;
                        }) ;
                    }
                }
            },"text") ;
        }
    })
})