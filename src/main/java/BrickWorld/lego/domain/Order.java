package BrickWorld.lego.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;                                                    //주문번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;                                              //주문회원

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();             //주문상세

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;                                          //배송

    private LocalDateTime orderDate;                                    //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;                                    //주문상태 [ORDER,CANCEL,DELIVERY]

    //주문상태 변경 메소드
    public void changeStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 연관관계 메서드
     */

    //주문상세추가
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.addOrder(this);
    }

    //배송추가
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.addOrder(this);
    }

    /**
    * Order 생성자
    */
    public Order(Member member, List<OrderItem> orderItems, Delivery delivery, LocalDateTime orderDate, OrderStatus orderStatus) {
        this.member = member;
        this.orderItems = orderItems;
        this.delivery = delivery;
        this.orderDate = LocalDateTime.now();
        this.orderStatus = OrderStatus.ORDER;
    }

    /**
    * 비지니스 로직
    */

    //주문 취소 로직
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMPLETE) {
            throw new IllegalArgumentException("이미 배송 완료된 상품은 취소가 불가능합니다.");
        }
        this.changeStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    //전체 주문 가격 조회
    public int getTotalPrice() {
        int totalPrice = orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();
        return totalPrice;
    }
}
