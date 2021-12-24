package com.learntest.design.builderdesign;

/**
 * @author yanglin
 * @date 2021/1/29 11:07
 */
public class Director {

    private Builder builder;

    public Director(Builder builder){
        this.builder = builder;
    }

    public Computer createComputer(String cpu,String hardDisk,String mainBoard,String memory){
        this.builder.createCpu(cpu);
        this.builder.createMainBoard(mainBoard);
        this.builder.createHardDisk(hardDisk);
        this.builder.createMemory(memory);
        return this.builder.createComputer();
    }
}
