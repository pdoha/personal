package com.hospital.admin.board;

import com.hospital.admin.menus.Menu;
import com.hospital.admin.menus.MenuDetail;
import com.hospital.commons.ExceptionProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller("adminBoardController")
@RequestMapping("/admin/board")
public class BoardController implements ExceptionProcessor {

    @ModelAttribute("menuCode")
    public String getMenuCode(){ //주메뉴 코드
        return "board";
    }

    @ModelAttribute("subMenus")
    public List<MenuDetail> getSubMenus(){
        return Menu.getMenus("board");
    }

    //게시판 목록
    @GetMapping
    public String list(Model model){
        commonProcess("list", model);
        return "admin/board/list";
    }

    //게시판 등록
    @GetMapping("/add")
    public String add(Model model){
        commonProcess("add", model);
        return "admin/board/add";
    }

    //(게시판 등록/수정 동시에 공유
    @PostMapping("/save")
    public String save(Model model){
        commonProcess("save", model);
        return "redirect:/admin/board";

    }

    //게시글 관리
    @GetMapping("/posts")
    public String posts(Model model){
        commonProcess("posts", model);
        return "admin/board/posts";
    }

    //공통처리 (페이지제목, css, js 등)
    private void commonProcess(String mode, Model model){
        String pageTitle = "게시판 목록";
        //모드값이 없을때 기본값 list
        mode = StringUtils.hasText(mode) ? mode : "list";



        if(mode.equals("add")){
            pageTitle = "게시판 등록";
        } else if(mode.equals("edit")){
            pageTitle = "게시글 수정";
        } else if (mode.equals("posts")){
            pageTitle = "게시글 관리";
        }

        //공통
        List<String> addCss = new ArrayList<>(); // CSS 추가

        //양식에 필요한 스크립트 ( 게시판 등록, 수정에 다 필요하니까 form)
        List<String> addScript = new ArrayList<>();

        //추가 등록 수정에 필요한 공통적인 자바스크립트 (관리자쪽에 필요한 설정
        List<String> addCommonScript = new ArrayList<>();


        if (mode.equals("add") || mode.equals("edit")){ //게시판 등록 또는 수정일때
            addCommonScript.add("ckeditor5/ckeditor"); //에디더 추가
            addScript.add("borad/form"); //보드 양식

        }



        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("subMenuCode", mode); //서브메뉴코드는 모드값과 동일하게
        model.addAttribute("addCommonScript", addCommonScript);
        model.addAttribute("addScript", addScript);
    }
}


