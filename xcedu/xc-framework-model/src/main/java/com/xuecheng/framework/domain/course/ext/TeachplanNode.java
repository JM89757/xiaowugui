package com.xuecheng.framework.domain.course.ext;

import com.xuecheng.framework.domain.course.Teachplan;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * Created by admin on 2018/2/7.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class TeachplanNode extends Teachplan {

    List<TeachplanNode> children;

}
