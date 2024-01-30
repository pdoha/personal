package com.hospital.admin.menus;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu {

    private final static Map<String, List<MenuDetail>> menus;
    //변동이 없으니까 처음로딩될때 만들어지게 static

    static {
        //서브메뉴를 담을 주메뉴 생성
        //주메뉴는 주메뉴 코드가있고 이걸로 서브메뉴를 조회할수있게
        menus = new HashMap<>(); //주메뉴는 map 형태
        menus.put("member", Arrays.asList( //서브메뉴는 list 형태
                new MenuDetail("list", "회원목록", "/admin/member"),
                new MenuDetail("authority", "회원권한", "/admin/member/authority")

        ));

        menus.put("center", Arrays.asList(
                new MenuDetail("list", "지점목록", "/admin/center"),
                new MenuDetail("add", "지점등록", "/admin.center/add")
        ));

        menus.put("borad", Arrays.asList(
                new MenuDetail("list", "게시판목록", "/admin/board"),
                new MenuDetail("add", "게시판등록", "/admin/board/add"),
                new MenuDetail("posts", "게시글관리", "/admin/board/posts")

        ));

        menus.put("center", Arrays.asList(

                new MenuDetail("add", "지점 목록", "/admin/center"),
                new MenuDetail("posts", "지점 등록", "/admin/center/add")


        ));

        menus.put("reservation", Arrays.asList(
                new MenuDetail("list", "예약리스트", "/admin/reservation"),
                new MenuDetail("holiday", "휴무일 관리", "/admin/reservation/holiday")

        ));
    }


    //주메뉴 코드를 가지고 서브메뉴 조회할 수있게
    //String code = 주메뉴 코드 (매개변수)
    public static List<MenuDetail> getMenus(String code){
        return menus.get(code);
    }
}
