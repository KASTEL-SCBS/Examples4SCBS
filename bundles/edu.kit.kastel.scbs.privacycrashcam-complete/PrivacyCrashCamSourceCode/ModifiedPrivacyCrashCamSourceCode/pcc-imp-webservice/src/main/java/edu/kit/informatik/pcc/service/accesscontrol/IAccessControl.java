package edu.kit.informatik.pcc.service.accesscontrol;

public interface IAccessControl {

    boolean canAccessUnanonymized(int userId);

    boolean canAccessAnonymized(int userId);

    boolean canAccessAllVideoInformation(int userId);

    boolean canAccessMetaData(int userId);

    boolean add(int id, IntegerRole role);
}
