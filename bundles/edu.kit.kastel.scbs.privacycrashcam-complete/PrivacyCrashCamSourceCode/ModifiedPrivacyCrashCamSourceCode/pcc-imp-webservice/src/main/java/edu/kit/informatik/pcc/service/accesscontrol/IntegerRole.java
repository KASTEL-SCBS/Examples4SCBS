package edu.kit.informatik.pcc.service.accesscontrol;

public enum IntegerRole {
    USER(new int[]{2}),
    LAW(new int[]{2, 3}),
    MAINTAINER(new int[]{4, 5, 6});

    //@ invariant permissions != null && permissions.length > 0;
    private /*@ spec_public @*/ int[] permissions;

    private IntegerRole(int[] permissions) {
        this.permissions = permissions;
    }

    /*@ public normal_behaviour
        ensures \result <==> (\exists int k; 0 <= k && k < permissions.length;
                            permissions[k] == perm);
        assignable \strictly_nothing;
     */
    public /*@ pure @*/ boolean hasPermission(int perm) {
        boolean res = false;
        /*@ loop_invariant 0 <= i && i <= permissions.length;
            loop_invariant res <==> (\exists int k; 0 <= k && k < i;
                            permissions[k] == perm);
            decreases permissions.length - i;
            assignable \strictly_nothing;
        */
        for (int i = 0; i < permissions.length; ++i) {
            if (permissions[i] == perm) {
                res = true;
            }
        }
        return res;
    }
}