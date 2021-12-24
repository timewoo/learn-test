package com.learntest.design.builderdesign;

/**
 * @author yanglin
 * @date 2021/1/29 10:53
 */
public interface Builder {

    void createCpu(String cpu);

    void createMainBoard(String mainBoard);

    void createHardDisk(String hardDisk);

    void createMemory(String memory);

    Computer createComputer();
}
