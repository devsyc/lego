package BrickWorld.lego.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String zipcode; //우편번호
    private String basic;   //기본주소
    private String detail;  //상세주소

    protected Address() {
    }

    public Address(String zipcode, String basic, String detail) {
        this.zipcode = zipcode;
        this.basic = basic;
        this.detail = detail;
    }
}
