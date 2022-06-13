package de.quinscape.Model;

import java.io.Serializable;

public class Client implements Serializable {
    private String name;
    private int insuranceNumber;

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", insuranceNumber=" + insuranceNumber +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(int insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }
}
