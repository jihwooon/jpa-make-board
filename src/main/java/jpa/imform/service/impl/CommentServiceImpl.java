package jpa.imform.service.impl;

import jpa.imform.domain.Board;
import jpa.imform.domain.Comment;
import jpa.imform.domain.Member;
import jpa.imform.dto.CommentDto;
import jpa.imform.error.CommentNotFoundException;
import jpa.imform.repository.CommentRepository;
import jpa.imform.service.BoardService;
import jpa.imform.service.CommentService;
import jpa.imform.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;
  private final BoardService boardService;
  private final MemberService memberService;

  @Override
  public List<CommentDto.CommentResponse> getComments(Long memberId, Long boardId) {
    Member member = memberService.getMember(memberId);
    Board board = boardService.getBoard(boardId);
    return CommentDto.CommentResponse.of(commentRepository.findByMemberAndBoard(member, board));
  }

  @Override
  public CommentDto.CommentResponse createComment(Long memberId, Long boardId, CommentDto.CommentRequest request) {
    Member member = memberService.getMember(memberId);
    Board board = boardService.getBoard(boardId);
    Comment comment = Comment.builder()
        .content(request.getContent())
        .member(member)
        .board(board)
        .build();
    return CommentDto.CommentResponse.of(commentRepository.save(comment));
  }

  @Override
  public Comment getComment(Long id) {
    return commentRepository.findById(id)
        .orElseThrow(() -> new CommentNotFoundException(id));
  }

  @Override
  public Comment updateComment(Long id, Comment source) {
    Comment comment = getComment(id);
    comment.change(source);
    return comment;
  }

  @Override
  public Comment deleteComment(Long id) {
    Comment comment = getComment(id);
    commentRepository.delete(comment);
    return comment;
  }
}
