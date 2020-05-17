package cn.wolfcode.crm.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QueryObject {
    private int page;//当前页
    private int rows;//每页显示条数

    public int getStart() {
        return (page - 1) * rows;
    }
}
