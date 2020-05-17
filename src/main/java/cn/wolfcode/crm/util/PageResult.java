package cn.wolfcode.crm.util;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class PageResult {
    private int total;//查询总条数
    private List<?> rows = new ArrayList<>();//结果集
}
