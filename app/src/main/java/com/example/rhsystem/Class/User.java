package com.example.rhsystem.Class;

public class User {
    private String account;//账号
    private String password;//密码
    private int identity;//身份 0:管理员 1:学生 2:老师
    //根据identity 进行页面的跳转

    public User(){}
    public User(String account,String password,int identity)
    {
        this.account=account;
        this.password=password;
        this.identity=identity;
    }

    public String GetAccount() {return account;}

    public void SetAccount(String id) {this.account = account; }

    public String GetPassword() {
        return password;
    }

    public void SetPassword(String password) {this.password = password;}

    public int GetIdentity() {
        return identity;
    }

    public void SetIdentity(int identity) {
        this.identity = identity;
    }
}
