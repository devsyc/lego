package BrickWorld.lego.service;

import BrickWorld.lego.domain.Category;
import BrickWorld.lego.domain.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {


    @Autowired
    ItemService itemService;

    /**
    * 상품 저장 테스트
    */
    @Test
    public void testSaveItem() throws Exception
    {
        //given
        Item item = new Item("ItemA", 10000, 10, 4500, 12, "text123", Category.TECHNIC);

        //when
        Long itemId = itemService.saveItem(item);

        //then
        Assertions.assertEquals(item, itemService.findOne(itemId));
    }

    /**
    * 상품 업데이트 테스트
    */
    @Test
    public void testUpdateItem() throws Exception
    {
        //given
        Item item = new Item("ItemA", 10000, 10, 4500, 12, "text123", Category.TECHNIC);
        Long itemId = itemService.saveItem(item);

        //when
        itemService.updateItem(itemId, "ItemB", 20000, 10, 4500, 12, "text234", Category.TECHNIC);
        Item findItem = itemService.findOne(itemId);

        //then
        Assertions.assertEquals("ItemB", findItem.getName(),"변경된 상품의 이름");


    }
}