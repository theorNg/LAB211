/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import entity.Member;
import utils.Validator;

/**
 *
 * @author win
 */
public class MemberInputer {
    private Member member;

    public MemberInputer() {
        member= new Member();
    }
    
    public Member input(){
        member.setName(Validator.getString("Enter name: ", "Can not empty!", "^(?!\\s*$).+"));
        member.setAddress(Validator.getString("Enter address: ", "Can not empty!", "^(?!\\s*$).+"));
        member.setPhone(Validator.getString("Enter phone: ", "Please enter 0xxxxxxxxx(x is digit)!", "^0\\d{9}$"));
        member.setType(Validator.getString("Enter type: ", "Invalid!", "[A-Za-z0-9\\s]+"));
        return member;
    }
}
