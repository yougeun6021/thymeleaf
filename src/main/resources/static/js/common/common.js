function logout() {
    if (confirm("로그아웃 하시겠습니까?")) {
        location.href = "/logout";
    }
}


let util = ({
    /**
     * datepicker 초기화
     */
    initDatepicker: function () {
        // Datepicker 초기화
        $('[data-toggle="datepicker"]').datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true
        });
    },
    copy:function(id) {
        // Get the text field
        let copyText = document.getElementById(id);

        // Select the text field
        copyText.select();
        copyText.setSelectionRange(0, 99999); // For mobile devices

        // Copy the text inside the text field
        navigator.clipboard.writeText(copyText.value);

        // Alert the copied text
        alert("복사되었습니다");
    },

    getToday:function (){
        const today = new Date();
        return this.getFormattedDay(today);
    },
    getFormattedDay:function (date){
        let year = date.getFullYear(); // 년도
        let month = date.getMonth() + 1; // 월 (0부터 시작하므로 1을 더함)
        let day = date.getDate(); // 일

        if (month < 10) {
            month = '0' + month;
        }
        if (day < 10) {
            day = '0' + day;
        }
        return year + '-' + month + '-' + day;
    }
})
