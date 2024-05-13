let editor;
window.onload = function () {

    $('#pb_select_weak').change(function (){
        let value = $(this).val();
        if(value === "t" || value === ""){
            $('#pb_select_number').hide();
        }else{
            $('#pb_select_number').show();
        }
        replace_problem();
    })
    $('#pb_select_number').change(function (){
        replace_problem();
    })

    $('#sub_select_weak').change(function (){
        let value = $(this).val();
        if(value === "t" || value === ""){
            $('#sub_select_number').hide();
        }else{
            $('#sub_select_number').show();
        }
        replace_submit();
    })
    $('#sub_select_number').change(function (){
        replace_submit();
    })
    $('#sub_select_member').change(function (){
        replace_submit();
    })
}

$(document).ready(function () {
    util.initDatepicker();
    editor = CodeMirror.fromTextArea(document.getElementById("code"), {
        mode: "text/x-java", // Java 모드 설정
        lineNumbers: true,   // 줄 번호 표시 활성화
        theme: "default",    // 테마 설정 (다른 테마를 사용할 수 있음)
        matchBrackets: true
    });

    editor.on("change",function (){
        update_submit_detail();
    })


})

function new_problem() {

    $.ajax({
        type: "POST",
        url: "/algorithm/problem",
        contentType: 'application/json',
        success: function (response) {
            replace_problem();
        },
        error: function (xhr, status, error) {
        }
    })
}

function new_submit() {

    $.ajax({
        type: "POST",
        url: "/algorithm/submit",
        contentType: 'application/json',
        success: function (response) {
            replace_submit();
        },
        error: function (xhr, status, error) {
        }
    })
}

function update_problem(pb_id) {
    let pb = document.getElementById("pb_"+pb_id);

    let data = {
        number: pb.querySelector('[name="pb_number"]').value,
        name: pb.querySelector('[name="pb_name"]').value,
        level: pb.querySelector('[name="pb_level"]').value,
        date: pb.querySelector('[name="pb_date"]').value,
        link: pb.querySelector('[name="pb_link"]').value
    }

    $.ajax({
        type: "patch",
        url: "/algorithm/problem/" + pb_id,
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (response) {
            replace_submit()
        },
        error: function (xhr, status, error) {
        }
    })
}

function update_problem_date(pb_id) {
    let pb = document.getElementById("pb_"+pb_id);

    let data = {
        number: pb.querySelector('[name="pb_number"]').value,
        name: pb.querySelector('[name="pb_name"]').value,
        level: pb.querySelector('[name="pb_level"]').value,
        date: pb.querySelector('[name="pb_date"]').value,
        link: pb.querySelector('[name="pb_link"]').value
    }

    $.ajax({
        type: "patch",
        url: "/algorithm/problem/" + pb_id,
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (response) {
            replace_problem();
            replace_submit();
        },
        error: function (xhr, status, error) {
        }
    })
}
function update_submit_problem(submit_id){
    let sub = document.getElementById("sub_"+submit_id);

    let data = {
        problemId:sub.querySelector('[name="sub_pb"]').value,
    }
    $.ajax({
        type: "patch",
        url: "/algorithm/submit/" + submit_id + "/problem",
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (response) {
            replace_submit();
        },
        error: function (xhr, status, error) {
        }
    })
}
function remove_problem(){
    let idArray = [];

    $('input[name="pb_chk"]:checked').each(function() {
        let row = $(this).closest('.row');
        let id = row.attr("id").split("_")[1];
        idArray.push(parseInt(id));
    });

    if(idArray.length === 0){
        alert("삭제할 데이터가 없습니다");
    }

    $.ajax({
        type: "delete",
        url: "/algorithm/problem",
        contentType: 'application/json',
        data: JSON.stringify(idArray),
        success: function (response) {
            replace_problem();
            replace_submit();
        },
        error: function (xhr, status, error) {
        }
    })
}

