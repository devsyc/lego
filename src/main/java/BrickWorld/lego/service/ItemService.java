package BrickWorld.lego.service;

import BrickWorld.lego.domain.Category;
import BrickWorld.lego.domain.Item;
import BrickWorld.lego.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    //저장
    @Transactional
    public Long saveItem(Item item) {
        itemRepository.save(item);
        return item.getId();
    }

    //아이템 수정(변경 감지)
    @Transactional
    public void updateItem(Long itemId, String name, int price, int quantity, int partsCount, int age, String desc, Category category) {
        Item findItem = itemRepository.findOne(itemId);
        findItem.changeItem(name, price, quantity, partsCount, age, desc, category);
    }

    //단건 조회
    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

    //전체 조회
    public List<Item> findItems() {
        return itemRepository.findAll();
    }
}
