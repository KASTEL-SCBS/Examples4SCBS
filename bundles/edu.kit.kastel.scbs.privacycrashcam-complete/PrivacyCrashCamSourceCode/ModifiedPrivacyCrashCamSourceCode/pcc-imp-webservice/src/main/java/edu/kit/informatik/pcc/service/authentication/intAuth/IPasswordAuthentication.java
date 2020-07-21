package edu.kit.informatik.pcc.service.authentication.intAuth;

public interface IPasswordAuthentication {

    public boolean authenticatePassword(int userId, String inputHash);

}
