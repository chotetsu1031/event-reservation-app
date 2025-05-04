// com.example.event_reservation.model.Reservation.java
package com.example.event_reservation.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservation",
       uniqueConstraints = @UniqueConstraint(columnNames = {"login_user_id", "event_id"}))
@Data
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ログインユーザー（Userエンティティと紐づけ）
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User loginUser;

    // イベント（Eventエンティティと紐づけ）
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    // キャンセルフラグ（0: 通常（初期値）, 1: キャンセル）
    @Column(name = "cancel_flg", nullable = false)
    private Integer cancelFlg = 0;

    // キャンセル理由（キャンセル時のみ）
    @Column(name = "cancel_reason")
    private String cancelReason;

    // 登録日時
    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate;

    // 更新日時（キャンセルなどで更新される）
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    // 登録時に自動で設定される
    @PrePersist
    public void prePersist() {
        this.registrationDate = LocalDateTime.now();
    }

    // 自動で設定される
    @PreUpdate
    public void preUpdate() {
        this.updateDate = LocalDateTime.now();
    }
}
