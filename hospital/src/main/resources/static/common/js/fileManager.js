var commonLib = commonLib || {};
//파일 업로드
commonLib.fileManager = {
    //파일 업로드 처리
    update(){

    }
};

//이벤트 처리
//DOM이 로딩된 후에 이벤트 생성 가능
 window.addEventListener("DOMContentLoaded", function(){
    //클래스명으로 선택
    const uploadFiles = document.getElementsByClassName("upload_files");

    //input 태그 만들기 (메모리상에 만드는거라서 노출 x)
    const fileEl = document.createElement("input");
    //타입을 file 로 바꾸기
    fileEl.type = "file";

    //파일업로드 버튼 누르면 파일탐색기 열기
    for(const el of uploadFiles){
        el.addEventListener("click", function(){
            fileEl.click(); //파일 탐색기 생성

        });
    }

    //파일 선택시 이벤트 처리
    fileEl.addEventListener("change", function(e)){

    }

 })

//파일 업로드 처리
upload(files){
    console.log(files);
    try{
        if(!files || files.length == 0){
            throw new Error("업로드할 파일을 선택")
        }
    }
}