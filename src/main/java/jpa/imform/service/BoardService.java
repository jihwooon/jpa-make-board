package jpa.imform.service;

import jpa.imform.domain.Board;
import jpa.imform.dto.BoardDto;

import java.util.List;

public interface BoardService {

  List<BoardDto.ListBoardResponse> getBoards(Long memberId);

  BoardDto.CreateBoardResponse createBoard(Long memberId, BoardDto.CreateBoardRequest request);

  BoardDto.UpdateBoardResponse updateBoard(Long memberId, Long boardId, BoardDto.UpdateBoardRequest update);

  BoardDto.getBoardResponse getBoardByIdAndMemberId(Long memberId, Long boardId);

  Board getBoard(Long id);

  void deleteBoard(Long id);

  List<Board> getBoardsByMember(Long id);
}
