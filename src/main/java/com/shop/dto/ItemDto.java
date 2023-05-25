package com.shop.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ItemDto {
    private Long id;
    private String itemName;
    private Integer price;
    private String itemDetail;
    private String sellStatCd;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;

    @Builder
    public ItemDto(String itemName, Integer price, String itemDetail, String sellStatCd, LocalDateTime regTime, LocalDateTime updateTime) {
        this.itemName = itemName;
        this.price = price;
        this.itemDetail = itemDetail;
        this.sellStatCd = sellStatCd;
        this.regTime = regTime;
        this.updateTime = updateTime;
    }
}
