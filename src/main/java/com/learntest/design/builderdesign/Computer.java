package com.learntest.design.builderdesign;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.digester.ObjectCreateRule;

/**
 * @author yanglin
 * @date 2021/1/29 10:55
 */
@Getter
@Setter
public class Computer {

    private String cpu;

    private String hardDisk;

    private String mainBoard;

    private String memory;
}