function remove_submit(){
    let idArray = [];

    $('input[name="sub_chk"]:checked').each(function() {
        let row = $(this).closest('.row');
        let id = row.attr("id").split("_")[1];
        idArray.push(parseInt(id));
    });

    if(idArray.length === 0){
        alert("삭제할 데이터가 없습니다");
    }

    $.ajax({
        type: "delete",
        url: "/algorithm/submit",
        contentType: 'application/json',
        data: JSON.stringify(idArray),
        async:false,
        success: function (response) {
            replace_submit();
        },
        error: function (xhr, status, error) {
        }
    })
}

function replace_problem(){
    let weak = $("#pb_select_weak").val();
    let number = $("#pb_select_number").val();

    let data = {
        weak:weak,
        number:number
    }
    $.ajax({
        type: "get",
        url: "/algorithm/problem",
        data:data,
        async:false,
        success: function (html) {
            $("#pb_fragment").replaceWith(html);
            util.initDatepicker();
        },
        error: function (xhr, status, error) {
        }
    })
}

function replace_submit(){
    let weak = $("#sub_select_weak").val();
    let number = $("#sub_select_number").val();
    let memberId = $("#sub_select_member").val();

    let data = {
        weak:weak,
        number:number,
        memberId:memberId
    }
    $.ajax({
        type: "get",
        url: "/algorithm/submit",
        data:data,
        success: function (html) {
            $("#sub_fragment").replaceWith(html);
        },
        error: function (xhr, status, error) {
        }
    })
}
function autoResize(textarea) {
    textarea.style.height = 'auto'; //height 초기화
    textarea.style.height = textarea.scrollHeight + 'px';
}

function update_submit_detail(){
    let submitId = $('[name="code"]').attr('id').split("_")[1];
    let code = editor.getValue();
    let method = $("#method").val();
    let etc = $("#etc").val();
    let data = {
        code:code,
        method:method,
        etc:etc
    }
    $.ajax({
        type: "patch",
        url: "/algorithm/submit/"+submitId,
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (response) {
        },
        error: function (xhr, status, error) {
        }
    })
}

function get_submit_detail(submitId){
    $.ajax({
        type: "get",
        url: "/algorithm/submit/"+submitId,
        contentType: 'application/json',
        success: function (response) {
            let data = response.data;
            $('[name="code"]').attr('id', 'code_'+submitId);
            $('[name="method"]').val(data.method);
            $('[name="etc"]').val(data.etc);
            $("#pb_name").val(data.problemName);
            editor.setValue(data.code);
            let methodTextArea = document.getElementById('method');
            let etcTextArea = document.getElementById('etc');
            autoResize(methodTextArea);
            autoResize(etcTextArea);
            replace_canvas_pb(data.problemId)
        },
        error: function (xhr, status, error) {
        }
    })
}

function replace_canvas_pb(problemId){
    let weak = $("#sub_select_weak").val();
    let number = $("#sub_select_number").val();

    let data = {
        weak:weak,
        number:number,
    }
    $.ajax({
        type: "get",
        url: "/algorithm/submit/problem",
        data:data,
        success: function (response) {
            let dataList = response.data;
            $("#canvas_pb").empty();
            let canvas_pb = document.getElementById("canvas_pb");

            let empty = document.createElement("option");
            empty.text = "선택";
            empty.value = "";
            canvas_pb.appendChild(empty);

            let selected_index =0;
            dataList.forEach(function (data,index) {
                let value = data.value;
                let text = data.text;
                let option_element = document.createElement("option");
                option_element.text = text;
                option_element.value = value;
                canvas_pb.appendChild(option_element);
                if(value === problemId){
                    selected_index = index+1;
                }
            })
            canvas_pb.selectedIndex = selected_index;
        },
        error: function (xhr, status, error) {
        }
    })
}

function update_canvas_submit_problem(){
    let submit_id = $('[name="code"]').attr("id").split("_")[1];
    let data = {
        problemId:$("#canvas_pb").val(),
    }
    $.ajax({
        type: "patch",
        url: "/algorithm/submit/" + submit_id + "/problem",
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (response) {
            replace_submit();
        },
        error: function (xhr, status, error) {
        }
    })
}