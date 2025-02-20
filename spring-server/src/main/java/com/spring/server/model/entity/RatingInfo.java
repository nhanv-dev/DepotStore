package com.spring.server.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "rating_info")
public class RatingInfo extends BaseEntity implements Serializable {

    @Column()
    private Integer star1, star2, star3, star4, star5;

    public RatingInfo() {
        this.star1 = 0;
        this.star2 = 0;
        this.star3 = 0;
        this.star4 = 0;
        this.star5 = 0;
    }

    public void setStar(Integer star) {
        switch (star) {
            case 1:
                star1++;
                break;
            case 2:
                star2++;
                break;
            case 3:
                star3++;
                break;
            case 4:
                star4++;
                break;
            case 5:
                star5++;
                break;
        }
    }
}
