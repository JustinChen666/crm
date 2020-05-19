package cn.wolfcode.crm.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JsonResult {
    private boolean success = true;
    private String msg;

    public JsonResult mark(String msg) {
        success = false;
        this.msg = msg;
        return this;
    }
}
