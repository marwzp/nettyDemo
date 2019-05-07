package com.icode.protocol.command;


import static com.icode.protocol.command.Command.LOGIN_REQUEST;

/**
 * 登录数据对象
 * @author wangz
 * @date 2019/5/7 - 16:57
 **/
public class LoginRequestPacket extends Packet {

    //用户id
    private Integer userId;

    //用户姓名
    private String username;

    //用户密码
    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
