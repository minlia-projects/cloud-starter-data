package com.minlia.module.data.adapter;

import com.minlia.module.data.body.PageResponseBody;
import java.util.ArrayList;

public class PageResponseBodyAdapter {

  public static PageResponseBody adapt(org.springframework.data.domain.Page page) {
    PageResponseBody result = new PageResponseBody();
    if (null != page.getContent() && page.getContent().size() > 0) {
      result.setItems(page.getContent());
    } else {
      result.setItems(new ArrayList());
    }

    if (page.getNumber() > -1) {
      result.setPage(Integer.valueOf(page.getNumber()+1).longValue());
    }

    if (page.getSize() > 0) {
      result.setSize(Integer.valueOf(page.getSize()).longValue());
    }

    if (page.getTotalPages() > 0) {
      result.setTotalPages(Integer.valueOf(page.getTotalPages()).longValue());
    }

    if (page.getTotalElements() > 0) {
      result.setTotalElements(page.getTotalElements());
    }

    return result;
  }


  public static PageResponseBody adapt(com.baomidou.mybatisplus.plugins.Page page) {
    PageResponseBody result = new PageResponseBody();
    if (null != page.getRecords() && page.getRecords().size() > 0) {

      result.setItems(page.getRecords());
    } else {
      result.setItems(new ArrayList());
    }

    if (page.getCurrent() > -1) {
      result.setPage(Integer.valueOf(page.getCurrent()).longValue());
    }

    if (page.getSize() > 0) {
      result.setSize(Integer.valueOf(page.getSize()).longValue());
    }

    if (page.getPages() > 0) {
      result.setTotalPages(page.getPages());
    }

    if (page.getTotal() > 0) {
      result.setTotalElements(page.getTotal());
    }
    return result;
  }
}
