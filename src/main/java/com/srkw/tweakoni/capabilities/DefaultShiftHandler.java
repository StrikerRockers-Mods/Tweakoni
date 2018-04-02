package com.srkw.tweakoni.capabilities;

public class DefaultShiftHandler implements ShiftHandler {
    private boolean shift;

    @Override
    public boolean getShift() {
        return shift;
    }

    @Override
    public void setShift(boolean value) {
        this.shift = value;
    }
}
