package BrickWorld.lego.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 프로텍트 레벨 기본생성자
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;                    //주문상세번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;                  //상품

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;                //주문

    private int orderPrice;             //주문가격
    private int count;                  //주문수량

    public void addOrder(Order order) {
        this.order = order;
    }
    /**
     * 생성 메서드
     */
    public OrderItem(Item item, int orderPrice, int count) {
        this.item = item;
        this.orderPrice = orderPrice;
        this.count = count;

        item.removeStock(count);
    }

    /**
    * 비지니스 로직
    */

    //취소
    public void cancel() {
        item.addStock(count);
    }

    //금액합계
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }

}
