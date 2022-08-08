let index = {
    init: function () {
        $("#btn-save").on("click", ()=> { //function(){}, ()=>{} this를 바인딩 하기위해 사용
            this.save();
        });
    },

    save: function () {
        //alert('user의 save 함수 호출됨')
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };
        //console.log(data);

        //ajax 호출 시 default가 비동기 호출
        //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
        $.ajax({
            type: "POST",
            url: "/auth/joinProc/",
            data: JSON.stringify(data), //http body 데이터
            contentType: "application/json; charset=urf-8", //body 데이터가 어떤 타입인지(MIME)
            dataType:"json" //요청을 서버로해서 응답이 왔을 때 기본적인 모든것이 문자열(생긴게 json이면 =>
                            // javascript dbject로 변경해준다다
        }).done(function (resp){
            alert("회원가입이 완료되었습니다.");
            location.href ="/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
}

index.init();