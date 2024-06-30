package study.data_jpa.dto;

import lombok.Data;
import study.data_jpa.entity.Member;

@Data
public class MemberDto {

    private Long id;
    private String username;
    private String teamName;

    public MemberDto(Long id, String username, String teamName) {
        this.id = id;
        this.username = username;
        this.teamName = teamName;
    }


    //Dto는 엔티티로 봐도 괜찮다.
    public MemberDto(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
    }
}
