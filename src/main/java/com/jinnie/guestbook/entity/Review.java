package com.jinnie.guestbook.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"movie","mmember"})
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewnum;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @ManyToOne(fetch=FetchType.LAZY)
    private Mmember mmember;

    private int grade;

    private String text;

    public void changeGrade(int grade){
        this.grade= grade;
    }
    public void changeText(String text){
        this.text=text;
    }
}