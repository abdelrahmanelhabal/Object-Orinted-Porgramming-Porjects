package service;
import model.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberService {
    List<Member> members ;

    public MemberService(){
        members = new ArrayList<>();
    }

    public boolean findMember(String username , String password , String email){
        for(Member member : members){
            if (member.getUsername().equals(username) && member.getPassword().equals(password) && member.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    public Member getMember(String username , String password , String email){
        for(Member member : members){
            if (member.getUsername().equals(username) && member.getPassword().equals(password) && member.getEmail().equals(email)){
                return member;
            }
        }
        return null;
    }

    public void addMember(Member member){
        for(Member m :members){
            if (m.getUsername().equals(member.getUsername())
                    && m.getPassword().equals(member.getPassword())
                    &&  m.getEmail().equals(member.getEmail())){
                System.out.println("This Member is already exist");
                return;
            }
        }
        members.add(member);
        System.out.println("Member added successfully");
    }

}
