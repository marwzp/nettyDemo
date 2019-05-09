package com.icode.protocol.response;

import com.icode.protocol.Packet;
import lombok.Data;

import static com.icode.protocol.command.Command.LOGIN_RESPONSE;

/**
 * 登录响应包
 * @author wangz
 * @date 2019/5/8 - 17:07
 **/
@Data
public class LoginResponsePacket extends Packet{

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
