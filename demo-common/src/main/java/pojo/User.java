package pojo;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;


/**
 * @Company: www.shangpin.com
 * @Author: Raymond
 * @Date: 2018/11/26 15:11
 * @Description:
 */
@Getter
@Setter
public class User {

    private Integer userId;
    @NotEmpty(message = "用户名不能为空")
    private String userName;

    public void setPassWord(String passWord) {
        StringBuffer str=new StringBuffer();
        try {
            byte[] md5spassword = MessageDigest.getInstance("MD5").digest(passWord.getBytes());
            for (byte b:md5spassword){
                int d=b & 0xff;
                String hexString=Integer.toHexString(d);
                if (hexString.length()==1){
                    hexString='0'+hexString;
                }
                str.append(hexString);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        this.passWord =str+"";
    }

    @NotEmpty(message = "密码不能为空")
    private String passWord;
    private Integer roleId;
    @Min(value = 10000000000L, message = "电话号码必须为11位！")
    @Max(value = 19999999999L, message = "电话号码必须为11位！")
    private Long tel;
    private String email;
    private int level;
    private Integer status;
    private String channel;
    private String localAdress;
    private Date lastOnlineTime;
    private Date createTime;
    private Date updateTime;


    public Integer getSalt() {
        return salt;
    }

    public void setSalt(Integer salt) {
        this.salt = salt;
    }

    private Integer salt;
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", roleId=" + roleId +
                ", tel=" + tel +
                ", email='" + email + '\'' +
                ", level=" + level +
                ", status=" + status +
                ", channel='" + channel + '\'' +
                ", localAdress='" + localAdress + '\'' +
                ", lastOnlineTime=" + lastOnlineTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

}
