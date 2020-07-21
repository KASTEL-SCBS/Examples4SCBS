package edu.kit.informatik.pcc.service.accesscontrol;

public class AccessControl implements IAccessControl {

    public static final int ACCESS_ANONYMIZED = 1;
    public static final int ACCESS_UNANONYMIZED = 2;
    public static final int ACCESS_VIDEO_METADATA = 3;
    public static final int ACCESS_ALL_VIDEOINFORMATION = 4;
    public static final int ACCESS_THIRD_PARTY_VIDEO = 5;
    public static final int ACCESS_THIRD_PARTY_METADATA = 6;

    private final int[] ids = new int[1024];
    private final IntegerRole[] roles = new IntegerRole[1024];

    //@ invariant ids != null && roles != null;
    //@ invariant ids.length == roles.length;
    //@ invariant ids != roles;

    /*@ normal_behaviour
        requires (\forall int m; 0 <= m && m < roles.length; \invariant_for(roles[m]));
        ensures \result <==> (\exists int i; 0 <= i && i < ids.length;
                                ids[i] == id && roles[i].hasPermission(perm));
        assignable \strictly_nothing;
     */
    public boolean hasPermission(int id, int perm) {
        boolean res = false;
        /*@
            loop_invariant 0 <= x && x <= ids.length;
            loop_invariant (x < ids.length ==> \invariant_for(roles[x]));
            loop_invariant res <==> (\exists int k; 0 <= k && k < x; ids[k] == id &&
                    roles[k].hasPermission(perm));
            decreases ids.length - x;
            assignable \strictly_nothing;
        */
        for(int x = 0; x < ids.length;  x++) {
            if (ids[x] == id && roles[x].hasPermission(perm)) {
                res = true;
            }
        }
        return res;
    }

    public boolean canAccessUnanonymized(int userId) {
        return hasPermission(userId, ACCESS_UNANONYMIZED);
    }

    public boolean canAccessAnonymized(int userId) {
        return hasPermission(userId, ACCESS_ANONYMIZED);
    }

    public boolean canAccessAllVideoInformation(int userId) {
        return hasPermission(userId, ACCESS_ALL_VIDEOINFORMATION);
    }

    public boolean canAccessMetaData(int userId) {
        return hasPermission(userId, ACCESS_ALL_VIDEOINFORMATION)
                || hasPermission(userId, ACCESS_VIDEO_METADATA);
    }

    /*@ normal_behaviour
            requires true;
            ensures \result <==> (\exists int i; 0 <= i && i < ids.length;
                                    ids[i] == id && roles[i] == role);
            assignable ids[*], roles[*];
        */
    public boolean add(int id, IntegerRole role) {
        int pos = posOfId(id);
        if (pos >= 0) {
            roles[pos] = role;
            return true;
        } else {
            int zeroPos = posOfId(0);
            if (zeroPos < 0) return false;
            ids[zeroPos] = id;
            roles[zeroPos] = role;
            return true;
        }
    }

    /*@normal_behaviour
        requires true;
        ensures \result < 0 ==> (\forall int i; 0 <= i && i < ids.length; ids[i] != id);
        ensures \result >= 0 ==> (ids[\result] == id &&\result < ids.length) ;
        assignable \strictly_nothing;
    */
    private /*@ pure @*/ int posOfId(int id) {
        int pos = -1;
        /*@
            loop_invariant pos < 0 ==> (\forall int k; 0 <= k && k < i; ids[k] != id);
            loop_invariant pos >= 0 ==> (ids[pos] == id);
            loop_invariant pos >= 0 ==> 0 <= pos && pos < ids.length;
            loop_invariant 0 <= i && i <= ids.length;

            decreases ids.length - i;
            assignable \strictly_nothing;
        */
        for(int i = 0; i < ids.length;  i++) {
            if(ids[i] == id) {
                pos = i;
            }
        }
        return pos;
    }
}
