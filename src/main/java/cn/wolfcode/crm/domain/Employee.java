package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Setter
@Getter
public class Employee extends BaseDomain{

    private String username;

    private String realname;

    private String password;

    private String tel;

    private String email;

    private Department dept;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hireDate;

    private boolean state;

    private Boolean admin;

    private List<Role> roles = new ArrayList<>();
}