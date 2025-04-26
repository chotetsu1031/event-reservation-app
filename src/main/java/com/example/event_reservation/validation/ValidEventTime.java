// com.example.event_reservation.validation.ValidEventTime.java
package com.example.event_reservation.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

//カスタムバリデーションアノテーション：イベント全体に対するルールを定義
@Documented
@Constraint(validatedBy = EventTimeValidator.class)
@Target({ ElementType.TYPE }) // Eventクラス単位でバリデーションする
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEventTime {
    String message() default "終了日時は開始日時より後でなければなりません。";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
