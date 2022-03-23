package jpa.imform.domain;

import jpa.imform.dto.BoardDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Board {

  @Id
  @GeneratedValue
  @Column(name = "board_id")
  private Long id;

  @Column(name = "board_userId")
  private String userId;

  @Column(name = "board_title")
  private String title;

  @Column(name = "board_content")
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @OneToMany(mappedBy = "board")
  private List<Comment> comments = new ArrayList<>();

  @CreationTimestamp
  @Column(name = "board_save_date", nullable = false, length = 20, updatable = false)
  private LocalDateTime saveDate;

  @UpdateTimestamp
  @Column(name = "board_update_date", length = 20)
  private LocalDateTime updateDate;

  public void setMember(Member member) {
    this.member = member;
    member.getBoards().add(this);
  }


  @Builder
  public Board(Long id, String userId, String title, String content, LocalDateTime saveDate, LocalDateTime updateDate) {
    this.id = id;
    this.userId = userId;
    this.title = title;
    this.content = content;
    this.saveDate = saveDate;
    this.updateDate = updateDate;
  }

  public void change(Board update) {
    this.userId = update.getUserId();
    this.title = update.getTitle();
    this.content = update.getContent();
  }

  public void changeWith(BoardDto.BoardRequest update) {
    this.id = update.getId();
    this.userId = update.getUserId();
  }
}
