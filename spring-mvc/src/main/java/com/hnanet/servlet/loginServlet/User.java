package com.hnanet.servlet.loginServlet;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String name;
    private String password;
    private Date birthDay;

    public String getBirthDay(){
        if(birthDay==null){
            return "";
        }else{
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(birthDay);
        }
    }
}
