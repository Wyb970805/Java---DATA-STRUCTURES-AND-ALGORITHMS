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
    private String member_ID;
    private int pointCollected;

    public Member() {
        
    }
    
    public Member(String name, String phoneNo, String emailAddress, String member_ID, int pointCollected) {
        super(name, phoneNo, emailAddress);
        this.member_ID = member_ID;
        this.pointCollected = pointCollected;
    }
    
    public String getMember_ID() {
        return member_ID;
    }

    public void setMember_ID(String member_ID) {
        this.member_ID = member_ID;
    }

    public int getPointCollected() {
        return pointCollected;
    }

    public void setPointCollected(int pointCollected) {
        this.pointCollected = pointCollected;
    }

    @Override
    public String toString() {
        return super.toString() + " Member{" + "member_ID=" + member_ID + ", pointCollected=" + pointCollected + '}';
    }
    
    
}
