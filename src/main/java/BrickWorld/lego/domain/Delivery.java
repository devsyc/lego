package BrickWorld.lego.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;                                                //배송번호

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;                                            //주문

    @Embedded
    private Address address;                                        //배송지주소

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;                                  //배송상태 [READY, PROGRESS, COMPLETE]

    private LocalDateTime deliveryDate;                             //배송날짜

    public void addOrder(Order order) {
        this.order = order;
    }
}
