// com.example.event_reservation.model.Event.java
package com.example.event_reservation.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.event_reservation.validation.ValidEventTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@ValidEventTime
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //NotBlankが使えるのは文字列（String）のみ
    @Column(name = "title")
    @NotBlank(message = "タイトルは必須です。")
    private String title;

    @Column(name = "description")
    private String description;
    
    @Column(name = "start_time")
    @NotNull(message = "開始日時は必須です。")
    @Future(message = "過去の日時は指定できません。")
    private LocalDateTime startTime;
    
    @Column(name = "end_time")
    @NotNull(message = "終了日時は必須です。")
    @Future(message = "過去の日時は指定できません。")
    private LocalDateTime endTime;
    
    @Column(name = "capacity")
    @Min(value = 0, message = "募集人数は1以上を入力してください。")
    private Integer capacity;

    //複数のSubEventを保持していることを示す
    //この関係はmappedBy:SubEventのeventプロパティで管理されていることを示す
    //cascade = CascadeType.ALL：Eventを保存・更新・削除する時に、その中にあるSubEventも一緒に保存・更新・削除される
    //orphanRemoval = true：EventのsubEventsリストから削除されたSubEventは、DBからも自動的に削除される
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    @Valid
    private List<SubEvent> subEvents = new ArrayList<>();

}

