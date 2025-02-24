package com.aloha.board.doamin;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Board 엔티티 클래스
 * 
 * 이 클래스는 게시판의 게시글을 나타냅니다.
 * 각 게시글은 고유의 번호(no), ID, 제목(title), 작성자(writer), 내용(content),
 * 생성일(createdAt), 수정일(updatedAt)을 가집니다.
 * 
 * - no: 게시글의 고유 번호 (자동 생성)
 * - id: 게시글의 고유 ID (UUID로 자동 생성)
 * - title: 게시글의 제목
 * - writer: 게시글의 작성자
 * - content: 게시글의 내용
 * - createdAt: 게시글의 생성일 (자동 생성)
 * - updatedAt: 게시글의 수정일 (자동 갱신)
 * 
 * 이 클래스는 Lombok을 사용하여 getter, setter, builder, all-args constructor를 자동 생성합니다.
 * 또한 JPA를 사용하여 데이터베이스와 매핑됩니다.
 */
@Data                           // getter, setter, toString, equals, hashCode 메서드를 자동 생성합니다.
@Builder                        // 빌더 패턴 클래스를 자동 생성합니다.
@AllArgsConstructor             // 모든 필드를 인자로 받는 생성자를 자동 생성합니다.
@Entity                         // 이 클래스를 JPA 엔티티로 지정합니다.
@Table(name = "board")          // 엔티티와 매핑할 테이블을 지정합니다.
public class Board {

    @Id                                                 // 기본 키를 지정합니다.
    // @GeneratedValue(strategy = GenerationType.AUTO)  // 기본 키 생성을 자동으로 설정합니다.       
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 데이터베이스에 위임합니다.
    private Long no;                                    // 게시글의 고유 번호

    @Column(unique = true, nullable = false)            // 엔티티의 필드를 컬럼으로 매핑합니다.
    private String id;                                  // 게시글의 고유 ID
    private String title;                               // 게시글의 제목
    private String writer;                              // 게시글의 작성자
    private String content;                             // 게시글의 내용

    @CreationTimestamp                                  // Hibernate 어노테이션: 엔티티가 생성될 때의 타임스탬프를 자동으로 설정합니다.
    private Date createdAt;                             // 게시글의 생성일
    
    @UpdateTimestamp                                    // Hibernate 어노테이션: 엔티티가 수정될 때의 타임스탬프를 자동으로 설정합니다.
    private Date updatedAt;                             // 게시글의 수정일

    public Board() {
        this.id = UUID.randomUUID().toString();         // UUID를 사용하여 고유 ID를 생성합니다.
    }

}
