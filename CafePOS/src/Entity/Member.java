/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Chase Tan
 */
public class Member extends Person {
    private static final int startingID = 999;
    private static int count;
    private String member_ID;

    public Member() {
        
    }
    
    public Member(String name, String phoneNo, String emailAddress) {
        super(name, phoneNo, emailAddress);
        this.count++;
        this.member_ID = countMemberID(count);
    }
    
    public String countMemberID(int count) {
        int addedID;
        String memberID;
        
        addedID = startingID + count;
        Integer.toString(addedID);
        
        String str = new String("M");
        memberID = str.concat(Integer.toString(addedID));
        
        return memberID;
    }
    
    public String getMember_ID() {
        return member_ID;
    }

    @Override
    public String toString() {
        return String.format("Member : %8s  |", member_ID) + super.toString();
    }
    
}
