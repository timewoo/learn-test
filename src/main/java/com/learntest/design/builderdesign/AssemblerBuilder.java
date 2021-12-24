package com.learntest.design.builderdesign;

import org.checkerframework.checker.units.qual.C;

/**
 * @author yanglin
 * @date 2021/1/29 11:03
 */
public class AssemblerBuilder implements Builder {

    private Computer computer = new Computer();

    @Override
    public void createCpu(String cpu) {
        computer.setCpu(cpu);
    }

    @Override
    public void createMainBoard(String mainBoard) {
        computer.setMainBoard(mainBoard);
    }

    @Override
    public void createHardDisk(String hardDisk) {
        computer.setHardDisk(hardDisk);
    }

    @Override
    public void createMemory(String memory) {
        computer.setMemory(memory);
    }

    @Override
    public Computer createComputer() {
        return computer;
    }
}
