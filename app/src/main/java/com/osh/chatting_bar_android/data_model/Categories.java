package com.osh.chatting_bar_android.data_model;

import java.util.Arrays;

public enum Categories {
    PHOTO("사진"), VOLUNTEERING("봉사"),COSMETIC("화장"),OUTDOOR("아웃도어"),HOME_DECO("홈데코"),DANCE("댄스"),HISTORY("역사"),CAR("차"),SPORT("스포츠"),DRAMA("드라마"),MOVIE("영화"),STUDY("공부"),GAME("게임"),ART("예술"),MUSIC("음악"),BOOK("책"),HEALTH("헬스"),CODING("코딩"),FOOD("음식"),COOKING("요리"),KPOP("케이팝"),FASHION("패션"),WORK("일"),TRAVEL("여행"),KIDS("키즈");

    private final String str;
    Categories(String str) {
        this.str = str;
    }

    public String getStr()
    {
        return str;
    }

    public static Categories valueOfStr(String str) {
        for (Categories c : Arrays.asList(Categories.values())) {
            if (c.getStr().equals(str))
                return c;
        }
        return null;
    }
}
