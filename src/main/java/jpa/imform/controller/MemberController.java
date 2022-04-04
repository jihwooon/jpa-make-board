package jpa.imform.controller;

import jpa.imform.dto.MemberDto;
import jpa.imform.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  //MemberRepository Interface
  @GetMapping("member")
  public List<MemberDto.ListMemberResponse> list() {
    return memberService.getMembers();
  }

  //MemberJpaRepository
  @GetMapping("memberV2")
  public List<MemberDto.ListMemberResponse> listV2() {
    return memberService.getMembersV2();
  }

  @GetMapping("member/{memberId}")
  public MemberDto.DetailMemberResponse detail(@PathVariable final Long memberId) {
    return MemberDto.DetailMemberResponse.of(memberService.getMember(memberId));
  }

  @PostMapping("member")
  @ResponseStatus(HttpStatus.CREATED)
  public MemberDto.CreateMemberResponse create(@RequestBody @Valid final MemberDto.CreateMemberRequest create) {
    return memberService.createMember(create);
  }

  @PatchMapping("member/{memberId}")
  public MemberDto.UpdateMemberResponse update(@PathVariable final Long memberId,
                                               @RequestBody @Valid final MemberDto.UpdateMemberRequest update) {
    return memberService.updateMember(memberId, update);
  }

  @DeleteMapping("member/{memberId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remove(@PathVariable final Long memberId) {
    memberService.delete(memberId);
  }
}
