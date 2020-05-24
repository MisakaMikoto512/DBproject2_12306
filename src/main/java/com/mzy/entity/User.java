package com.mzy.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author mzy
 * @since 2020-05-22
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID=1L;

      private String user_id;

    private String password;

    private String authority;

    private String phone_number;

    public User(String userName,String userPassword,String authority,String phone_number){
        this.user_id = userName;
        this.password = userPassword;
        this.authority = authority;
        this.phone_number = phone_number;
    }

    public String getUserName(){
        return user_id;
    }

    public void setUserName(String userName){
        this.user_id = userName;
    }

    public String getUserPassword(){
        return password;
    }

    public void setUserPassword(String userPassword){
        this.password = userPassword;
    }

    public void setAuthority(String authority){
           this.authority = authority;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPhone_number(){
        return phone_number;
    }
}
