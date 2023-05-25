package com.shop.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {

    @PersistenceContext
    EntityManager em;
    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest() {
        // given
        Item item = Item.builder()
                .itemName("test")
                .price(10000)
                .itemDetail("test item detail")
                .itemSellStatus(ItemSellStatus.SELL)
                .stockNumber(100)
                .regTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();

        // when
        Item savedItem = itemRepository.save(item);

        // then
        assertThat(savedItem).isEqualTo(item);
    }

    public void createSampleItems() {
        for (int i = 1; i <= 10; i++) {
            Item item = Item.builder()
                    .itemName("test" + i)
                    .price(10000 + i)
                    .itemDetail("test item detail" + i)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .stockNumber(100)
                    .regTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build();
            itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNameTest() {
        // given
        this.createSampleItems();
        List<Item> all = itemRepository.findAll();
        assertThat(all.size()).isEqualTo(10);

        // when
        List<Item> itemList = itemRepository.findByItemName("test1");

        // then
        assertThat(itemList.size()).isEqualTo(1);


    }

    @Test
    public void queryDslTest() {
        createSampleItems();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

    }


}