package study.data_jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 새로운 엔티티 구별하는 방법
 */
@Entity
/*
@EntityListeners(AuditingEntityListener.class) //이걸 추가해 주어야함
*/
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item /*implements Persistable<String> */{


    @Id /*@GeneratedValue*/ //GeneratedValue이면 perirst할때 id값이 생성되서 들어간다.
    private String id;

/*    @CreatedDate //persist되기 전에 호출됨.
    private LocalDateTime createdDate;*/

    public Item(String id) {
        this.id = id;
    }
/*
    @Override
    public String getId() {
        return id;
    }

    @Override //이 메서드로 새로운 엔티티인지 아닌지 판단 -> save의 isNew 오버라이드
    public boolean isNew() {
        return createdDate == null;
    }*/
}
