package BrickWorld.lego.domain;

import BrickWorld.lego.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;                //상품번호

    @NotEmpty
    private String name;            //상품명

    @NotEmpty
    private int price;              //상품가격

    @NotEmpty
    private int quantity;           //상품재고수량

    @NotEmpty
    private int partsCount;         //상품파츠수

    @NotEmpty
    private int age;                //상품연령

    @NotEmpty
    private String desc;            //상품설명

    private LocalDateTime date;     //상품등록시간

    @Enumerated(EnumType.STRING)
    private Category category;      //상품카테고리 [TECHNIC, MARVEL, STARWARS]

    public Item(String name, int price, int quantity, int partsCount, int age, String desc, Category category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.partsCount = partsCount;
        this.age = age;
        this.desc = desc;
        this.category = category;
        this.date = LocalDateTime.now();
    }

    /**
    * 비지니스 로직
    */

    //재고 수량 증가 로직
    public void addStock(int quantity) {
        this.quantity += quantity;
    }

    //재고 수량 감소 로직
    public void removeStock(int quantity) {
        int restStock = this.quantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.quantity = restStock;
    }
}
