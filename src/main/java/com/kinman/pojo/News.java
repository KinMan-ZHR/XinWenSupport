package com.kinman.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {

  private long id;
  private String title;
  private String url;
  private java.sql.Timestamp updateTime;
  private boolean category;
    private String image;
}
