package edu.kit.informatik.pcc.service.authentication.intAuth;

public class IntPasswordAuthentication implements IPasswordAuthentication {



    private final int[] ids = new int[1024];
    private final String[] hashes = new String[1024];

    /*@ normal_behaviour
	requires true;
	ensures \result <==> \dl_strContent(a) == \dl_strContent(b);
	assignable \strictly_nothing;
    */
    public native boolean equals(String a, String b);



    /*@ normal_behaviour
        ensures \result == true;
        assignable \strictly_nothing;
    */
    public boolean test() {
        return equals("abc", "abc");
    }

    /*@ normal_behaviour
        ensures \result == false;
        assignable \strictly_nothing;
    */
    public boolean test2() {
        return equals("abc", "abc2");
    }

    /*@ normal_behaviour
        ensures \result == true;
        assignable \strictly_nothing;
    */
    public boolean test3() {
        String a = "a";
        return equals(a, "a");
    }

    /*@ normal_behaviour
        ensures \result == false;
        assignable \strictly_nothing;
    */
    public boolean test4() { // not provable, a + "a" modifies the heap!
        String a = "a";
        return equals(a, a+"a");
    }


    // @ invariant ids != null && hashes != null;
    // @ invariant ids.length == hashes.length;
    // @ invariant ids != hashes;

    /*@ normal_behaviour
	requires ids != null && hashes != null;
	requires ids.length == hashes.length;
	requires ids != hashes;

        ensures \result >= -1 && \result < ids.length;
        ensures \result >= 0 ==> ids[\result] == userId && equals(hashes[\result], inputHash);
	ensures \result == -1 ==> (\forall int i;
					0 <= i && i < ids.length;
					!(ids[i] == userId && equals(hashes[i], inputHash)));

        assignable \strictly_nothing;
     */
    public int findUserPwdPair(int userId, String inputHash) {
        /*@
            loop_invariant 0 <= i && i <= ids.length;
            loop_invariant (\forall int k; 0 <= k && k < i;
            			!(ids[k] == userId && equals(hashes[k],inputHash)));

            loop_invariant ids != null && hashes != null;
   	    loop_invariant ids.length == hashes.length;
    	    loop_invariant ids != hashes;

            //loop_invariant this.<inv>;

            decreases ids.length - i;
            assignable \strictly_nothing;
        */
        for(int i = 0; i < ids.length;  i++) {
            if (ids[i] == userId && equals(hashes[i], inputHash)) {
                return i;
            }
        }
        return -1;
    }

    /*@ normal_behaviour
	requires ids != null && hashes != null;
	requires ids.length == hashes.length;
	requires ids != hashes;

        ensures \result <==>
        		(\exists int i; 0 <= i && i < ids.length;
        			ids[i] == userId && equals(hashes[i], inputHash));
        assignable \strictly_nothing;
     */
    public boolean authenticatePassword(int userId, String inputHash) {
        int pos = findUserPwdPair(userId, inputHash);
        return pos >= 0;
    }
}