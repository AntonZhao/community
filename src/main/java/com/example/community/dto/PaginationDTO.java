package com.example.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO<T> {
    private List<T> data;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;

    private Integer page;
    private Integer totalPage;
    private List<Integer> pages = new ArrayList<>();

    public void setPagination(Integer totalPage, Integer page) {
        this.page = page;
        this.totalPage = totalPage;

        if (page < 1) page = 1;
        if (page > totalPage) page = totalPage;

        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) pages.add(0, page - i);
            if (page + i <= totalPage) pages.add(page + i);
        }

        //是否展示上一页
        showPrevious = page == 1 ? false : true;
        //是否展示下一页
        showNext = page == totalPage ? false : true;
        //是否展示第一页
        showFirstPage = pages.contains(1) ? false : true;
        //是否展示最后一页
        showEndPage = pages.contains(totalPage) ? false : true;
    }
}
