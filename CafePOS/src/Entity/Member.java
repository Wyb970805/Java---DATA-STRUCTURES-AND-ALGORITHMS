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
    private static final int startingID = 1000;
    private static int count;
    private String member_ID;
    private int pointCollected;

    public Member() {
        
    }
    
    public Member(String name, String phoneNo, String emailAddress, int pointCollected) {
        super(name, phoneNo, emailAddress);
        this.count++;
        this.member_ID = countMemberID(count);
        this.pointCollected = pointCollected;
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

    public int getPointCollected() {
        return pointCollected;
    }

    public void setPointCollected(int pointCollected) {
        this.pointCollected = pointCollected;
    }

    @Override
    public String toString() {
        return "Member {" + "member_ID=" + member_ID + ", pointCollected=" + pointCollected + super.toString() + '}';
    }
    
    
}
